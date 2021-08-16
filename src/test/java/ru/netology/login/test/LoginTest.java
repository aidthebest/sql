package ru.netology.login.test;

import com.codeborne.selenide.Condition;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.datahelper.DataHelper;
import ru.netology.dbhelper.DdInfo;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class LoginTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void tripleLogin() {


        LoginPage lp = new LoginPage();
        DdInfo dbInfo = new DdInfo();
        lp.userLogin(DataHelper.getWrongPass());
        $("[class=notification__content]").shouldBe(Condition.visible).shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"));
        lp.userLogin(DataHelper.getWrongPass());
        $("[class=notification__content]").shouldBe(Condition.visible).shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"));
        lp.userLogin(DataHelper.getWrongPass());
        $("[class=notification__content]").shouldBe(Condition.visible).shouldHave(Condition.exactText("Система заблокирована, попробуйте позже"));
        dbInfo.cleanDB();
    }


    @Test
    @SneakyThrows
    public void verifyLogin2() {
        DdInfo dbInfo = new DdInfo();
        LoginPage lp = new LoginPage();
        lp.userLogin(DataHelper.getAuthInfo());
        VerificationPage vp = new VerificationPage();
        vp.verify(dbInfo.getCode());
        $(".heading").shouldHave(Condition.exactText("  Личный кабинет"));
        dbInfo.cleanDB();
        }
    }
