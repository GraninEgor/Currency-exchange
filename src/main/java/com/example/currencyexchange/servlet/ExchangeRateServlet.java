package com.example.currencyexchange.servlet;

import com.example.currencyexchange.entity.Currency;
import com.example.currencyexchange.entity.ExchangeRate;
import com.example.currencyexchange.service.ExchangeRateService;
import com.example.currencyexchange.utils.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@WebServlet("/exchangeRate/*")
public class ExchangeRateServlet extends HttpServlet {

    private static final ExchangeRateService exchangeRateService = ExchangeRateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String code = req.getPathInfo().substring(1);
            String baseCurrencyCode = code.substring(0,3);
            String targetCurrencyCode = code.substring(3,6);
            System.out.println(baseCurrencyCode);
            System.out.println(targetCurrencyCode);
            resp.setContentType("application/json");
            resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
            ExchangeRate exchangeRate = exchangeRateService.findByCode(baseCurrencyCode, targetCurrencyCode);
            String json = JsonUtil.toJson(exchangeRate);
            resp.getWriter().write(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
