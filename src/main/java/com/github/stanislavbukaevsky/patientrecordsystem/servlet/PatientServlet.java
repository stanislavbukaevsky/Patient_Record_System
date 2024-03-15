package com.github.stanislavbukaevsky.patientrecordsystem.servlet;

import com.github.stanislavbukaevsky.patientrecordsystem.serialization.Serialization;
import com.github.stanislavbukaevsky.patientrecordsystem.serialization.impl.PatientSerialization;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Класс-сервлет для методов пациента.
 * Наследуется от абстрактного класса {@link HttpServlet}
 */
@WebServlet(urlPatterns = "/patients/*", description = "Сервлет, для работы с методами пациента")
public class PatientServlet extends HttpServlet {
    private final Serialization<HttpServletRequest, HttpServletResponse, String> patientSerialization = PatientSerialization.getInstance();

    /**
     * Этот метод для POST запроса (сохранение пациента в базу данных)
     *
     * @param req  запрос пользователя
     * @param resp ответ пользователю
     * @throws IOException исключение ввода/вывода
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        patientSerialization.responseHeader(resp);
        String requestHeader = patientSerialization.requestHeader(req);
        patientSerialization.doPost(req, resp, requestHeader);
    }

    /**
     * Этот метод для PUT запроса (изменение пациента в базе данных)
     *
     * @param req  запрос пользователя
     * @param resp ответ пользователю
     * @throws IOException исключение ввода/вывода
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        patientSerialization.responseHeader(resp);
        String requestHeader = patientSerialization.requestHeader(req);
        patientSerialization.doPut(req, resp, requestHeader);
    }

    /**
     * Этот метод для GET запроса (получение пациента из базы данных)
     *
     * @param req  запрос пользователя
     * @param resp ответ пользователю
     * @throws IOException исключение ввода/вывода
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        patientSerialization.responseHeader(resp);
        patientSerialization.doGet(req, resp);
    }

    /**
     * Этот метод для DELETE запроса (удаление пациента из базы данных)
     *
     * @param req  запрос пользователя
     * @param resp ответ пользователю
     * @throws IOException исключение ввода/вывода
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        patientSerialization.responseHeader(resp);
        patientSerialization.doDelete(req, resp);
    }
}
