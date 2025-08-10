package com.example.currencyexchange.servlet;

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
import java.util.List;


@WebServlet("/exchangeRates")
public class ExchangeRatesServlet extends HttpServlet {

    private static final ExchangeRateService exchangeRateService = ExchangeRateService.getInstance();

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
}
