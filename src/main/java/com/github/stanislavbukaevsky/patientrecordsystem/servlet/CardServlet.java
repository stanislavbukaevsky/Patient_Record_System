package com.github.stanislavbukaevsky.patientrecordsystem.servlet;

import com.github.stanislavbukaevsky.patientrecordsystem.serialization.Serialization;
import com.github.stanislavbukaevsky.patientrecordsystem.serialization.impl.CardSerialization;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Класс-сервлет для методов карты пациента.
 * Наследуется от абстрактного класса {@link HttpServlet}
 */
@WebServlet(urlPatterns = "/cards/*", description = "Сервлет, для работы с методами карты пациента")
public class CardServlet extends HttpServlet {
    private final Serialization<HttpServletRequest, HttpServletResponse, String> cardSerialization = CardSerialization.getInstance();

    /**
     * Этот метод для POST запроса (сохранение карты пациента в базу данных)
     *
     * @param req  запрос пользователя
     * @param resp ответ пользователю
     * @throws IOException исключение ввода/вывода
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        cardSerialization.responseHeader(resp);
        String requestHeader = cardSerialization.requestHeader(req);
        cardSerialization.doPost(req, resp, requestHeader);
    }

    /**
     * Этот метод для PUT запроса (изменение карты пациента в базе данных)
     *
     * @param req  запрос пользователя
     * @param resp ответ пользователю
     * @throws IOException исключение ввода/вывода
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        cardSerialization.responseHeader(resp);
        String requestHeader = cardSerialization.requestHeader(req);
        cardSerialization.doPut(req, resp, requestHeader);
    }

    /**
     * Этот метод для GET запроса (получение карты пациента из базы данных)
     *
     * @param req  запрос пользователя
     * @param resp ответ пользователю
     * @throws IOException исключение ввода/вывода
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        cardSerialization.responseHeader(resp);
        cardSerialization.doGet(req, resp);
    }

    /**
     * Этот метод для DELETE запроса (удаление карты пациента из базы данных)
     *
     * @param req  запрос пользователя
     * @param resp ответ пользователю
     * @throws IOException исключение ввода/вывода
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        cardSerialization.responseHeader(resp);
        cardSerialization.doDelete(req, resp);
    }
}
