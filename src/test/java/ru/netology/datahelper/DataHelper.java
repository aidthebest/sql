package ru.netology.datahelper;

import lombok.Value;

public class DataHelper {

    public DataHelper() {
    }
    @Value
    public static class UserInfo {
        String login;
        String password;
    }

    public static UserInfo getAuthInfo() {
        return new UserInfo("vasya", "qwerty123");
    }

    public static UserInfo getWrongPass() {
        return new UserInfo("vasya", "dfg34gdfg4e");
    }
}
