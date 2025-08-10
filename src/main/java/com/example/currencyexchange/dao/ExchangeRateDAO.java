package com.example.currencyexchange.dao;

import com.example.currencyexchange.entity.Currency;
import com.example.currencyexchange.entity.ExchangeRate;
import com.example.currencyexchange.servlet.ExchangeRatesServlet;
import com.example.currencyexchange.utils.ConnectionManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExchangeRateDAO implements Dao<Long, ExchangeRate>{

    private static final ExchangeRateDAO INSTANCE = new ExchangeRateDAO();

    public static ExchangeRateDAO getInstance() {
        return INSTANCE;
    }

    private final static String FIND_ALL_SQL = """
                                            SELECT
                                                e.id AS exchange_rate_id, e.base_currency_id, e.target_currency_id, e.rate,
                                                bc.id AS base_currency_id, bc.code AS base_currency_code, bc.full_name AS base_currency_full_name, bc.sign AS base_currency_sign,
                                                tc.id AS target_currency_id, tc.code AS target_currency_code, tc.full_name AS target_currency_full_name, tc.sign AS target_currency_sign
                                            FROM exchange_rates e
                                            JOIN currencies bc ON e.base_currency_id = bc.id
                                            JOIN currencies tc ON e.target_currency_id = tc.id;
""";

    @Override
    public boolean update(ExchangeRate exchangeRate) {
        return false;
    }

    @Override
    public List<ExchangeRate> findAll() {
        try(var connection = ConnectionManager.open();
            var statement = connection.prepareStatement(FIND_ALL_SQL)) {
            List<ExchangeRate> exchangeRates = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next()){
                Currency baseCurrency = new Currency(result.getLong("base_currency_id"),
                        result.getString("base_currency_code"),
                        result.getString("base_currency_full_name"),
                        result.getString("base_currency_sign"));

                Currency targetCurrency = new Currency(result.getLong("target_currency_id"),
                        result.getString("target_currency_code"),
                        result.getString("target_currency_full_name"),
                        result.getString("target_currency_sign"));

                exchangeRates.add(new ExchangeRate(result.getLong("exchange_rate_id"),
                        baseCurrency,
                        targetCurrency,
                        result.getBigDecimal("rate")));
            }
            return exchangeRates;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ExchangeRate create(ExchangeRate exchangeRate) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
