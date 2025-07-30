package com.example.currencyexchange.dao;

import com.example.currencyexchange.entity.Currency;
import com.example.currencyexchange.utils.ConnectionManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDao implements Dao<Long, Currency>{

    private final static CurrencyDao INSTANCE = new CurrencyDao();

    private final static String CREATE_SQL = """
                                            INSERT INTO currencies
                                            (code, full_name, sign)
                                            VALUES (?,?,?)
""";
    private final static String FIND_ALL_SQL = """
                                            SELECT id, code, full_name, sign
                                            FROM currencies
""";
    private final static String FIND_BY_CODE_SQL = """
                                            SELECT * FROM currencies
                                            WHERE code = ?
""";

    @Override
    public Currency create(Currency currency) {
        try(var connection = ConnectionManager.open();
        var statement = connection.prepareStatement(CREATE_SQL)) {
            statement.setString(1,currency.getCode());
            statement.setString(2,currency.getFullName());
            statement.setString(3,currency.getSign());
            statement.executeUpdate();
            return currency;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Currency currency) {
        return false;
    }

    @Override
    public List<Currency> findAll() {
        try(var connection = ConnectionManager.open();
            var statement = connection.prepareStatement(FIND_ALL_SQL)) {
            List<Currency> currencies = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next()){
                currencies.add(new Currency(result.getLong("id"),
                        result.getString("code"),
                        result.getString("full_name"),
                        result.getString("sign")));
            }
            return currencies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    public static CurrencyDao getInstance() {
        return INSTANCE;
    }

    public Currency findByCode(String code) {
        try(var connection = ConnectionManager.open();
            var statement = connection.prepareStatement(FIND_BY_CODE_SQL)) {
            statement.setString(1, code);
            var result = statement.executeQuery();
            if (result.next()) {
                return new Currency(
                        result.getLong("id"),
                        result.getString("code"),
                        result.getString("full_name"),
                        result.getString("sign")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
