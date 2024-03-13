package com.github.stanislavbukaevsky.patientrecordsystem.serialization.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.FindByCardsResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.FindByDoctorsAndCardsResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.FindByTicketsResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.CardNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.serialization.FindSerialization;
import com.github.stanislavbukaevsky.patientrecordsystem.service.FindService;
import com.github.stanislavbukaevsky.patientrecordsystem.service.impl.FindByForeignKeyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FindByForeignKeySerialization implements FindSerialization<HttpServletRequest, HttpServletResponse> {
    private final static FindByForeignKeySerialization INSTANCE = new FindByForeignKeySerialization();
    private final FindService findService = FindByForeignKeyService.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private FindByForeignKeySerialization() {
    }

    public static FindByForeignKeySerialization getInstance() {
        return INSTANCE;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String answer = "";

        try {
            String uri = request.getRequestURI();
            String[] findId = uri.split("/");
            Long id = Long.parseLong(findId[3]);

            if (uri.equals("/find/cards-patient/" + id)) {
                List<FindByCardsResponseDto> cardsResponse = findService.findCardsByPatientId(id);
                response.setStatus(HttpServletResponse.SC_OK);
                answer = objectMapper.writeValueAsString(cardsResponse);
            } else if (uri.equals("/find/tickets-doctor/" + id)) {
                List<FindByTicketsResponseDto> ticketsResponse = findService.findTicketsByDoctorId(id);
                response.setStatus(HttpServletResponse.SC_OK);
                answer = objectMapper.writeValueAsString(ticketsResponse);
            } else if (uri.equals("/find/tickets-patient/" + id)) {
                List<FindByTicketsResponseDto> ticketsResponse = findService.findTicketsByPatientId(id);
                response.setStatus(HttpServletResponse.SC_OK);
                answer = objectMapper.writeValueAsString(ticketsResponse);
            } else if (uri.equals("/find/doctors-and-cards-doctor/" + id)) {
                List<FindByDoctorsAndCardsResponseDto> doctorsAndCardsResponse = findService.findDoctorsAndCardsByDoctorId(id);
                response.setStatus(HttpServletResponse.SC_OK);
                answer = objectMapper.writeValueAsString(doctorsAndCardsResponse);
            } else if (uri.equals("/find/doctors-and-cards-card/" + id)) {
                List<FindByDoctorsAndCardsResponseDto> doctorsAndCardsResponse = findService.findDoctorsAndCardsByCardId(id);
                response.setStatus(HttpServletResponse.SC_OK);
                answer = objectMapper.writeValueAsString(doctorsAndCardsResponse);
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
}
