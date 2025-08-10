package com.example.currencyexchange.servlet;

import com.example.currencyexchange.entity.Currency;
import com.example.currencyexchange.utils.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ExchangeRates extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
