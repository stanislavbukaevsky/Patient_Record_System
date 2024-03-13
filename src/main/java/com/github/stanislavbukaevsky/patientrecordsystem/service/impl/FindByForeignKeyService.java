package com.github.stanislavbukaevsky.patientrecordsystem.service.impl;

import com.github.stanislavbukaevsky.patientrecordsystem.dao.FindDao;
import com.github.stanislavbukaevsky.patientrecordsystem.dao.impl.FindByForeignKeyDao;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.FindByCardsResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.FindByDoctorsAndCardsResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.FindByTicketsResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.CardNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.DoctorAndCardNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.TicketNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.FindMapper;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.impl.FindByForeignKeyMapper;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Card;
import com.github.stanislavbukaevsky.patientrecordsystem.model.DoctorAndCard;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Ticket;
import com.github.stanislavbukaevsky.patientrecordsystem.service.FindService;

import java.util.List;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.ExceptionTextMessageConstant.*;

public class FindByForeignKeyService implements FindService {
    private final static FindByForeignKeyService INSTANCE = new FindByForeignKeyService();
    private final FindDao findDao = FindByForeignKeyDao.getInstance();
    private final FindMapper findMapper = FindByForeignKeyMapper.getInstance();

    private FindByForeignKeyService() {
    }

    public static FindByForeignKeyService getInstance() {
        return INSTANCE;
    }

    public List<FindByCardsResponseDto> findCardsByPatientId(Long patientId) {
        List<Card> cards = findDao.findCardsByPatientId(patientId);
        if (cards.isEmpty()) {
            throw new CardNotFoundException(CARD_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        return findMapper.mappingForFindCardsByPatientId(cards);
    }

    @Override
    public List<FindByTicketsResponseDto> findTicketsByDoctorId(Long doctorId) {
        List<Ticket> tickets = findDao.findTicketsByDoctorId(doctorId);
        if (tickets.isEmpty()) {
            throw new TicketNotFoundException(TICKET_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        return findMapper.mappingForFindTicketsByForeignKey(tickets);
    }

    @Override
    public List<FindByTicketsResponseDto> findTicketsByPatientId(Long patientId) {
        List<Ticket> tickets = findDao.findTicketsByPatientId(patientId);
        if (tickets.isEmpty()) {
            throw new TicketNotFoundException(TICKET_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        return findMapper.mappingForFindTicketsByForeignKey(tickets);
    }

    @Override
    public List<FindByDoctorsAndCardsResponseDto> findDoctorsAndCardsByDoctorId(Long doctorId) {
        List<DoctorAndCard> doctorsAndCards = findDao.findDoctorsAndCardsByDoctorId(doctorId);
        if (doctorsAndCards.isEmpty()) {
            throw new DoctorAndCardNotFoundException(DOCTOR_AND_CARD_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        return findMapper.mappingForFindDoctorsAndCardsByForeignKey(doctorsAndCards);
    }

    @Override
    public List<FindByDoctorsAndCardsResponseDto> findDoctorsAndCardsByCardId(Long cardId) {
        List<DoctorAndCard> doctorsAndCards = findDao.findDoctorsAndCardsByCardId(cardId);
        if (doctorsAndCards.isEmpty()) {
            throw new DoctorAndCardNotFoundException(DOCTOR_AND_CARD_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        return findMapper.mappingForFindDoctorsAndCardsByForeignKey(doctorsAndCards);
    }
}
