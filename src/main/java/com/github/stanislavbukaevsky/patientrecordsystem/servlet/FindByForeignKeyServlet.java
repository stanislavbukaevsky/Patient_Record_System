package com.github.stanislavbukaevsky.patientrecordsystem.servlet;

import com.github.stanislavbukaevsky.patientrecordsystem.serialization.FindSerialization;
import com.github.stanislavbukaevsky.patientrecordsystem.serialization.impl.FindByForeignKeySerialization;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/find/*", description = "Сервлет, для работы с методами поиска по внешнему ключу")
public class FindByForeignKeyServlet extends HttpServlet {
    private final FindSerialization<HttpServletRequest, HttpServletResponse> findSerialization = FindByForeignKeySerialization.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        findSerialization.responseHeader(resp);
        findSerialization.doGet(req, resp);
    }
}
