package com.github.stanislavbukaevsky.patientrecordsystem.serialization.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.CardRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.CardRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.CardResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.CardNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.serialization.Serialization;
import com.github.stanislavbukaevsky.patientrecordsystem.service.Service;
import com.github.stanislavbukaevsky.patientrecordsystem.service.impl.CardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CardSerialization implements Serialization<HttpServletRequest, HttpServletResponse, String> {
    private final static CardSerialization INSTANCE = new CardSerialization();
    private final Service<CardRequestDto, CardRequestUpdateDto, CardResponseDto, Long> cardService = CardService.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private CardSerialization() {
    }

    public static CardSerialization getInstance() {
        return INSTANCE;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response, String requestHeader) throws IOException {
        String answer = "";
        CardRequestDto cardRequest;

        try {
            cardRequest = objectMapper.readValue(requestHeader, CardRequestDto.class);
            CardResponseDto cardResponse = cardService.save(cardRequest);
            response.setStatus(HttpServletResponse.SC_OK);
            answer = objectMapper.writeValueAsString(cardResponse);
        } catch (CardNotFoundException c) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            answer = c.getMessage();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            answer = e.getMessage();
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write(answer);
        printWriter.flush();
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response, String requestHeader) throws IOException {
        String answer = "";
        CardRequestUpdateDto cardRequest;

        try {
            cardRequest = objectMapper.readValue(requestHeader, CardRequestUpdateDto.class);
            CardResponseDto cardResponse = cardService.update(cardRequest);
            response.setStatus(HttpServletResponse.SC_OK);
            answer = objectMapper.writeValueAsString(cardResponse);
        } catch (CardNotFoundException c) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            answer = c.getMessage();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            answer = e.getMessage();
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write(answer);
        printWriter.flush();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String answer = "";

        try {
            String uri = request.getRequestURI();
            if (uri.equals("/cards/all")) {
                List<CardResponseDto> cardResponse = cardService.findAll();
                response.setStatus(HttpServletResponse.SC_OK);
                answer = objectMapper.writeValueAsString(cardResponse);
            } else {
                String[] findId = uri.split("/");
                Long id = Long.parseLong(findId[2]);
                CardResponseDto cardResponse = cardService.findById(id);
                response.setStatus(HttpServletResponse.SC_OK);
                answer = objectMapper.writeValueAsString(cardResponse);
            }
        } catch (CardNotFoundException c) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            answer = c.getMessage();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            answer = e.getMessage();
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write(answer);
        printWriter.flush();
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String answer = "";

        try {
            String uri = request.getRequestURI();
            String[] findId = uri.split("/");
            Long id = Long.parseLong(findId[2]);
            String deleteCard = cardService.delete(id);
            response.setStatus(HttpServletResponse.SC_OK);
            answer = objectMapper.writeValueAsString(deleteCard);
        } catch (CardNotFoundException c) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            answer = c.getMessage();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            answer = e.getMessage();
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write(answer);
        printWriter.flush();
    }
}
