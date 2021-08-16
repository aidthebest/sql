package ru.netology.page;

import ru.netology.datahelper.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public void userLogin(DataHelper.UserInfo info) {
        $("[name=login]").setValue(info.getLogin());
        $("[name=password]").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();
    }
}
