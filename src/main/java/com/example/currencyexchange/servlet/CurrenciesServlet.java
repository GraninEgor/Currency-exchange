package com.example.currencyexchange.servlet;

import com.example.currencyexchange.entity.Currency;
import com.example.currencyexchange.service.CurrencyService;
import com.example.currencyexchange.utils.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/currencies")
public class CurrenciesServlet extends HttpServlet {

    private static final CurrencyService currencyService = CurrencyService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
            List<Currency> currencies = currencyService.findAll();
            String json = JsonUtil.toJson(currencies);
            resp.getWriter().write(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String fullName = req.getParameter("code");
            String code = req.getParameter("fullName");
            String sign = req.getParameter("sign");
            currencyService.create(new Currency(null,fullName,code,sign));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
