package com.github.stanislavbukaevsky.patientrecordsystem.mapper.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.TicketRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.TicketRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.TicketResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.Mapper;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Ticket;
import com.github.stanislavbukaevsky.patientrecordsystem.parser.Parser;
import com.github.stanislavbukaevsky.patientrecordsystem.parser.impl.DateAndTimeParser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketMapper implements Mapper<TicketRequestDto, TicketRequestUpdateDto, TicketResponseDto, Ticket> {
    private final static TicketMapper INSTANCE = new TicketMapper();
    private final Parser<LocalDateTime, String> parser = DateAndTimeParser.getInstance();

    private TicketMapper() {
    }

    public static TicketMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Ticket mappingToEntity(TicketRequestDto ticketRequest) throws JsonProcessingException {
        if (ticketRequest == null) {
            return null;
        }

        Ticket ticket = new Ticket();
        ticket.setDateAdmission(parser.parseDateAndTime(ticketRequest.getDateAdmission()));

        return ticket;
    }

    @Override
    public Ticket mappingByUpdateToEntity(TicketRequestUpdateDto ticketRequest) throws JsonProcessingException {
        if (ticketRequest == null) {
            return null;
        }

        Ticket ticket = new Ticket();
        ticket.setId(ticketRequest.getId());
        ticket.setDateAdmission(parser.parseDateAndTime(ticketRequest.getDateAdmission()));

        return ticket;
    }

    @Override
    public TicketResponseDto mappingToDto(Ticket ticket) throws JsonProcessingException {
        if (ticket == null) {
            return null;
        }

        TicketResponseDto ticketResponse = new TicketResponseDto();
        ticketResponse.setId(ticket.getId());
        ticketResponse.setDoctor(ticket.getDoctor());
        ticketResponse.setPatient(ticket.getPatient());
        ticketResponse.setDateAdmission(parser.parseString(ticket.getDateAdmission()));

        return ticketResponse;
    }

    @Override
    public List<TicketResponseDto> mappingToListDto(List<Ticket> tickets) throws JsonProcessingException {
        if (tickets == null) {
            return null;
        }

        List<TicketResponseDto> ticketResponse = new ArrayList<>(tickets.size());
        for (Ticket ticket : tickets) {
            ticketResponse.add(mappingToDto(ticket));
        }

        return ticketResponse;
    }
}
