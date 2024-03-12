package com.github.stanislavbukaevsky.patientrecordsystem.servlet;

import com.github.stanislavbukaevsky.patientrecordsystem.serialization.Serialization;
import com.github.stanislavbukaevsky.patientrecordsystem.serialization.impl.PatientSerialization;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/patients/*", description = "Сервлет, для работы с методами пациента")
public class PatientServlet extends HttpServlet {
    private final Serialization<HttpServletRequest, HttpServletResponse, String> patientSerialization = PatientSerialization.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        patientSerialization.responseHeader(resp);
        String requestHeader = patientSerialization.requestHeader(req);
        patientSerialization.doPost(req, resp, requestHeader);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        patientSerialization.responseHeader(resp);
        String requestHeader = patientSerialization.requestHeader(req);
        patientSerialization.doPut(req, resp, requestHeader);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        patientSerialization.responseHeader(resp);
        patientSerialization.doGet(req, resp);
    }

    /**
     * todo Настроить удаление записи таким образом, чтобы запись удалялась из другой таблицы тоже
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        patientSerialization.responseHeader(resp);
        patientSerialization.doDelete(req, resp);
    }
}
