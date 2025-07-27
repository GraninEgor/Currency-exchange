package com.example.currencyexchange;

import com.example.currencyexchange.dao.CurrencyDao;

public class JdbcRunner {
    public static void main(String[] args) {
        CurrencyDao currencyDao = new CurrencyDao();
        System.out.println(currencyDao.findAll());
    }
}
