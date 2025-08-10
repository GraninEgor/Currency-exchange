package com.example.currencyexchange.entity;

import java.math.BigDecimal;

public class ExchangeRate {
    private Long id;
    private Currency baseCurrency;
    private Currency targetCurrency;;
    private BigDecimal rate;
}
