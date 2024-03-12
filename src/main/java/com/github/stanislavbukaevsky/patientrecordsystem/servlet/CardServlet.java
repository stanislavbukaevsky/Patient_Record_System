package com.github.stanislavbukaevsky.patientrecordsystem.servlet;

import com.github.stanislavbukaevsky.patientrecordsystem.serialization.Serialization;
import com.github.stanislavbukaevsky.patientrecordsystem.serialization.impl.CardSerialization;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/cards/*", description = "Сервлет, для работы с методами карты пациента")
public class CardServlet extends HttpServlet {
    private final Serialization<HttpServletRequest, HttpServletResponse, String> cardSerialization = CardSerialization.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        cardSerialization.responseHeader(resp);
        String requestHeader = cardSerialization.requestHeader(req);
        cardSerialization.doPost(req, resp, requestHeader);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        cardSerialization.responseHeader(resp);
        String requestHeader = cardSerialization.requestHeader(req);
        cardSerialization.doPut(req, resp, requestHeader);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        cardSerialization.responseHeader(resp);
        cardSerialization.doGet(req, resp);
    }

    /**
     * todo Настроить удаление записи таким образом, чтобы запись удалялась из другой таблицы тоже
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        cardSerialization.responseHeader(resp);
        cardSerialization.doDelete(req, resp);
    }
}
