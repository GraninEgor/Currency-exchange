package com.example.currencyexchange.dao;

import com.example.currencyexchange.entity.Currency;
import com.example.currencyexchange.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CurrencyDao implements Dao<Long, Currency>{

    private final static CurrencyDao INSTANCE = new CurrencyDao();

    @Override
    public Currency save(Currency currency) {
        try(var connection = ConnectionManager.open();
        var statement = connection.prepareStatement( )) {
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
        return List.of();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
