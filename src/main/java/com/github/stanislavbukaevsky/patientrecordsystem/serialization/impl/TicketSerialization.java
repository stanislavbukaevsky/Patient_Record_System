package com.github.stanislavbukaevsky.patientrecordsystem.serialization.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.TicketRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.TicketRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.TicketResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.TicketNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.serialization.Serialization;
import com.github.stanislavbukaevsky.patientrecordsystem.service.Service;
import com.github.stanislavbukaevsky.patientrecordsystem.service.impl.TicketService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Класс для сериализации в JSON формат для талона.
 * Реализует интерфейс {@link Serialization}. Параметры: <br>
 * {@link HttpServletRequest} - запрос пользователя <br>
 * {@link HttpServletResponse} - ответ пользователю <br>
 * {@link String} - объект строки
 */
public class TicketSerialization implements Serialization<HttpServletRequest, HttpServletResponse, String> {
    private final static TicketSerialization INSTANCE = new TicketSerialization();
    private final Service<TicketRequestDto, TicketRequestUpdateDto, TicketResponseDto, Long> ticketService = TicketService.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private TicketSerialization() {
    }

    public static TicketSerialization getInstance() {
        return INSTANCE;
    }

    /**
     * Этот метод преобразует в JSON для сохранения талона
     *
     * @param request       запрос пользователя
     * @param response      ответ пользователю
     * @param requestHeader запрос пользователя, преобразованный в строку
     * @throws IOException исключение ввода/вывода
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response, String requestHeader) throws IOException {
        String answer = "";
        TicketRequestDto ticketRequest;

        try {
            ticketRequest = objectMapper.readValue(requestHeader, TicketRequestDto.class);
            TicketResponseDto ticketResponse = ticketService.save(ticketRequest);
            response.setStatus(HttpServletResponse.SC_OK);
            answer = objectMapper.writeValueAsString(ticketResponse);
        } catch (TicketNotFoundException t) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            answer = t.getMessage();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            answer = e.getMessage();
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write(answer);
        printWriter.flush();
    }

    /**
     * Этот метод преобразует в JSON для изменения талона
     *
     * @param request       запрос пользователя
     * @param response      ответ пользователю
     * @param requestHeader запрос пользователя, преобразованный в строку
     * @throws IOException исключение ввода/вывода
     */
    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response, String requestHeader) throws IOException {
        String answer = "";
        TicketRequestUpdateDto ticketRequest;

        try {
            ticketRequest = objectMapper.readValue(requestHeader, TicketRequestUpdateDto.class);
            TicketResponseDto ticketResponse = ticketService.update(ticketRequest);
            response.setStatus(HttpServletResponse.SC_OK);
            answer = objectMapper.writeValueAsString(ticketResponse);
        } catch (TicketNotFoundException t) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            answer = t.getMessage();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            answer = e.getMessage();
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write(answer);
        printWriter.flush();
    }

    /**
     * Этот метод преобразует в JSON для получения талона
     *
     * @param request  запрос пользователя
     * @param response ответ пользователю
     * @throws IOException исключение ввода/вывода
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String answer = "";

        try {
            String uri = request.getRequestURI();
            if (uri.equals("/tickets/all")) {
                List<TicketResponseDto> ticketResponse = ticketService.findAll();
                response.setStatus(HttpServletResponse.SC_OK);
                answer = objectMapper.writeValueAsString(ticketResponse);
            } else {
                String[] findId = uri.split("/");
                Long id = Long.parseLong(findId[2]);
                TicketResponseDto ticketResponse = ticketService.findById(id);
                response.setStatus(HttpServletResponse.SC_OK);
                answer = objectMapper.writeValueAsString(ticketResponse);
            }
        } catch (TicketNotFoundException t) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            answer = t.getMessage();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            answer = e.getMessage();
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write(answer);
        printWriter.flush();
    }

    /**
     * Этот метод преобразует в JSON для удаления талона
     *
     * @param request  запрос пользователя
     * @param response ответ пользователю
     * @throws IOException исключение ввода/вывода
     */
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String answer = "";

        try {
            String uri = request.getRequestURI();
            String[] findId = uri.split("/");
            Long id = Long.parseLong(findId[2]);
            String deleteTicket = ticketService.delete(id);
            response.setStatus(HttpServletResponse.SC_OK);
            answer = objectMapper.writeValueAsString(deleteTicket);
        } catch (TicketNotFoundException t) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            answer = t.getMessage();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            answer = e.getMessage();
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write(answer);
        printWriter.flush();
    }
}
