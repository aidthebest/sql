package ru.netology.dbhelper;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class DdInfo {

    @SneakyThrows
    public Connection getConnection() {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass"
        );
    }

    @SneakyThrows
    public void cleanDB() {
        var conn = getConnection();
        conn.prepareStatement("DROP TABLE IF EXISTS cards;");
        conn.prepareStatement("DROP TABLE IF EXISTS users;");
        conn.prepareStatement("DROP TABLE IF EXISTS auth_codes");
        conn.prepareStatement("DROP TABLE IF EXISTS card_transactions");
    }

    @SneakyThrows
    public String getCode () {
        var runner = new QueryRunner();
        var conn = getConnection();
        var codeSQL = "SELECT (code) FROM auth_codes ath, users us\n" +
                "WHERE ath.user_id=us.id and us.login='vasya'\n" +
                " and created=(select max(created) from auth_codes where\n" +
                "     user_id=ath.user_id);";
        String codeVer = runner.query(conn, codeSQL, new ScalarHandler<>());
        return codeVer;
    }
}
