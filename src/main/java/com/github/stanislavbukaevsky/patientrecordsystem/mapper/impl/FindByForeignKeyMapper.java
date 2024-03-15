package com.github.stanislavbukaevsky.patientrecordsystem.mapper.impl;

import com.github.stanislavbukaevsky.patientrecordsystem.dto.FindByCardsResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.FindByDoctorsAndCardsResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.FindByTicketsResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.FindMapper;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Card;
import com.github.stanislavbukaevsky.patientrecordsystem.model.DoctorAndCard;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Ticket;
import com.github.stanislavbukaevsky.patientrecordsystem.parser.Parser;
import com.github.stanislavbukaevsky.patientrecordsystem.parser.impl.DateAndTimeParser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс-маппер, который преобразует модель в dto и обратно.
 * Реализует интерфейс {@link FindMapper}.
 */
public class FindByForeignKeyMapper implements FindMapper {
    private final static FindByForeignKeyMapper INSTANCE = new FindByForeignKeyMapper();
    private final Parser<LocalDateTime, String> parser = DateAndTimeParser.getInstance();

    private FindByForeignKeyMapper() {
    }

    public static FindByForeignKeyMapper getInstance() {
        return INSTANCE;
    }

    /**
     * Этот метод преобразует список моделей карт пациента в dto
     *
     * @param cards список карт пациента
     * @return Возвращает список dto
     */
    @Override
    public List<FindByCardsResponseDto> mappingForFindCardsByPatientId(List<Card> cards) {
        if (cards == null) {
            return null;
        }

        List<FindByCardsResponseDto> cardsResponse = new ArrayList<>(cards.size());
        for (Card card : cards) {
            FindByCardsResponseDto cardResponse = new FindByCardsResponseDto();
            cardResponse.setId(card.getId());
            cardResponse.setPatients(Collections.singletonList(card.getPatient()));
            cardResponse.setAppointments(card.getAppointments());
            cardResponse.setAnalyzes(card.getAnalyzes());
            cardsResponse.add(cardResponse);
        }

        return cardsResponse;
    }

    /**
     * Этот метод преобразует список моделей талонов в dto
     *
     * @param tickets список талонов
     * @return Возвращает список dto
     */
    @Override
    public List<FindByTicketsResponseDto> mappingForFindTicketsByForeignKey(List<Ticket> tickets) {
        if (tickets == null) {
            return null;
        }

        List<FindByTicketsResponseDto> ticketsResponse = new ArrayList<>(tickets.size());
        for (Ticket ticket : tickets) {
            FindByTicketsResponseDto ticketResponse = new FindByTicketsResponseDto();
            ticketResponse.setId(ticket.getId());
            ticketResponse.setDoctors(Collections.singletonList(ticket.getDoctor()));
            ticketResponse.setPatients(Collections.singletonList(ticket.getPatient()));
            ticketResponse.setDateAdmission(parser.parseString(ticket.getDateAdmission()));
            ticketsResponse.add(ticketResponse);
        }

        return ticketsResponse;
    }

    /**
     * Этот метод преобразует список моделей врачей и карт пациента в dto
     *
     * @param doctorsAndCards список врачей и карт пациента
     * @return Возвращает список dto
     */
    @Override
    public List<FindByDoctorsAndCardsResponseDto> mappingForFindDoctorsAndCardsByForeignKey(List<DoctorAndCard> doctorsAndCards) {
        if (doctorsAndCards == null) {
            return null;
        }

        List<FindByDoctorsAndCardsResponseDto> doctorsAndCardsResponse = new ArrayList<>(doctorsAndCards.size());
        for (DoctorAndCard doctorAndCard : doctorsAndCards) {
            FindByDoctorsAndCardsResponseDto doctorAndCardResponse = new FindByDoctorsAndCardsResponseDto();
            doctorAndCardResponse.setId(doctorAndCard.getId());
            doctorAndCardResponse.setDoctors(Collections.singletonList(doctorAndCard.getDoctor()));
            doctorAndCardResponse.setCards(Collections.singletonList(doctorAndCard.getCard()));
            doctorsAndCardsResponse.add(doctorAndCardResponse);
        }

        return doctorsAndCardsResponse;
    }
}
