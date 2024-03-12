package com.github.stanislavbukaevsky.patientrecordsystem.servlet;

import com.github.stanislavbukaevsky.patientrecordsystem.serialization.Serialization;
import com.github.stanislavbukaevsky.patientrecordsystem.serialization.impl.DoctorSerialization;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/doctors/*", description = "Сервлет, для работы с методами врача")
public class DoctorServlet extends HttpServlet {
    private final Serialization<HttpServletRequest, HttpServletResponse, String> doctorSerialization = DoctorSerialization.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doctorSerialization.responseHeader(resp);
        String requestHeader = doctorSerialization.requestHeader(req);
        doctorSerialization.doPost(req, resp, requestHeader);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doctorSerialization.responseHeader(resp);
        String requestHeader = doctorSerialization.requestHeader(req);
        doctorSerialization.doPut(req, resp, requestHeader);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doctorSerialization.responseHeader(resp);
        doctorSerialization.doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doctorSerialization.responseHeader(resp);
        doctorSerialization.doDelete(req, resp);
    }
}
