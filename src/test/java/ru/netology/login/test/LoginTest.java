package ru.netology.login.test;

import com.codeborne.selenide.Condition;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.sql.DriverManager;

import static com.codeborne.selenide.Selenide.*;


public class LoginTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void tripleLogin() {
        $("[name=login]").setValue("vasya");
        $("[name=password]").setValue("jkgg76");
        $("[data-test-id=action-login]").click();
        $("[class=notification__content]").shouldBe(Condition.visible).shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"));
        $("[name=password]").doubleClick().sendKeys(Keys.SPACE);
        $("[name=password]").setValue("dfgdg");
        $("[data-test-id=action-login]").click();
        $("[class=notification__content]").shouldBe(Condition.visible).shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"));
        $("[name=password]").doubleClick().sendKeys(Keys.SPACE);
        $("[name=password]").setValue("sdvsss");
        $("[data-test-id=action-login]").click();
        $("[class=notification__content]").shouldBe(Condition.visible).shouldHave(Condition.exactText("Система заблокирована, попробуйте позже"));
    }


    @Test
    @SneakyThrows
    public void verifyLogin2() {
        var runner = new QueryRunner();
        var codeSQL = "SELECT (code) FROM auth_codes WHERE last_day(created);";
        $("[name=login]").setValue("vasya");
        $("[name=password]").setValue("qwerty123");
        $("button").click();
        var conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass"
        );
        String codeVer = runner.query(conn, codeSQL, new ScalarHandler<>());

            $("[class=input__control]").setValue(codeVer);
            $("[data-test-id=action-verify]").click();
            $(".heading").shouldHave(Condition.exactText("  Личный кабинет"));
        }
    }
