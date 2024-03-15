package com.github.stanislavbukaevsky.patientrecordsystem.mapper.impl;

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

/**
 * Класс-маппер, который преобразует модель в dto и обратно.
 * Реализует интерфейс {@link Mapper}. Параметры: <br>
 * {@link TicketRequestDto} - DTO для запроса пользователя на сохранение талона <br>
 * {@link TicketRequestUpdateDto} - DTO для запроса пользователя на изменение талона <br>
 * {@link TicketResponseDto} - DTO для ответа пользователю с информацией о талоне <br>
 * {@link Ticket} - модель талона <br>
 */
public class TicketMapper implements Mapper<TicketRequestDto, TicketRequestUpdateDto, TicketResponseDto, Ticket> {
    private final static TicketMapper INSTANCE = new TicketMapper();
    private final Parser<LocalDateTime, String> parser = DateAndTimeParser.getInstance();

    private TicketMapper() {
    }

    public static TicketMapper getInstance() {
        return INSTANCE;
    }

    /**
     * Этот метод преобразует ответ пользователя из dto в модель
     *
     * @param ticketRequest ответ пользователя
     * @return Возвращает модель талона
     */
    @Override
    public Ticket mappingToEntity(TicketRequestDto ticketRequest) {
        if (ticketRequest == null) {
            return null;
        }

        Ticket ticket = new Ticket();
        ticket.setDateAdmission(parser.parseDateAndTime(ticketRequest.getDateAdmission()));

        return ticket;
    }

    /**
     * Этот метод преобразует ответ пользователя из dto в модель
     *
     * @param ticketRequest ответ пользователя
     * @return Возвращает модель талона
     */
    @Override
    public Ticket mappingByUpdateToEntity(TicketRequestUpdateDto ticketRequest) {
        if (ticketRequest == null) {
            return null;
        }

        Ticket ticket = new Ticket();
        ticket.setId(ticketRequest.getId());
        ticket.setDateAdmission(parser.parseDateAndTime(ticketRequest.getDateAdmission()));

        return ticket;
    }

    /**
     * Этот метод преобразует модель талона в dto
     *
     * @param ticket модель талона
     * @return Возвращает ответ пользователю
     */
    @Override
    public TicketResponseDto mappingToDto(Ticket ticket) {
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

    /**
     * Этот метод преобразует список моделей талона в dto
     *
     * @param tickets список талонов
     * @return Возвращает список dto
     */
    @Override
    public List<TicketResponseDto> mappingToListDto(List<Ticket> tickets) {
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
