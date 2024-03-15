package com.github.stanislavbukaevsky.patientrecordsystem.servlet;

import com.github.stanislavbukaevsky.patientrecordsystem.serialization.Serialization;
import com.github.stanislavbukaevsky.patientrecordsystem.serialization.impl.DoctorSerialization;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Класс-сервлет для методов врача.
 * Наследуется от абстрактного класса {@link HttpServlet}
 */
@WebServlet(urlPatterns = "/doctors/*", description = "Сервлет, для работы с методами врача")
public class DoctorServlet extends HttpServlet {
    private final Serialization<HttpServletRequest, HttpServletResponse, String> doctorSerialization = DoctorSerialization.getInstance();

    /**
     * Этот метод для POST запроса (сохранение врача в базу данных)
     *
     * @param req  запрос пользователя
     * @param resp ответ пользователю
     * @throws IOException исключение ввода/вывода
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doctorSerialization.responseHeader(resp);
        String requestHeader = doctorSerialization.requestHeader(req);
        doctorSerialization.doPost(req, resp, requestHeader);
    }

    /**
     * Этот метод для PUT запроса (изменение врача в базе данных)
     *
     * @param req  запрос пользователя
     * @param resp ответ пользователю
     * @throws IOException исключение ввода/вывода
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doctorSerialization.responseHeader(resp);
        String requestHeader = doctorSerialization.requestHeader(req);
        doctorSerialization.doPut(req, resp, requestHeader);
    }

    /**
     * Этот метод для GET запроса (получение врача из базы данных)
     *
     * @param req  запрос пользователя
     * @param resp ответ пользователю
     * @throws IOException исключение ввода/вывода
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doctorSerialization.responseHeader(resp);
        doctorSerialization.doGet(req, resp);
    }

    /**
     * Этот метод для DELETE запроса (удаление врача из базы данных)
     *
     * @param req  запрос пользователя
     * @param resp ответ пользователю
     * @throws IOException исключение ввода/вывода
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doctorSerialization.responseHeader(resp);
        doctorSerialization.doDelete(req, resp);
    }
}
