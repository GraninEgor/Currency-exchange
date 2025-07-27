package com.example.currencyexchange.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";

    static {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("PostgreSQL JDBC Driver registered!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL JDBC Driver not found in classpath. " +
                    "Please ensure postgresql JAR is in WEB-INF/lib", e);
        }
    }

    public static Connection open(){
        try {
           return DriverManager.getConnection(PropertiesUtil.get(URL_KEY),PropertiesUtil.get(USERNAME_KEY),PropertiesUtil.get(PASSWORD_KEY));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
