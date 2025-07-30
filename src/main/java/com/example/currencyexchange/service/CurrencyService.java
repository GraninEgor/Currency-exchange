package com.example.currencyexchange.service;

import com.example.currencyexchange.dao.CurrencyDao;
import com.example.currencyexchange.entity.Currency;

import java.util.List;

public class CurrencyService {

    private static final CurrencyDao currencyDao = CurrencyDao.getInstance();

    private static final CurrencyService INSTANCE = new CurrencyService();

    public static CurrencyService getInstance(){
        return INSTANCE;
    }

    public List<Currency> findAll(){
        return currencyDao.findAll();
    }

    public Currency findByCode(String code) {
        return currencyDao.findByCode(code);
    }

    public void create(Currency currency) {
        currencyDao.create(currency);
    }
}
