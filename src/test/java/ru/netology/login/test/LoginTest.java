package ru.netology.login.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.datahelper.DataHelper;
import ru.netology.dbhelper.DdInfo;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;


public class LoginTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @AfterAll
    public static void clean() {
        DdInfo.cleanDB();
    };

    @Test
    public void tripleLogin() {

        var loginPage = new LoginPage();
        var userInfo = DataHelper.getWrongAuthInfo();
        loginPage.wrondLogin(userInfo);
        loginPage.wrongLorP();
        loginPage.wrondLogin(userInfo);
        loginPage.wrongLorP();
        loginPage.wrondLogin(userInfo);
        loginPage.systemBlockMessage();
    }


    @Test
    public void verifyLogin2() {
        var loginPage = new LoginPage();
        var userInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.userLogin(userInfo);
        verificationPage.verify(DdInfo.getCode());
        }
    }
