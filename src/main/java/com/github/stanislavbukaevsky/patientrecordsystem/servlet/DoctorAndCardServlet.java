package com.github.stanislavbukaevsky.patientrecordsystem.servlet;

import com.github.stanislavbukaevsky.patientrecordsystem.serialization.Serialization;
import com.github.stanislavbukaevsky.patientrecordsystem.serialization.impl.DoctorAndCardSerialization;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/doctors-and-cards/*", description = "Сервлет, для работы с методами врача и талона пациента")
public class DoctorAndCardServlet extends HttpServlet {
    private final Serialization<HttpServletRequest, HttpServletResponse, String> doctorAndCardSerialization = DoctorAndCardSerialization.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doctorAndCardSerialization.responseHeader(resp);
        String requestHeader = doctorAndCardSerialization.requestHeader(req);
        doctorAndCardSerialization.doPost(req, resp, requestHeader);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doctorAndCardSerialization.responseHeader(resp);
        String requestHeader = doctorAndCardSerialization.requestHeader(req);
        doctorAndCardSerialization.doPut(req, resp, requestHeader);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doctorAndCardSerialization.responseHeader(resp);
        doctorAndCardSerialization.doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doctorAndCardSerialization.responseHeader(resp);
        doctorAndCardSerialization.doDelete(req, resp);
    }
}
