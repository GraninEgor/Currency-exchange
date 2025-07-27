package com.example.currencyexchange;

import com.example.currencyexchange.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcRunner {
    public static void main(String[] args) {
        try (Connection connection = ConnectionManager.open()) {
            System.out.println(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
