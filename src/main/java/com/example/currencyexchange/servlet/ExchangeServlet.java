package com.example.currencyexchange.servlet;

import com.example.currencyexchange.dto.ConvertedAmountDTO;
import com.example.currencyexchange.entity.ExchangeRate;
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

@WebServlet("/exchange")
public class ExchangeServlet extends HttpServlet {

    private static final ExchangeRateService exchangeRateService = ExchangeRateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String baseCurrencyCode = req.getParameter("from");
        String targetCurrencyCode = req.getParameter("to");
        BigDecimal amount = new BigDecimal(req.getParameter("amount"));
        resp.setContentType("application/json");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        ConvertedAmountDTO convertedAmountDTO = exchangeRateService.convert(baseCurrencyCode,targetCurrencyCode,amount);
        String json = JsonUtil.toJson(convertedAmountDTO);
        resp.getWriter().write(json);
    }
}
