package com.github.stanislavbukaevsky.patientrecordsystem.servlet;

import com.github.stanislavbukaevsky.patientrecordsystem.serialization.FindSerialization;
import com.github.stanislavbukaevsky.patientrecordsystem.serialization.impl.FindByForeignKeySerialization;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Класс-сервлет для метода поиска по внешнему ключу.
 * Наследуется от абстрактного класса {@link HttpServlet}
 */
@WebServlet(urlPatterns = "/find/*", description = "Сервлет, для работы с методами поиска по внешнему ключу")
public class FindByForeignKeyServlet extends HttpServlet {
    private final FindSerialization<HttpServletRequest, HttpServletResponse> findSerialization = FindByForeignKeySerialization.getInstance();

    /**
     * Этот метод для GET запроса
     *
     * @param req  запрос пользователя
     * @param resp ответ пользователю
     * @throws IOException исключение ввода/вывода
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        findSerialization.responseHeader(resp);
        findSerialization.doGet(req, resp);
    }
}
