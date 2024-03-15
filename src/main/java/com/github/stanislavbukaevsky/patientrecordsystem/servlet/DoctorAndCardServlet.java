package com.github.stanislavbukaevsky.patientrecordsystem.servlet;

import com.github.stanislavbukaevsky.patientrecordsystem.serialization.Serialization;
import com.github.stanislavbukaevsky.patientrecordsystem.serialization.impl.DoctorAndCardSerialization;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Класс-сервлет для методов врача и карты пациента.
 * Наследуется от абстрактного класса {@link HttpServlet}
 */
@WebServlet(urlPatterns = "/doctors-and-cards/*", description = "Сервлет, для работы с методами врача и карты пациента")
public class DoctorAndCardServlet extends HttpServlet {
    private final Serialization<HttpServletRequest, HttpServletResponse, String> doctorAndCardSerialization = DoctorAndCardSerialization.getInstance();

    /**
     * Этот метод для POST запроса (сохранение врача и карты пациента в базу данных)
     *
     * @param req  запрос пользователя
     * @param resp ответ пользователю
     * @throws IOException исключение ввода/вывода
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doctorAndCardSerialization.responseHeader(resp);
        String requestHeader = doctorAndCardSerialization.requestHeader(req);
        doctorAndCardSerialization.doPost(req, resp, requestHeader);
    }

    /**
     * Этот метод для PUT запроса (изменение врача и карты пациента в базе данных)
     *
     * @param req  запрос пользователя
     * @param resp ответ пользователю
     * @throws IOException исключение ввода/вывода
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doctorAndCardSerialization.responseHeader(resp);
        String requestHeader = doctorAndCardSerialization.requestHeader(req);
        doctorAndCardSerialization.doPut(req, resp, requestHeader);
    }

    /**
     * Этот метод для GET запроса (получение врача и карты пациента из базы данных)
     *
     * @param req  запрос пользователя
     * @param resp ответ пользователю
     * @throws IOException исключение ввода/вывода
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doctorAndCardSerialization.responseHeader(resp);
        doctorAndCardSerialization.doGet(req, resp);
    }

    /**
     * Этот метод для DELETE запроса (удаление врача и карты пациента из базы данных)
     *
     * @param req  запрос пользователя
     * @param resp ответ пользователю
     * @throws IOException исключение ввода/вывода
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doctorAndCardSerialization.responseHeader(resp);
        doctorAndCardSerialization.doDelete(req, resp);
    }
}
