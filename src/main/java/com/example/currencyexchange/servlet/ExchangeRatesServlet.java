package com.example.currencyexchange.servlet;

import com.example.currencyexchange.entity.Currency;
import com.example.currencyexchange.entity.ExchangeRate;
import com.example.currencyexchange.service.CurrencyService;
import com.example.currencyexchange.service.ExchangeRateService;
import com.example.currencyexchange.utils.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;


@WebServlet("/exchangeRates")
public class ExchangeRatesServlet extends HttpServlet {

    private static final ExchangeRateService exchangeRateService = ExchangeRateService.getInstance();
    private static final CurrencyService currencyService = CurrencyService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
            List<ExchangeRate> currencies = exchangeRateService.findAll();
            String json = JsonUtil.toJson(currencies);
            resp.getWriter().write(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            String baseCurrencyCode = req.getParameter("baseCurrencyCode");
            String targetCurrencyCode = req.getParameter("targetCurrencyCode");
            BigDecimal rate = new BigDecimal(req.getParameter("rate"));
            Currency baseCurrency = currencyService.findByCode(baseCurrencyCode);
            Currency targetCurrency = currencyService.findByCode(targetCurrencyCode);
            exchangeRateService.create(new ExchangeRate(null,baseCurrency,targetCurrency,rate));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
