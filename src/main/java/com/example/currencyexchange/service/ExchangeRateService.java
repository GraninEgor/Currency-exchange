package com.example.currencyexchange.service;

import com.example.currencyexchange.dao.ExchangeRateDAO;
import com.example.currencyexchange.dto.ConvertedAmountDTO;
import com.example.currencyexchange.entity.Currency;
import com.example.currencyexchange.entity.ExchangeRate;

import java.math.BigDecimal;
import java.util.List;

public class ExchangeRateService {

    private static final ExchangeRateDAO exchangeRateDAO = ExchangeRateDAO.getInstance();

    private static final ExchangeRateService INSTANCE = new ExchangeRateService();

    public static ExchangeRateService getInstance() {
        return INSTANCE;
    }

    public List<ExchangeRate> findAll() {
        return exchangeRateDAO.findAll();
    }

    public ExchangeRate findByCode(String baseCurrencyCode, String targetCurrencyCode) {
        return exchangeRateDAO.findByCode(baseCurrencyCode, targetCurrencyCode);
    }

    public void create(ExchangeRate exchangeRate) {
        exchangeRateDAO.create(exchangeRate);
    }

    public ConvertedAmountDTO convert(String baseCurrencyCode, String targetCurrencyCode, BigDecimal amount) {
        ExchangeRate exchangeRate = findByCode(baseCurrencyCode, targetCurrencyCode);
        if(exchangeRate != null){
            BigDecimal convertedAmount = exchangeRate.getRate().multiply(amount);
            return new ConvertedAmountDTO(exchangeRate.getBaseCurrency(), exchangeRate.getTargetCurrency(), exchangeRate.getRate(), amount, convertedAmount);
        }
        else{
            exchangeRate = findByCode(targetCurrencyCode, baseCurrencyCode);
            if(exchangeRate != null){

            }
            else{

            }
        }
        return null;
    }
}
