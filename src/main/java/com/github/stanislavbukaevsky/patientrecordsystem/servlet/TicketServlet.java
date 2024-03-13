package com.github.stanislavbukaevsky.patientrecordsystem.servlet;

import com.github.stanislavbukaevsky.patientrecordsystem.serialization.Serialization;
import com.github.stanislavbukaevsky.patientrecordsystem.serialization.impl.TicketSerialization;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/tickets/*", description = "Сервлет, для работы с методами талона пациента")
public class TicketServlet extends HttpServlet {
    private final Serialization<HttpServletRequest, HttpServletResponse, String> ticketSerialization = TicketSerialization.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ticketSerialization.responseHeader(resp);
        String requestHeader = ticketSerialization.requestHeader(req);
        ticketSerialization.doPost(req, resp, requestHeader);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ticketSerialization.responseHeader(resp);
        String requestHeader = ticketSerialization.requestHeader(req);
        ticketSerialization.doPut(req, resp, requestHeader);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ticketSerialization.responseHeader(resp);
        ticketSerialization.doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ticketSerialization.responseHeader(resp);
        ticketSerialization.doDelete(req, resp);
    }
}
