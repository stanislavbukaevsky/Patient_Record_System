package com.github.stanislavbukaevsky.patientrecordsystem.service.impl;

import com.github.stanislavbukaevsky.patientrecordsystem.dao.Dao;
import com.github.stanislavbukaevsky.patientrecordsystem.dao.impl.CardDao;
import com.github.stanislavbukaevsky.patientrecordsystem.dao.impl.PatientDao;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.CardRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.CardRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.CardResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.CardNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.PatientNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.Mapper;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.impl.CardMapper;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Card;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Patient;
import com.github.stanislavbukaevsky.patientrecordsystem.service.Service;

import java.util.List;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.ExceptionTextMessageConstant.*;

public class CardService implements Service<CardRequestDto, CardRequestUpdateDto, CardResponseDto, Long> {
    private final static CardService INSTANCE = new CardService();
    private final Dao<Card, Long> cardDao = CardDao.getInstance();
    private final Dao<Patient, Long> patientDao = PatientDao.getInstance();
    private final Mapper<CardRequestDto, CardRequestUpdateDto, CardResponseDto, Card> cardMapper = CardMapper.getInstance();

    private CardService() {
    }

    public static CardService getInstance() {
        return INSTANCE;
    }

    @Override
    public CardResponseDto save(CardRequestDto cardRequest) {
        if (cardRequest == null) {
            throw new CardNotFoundException(CARD_NOT_FOUND_EXCEPTION_BY_SAVE_AND_UPDATE_MESSAGE);
        }
        Patient patient = patientDao.findById(cardRequest.getPatientId()).orElseThrow(() ->
                new PatientNotFoundException(PATIENT_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));
        Card card = cardMapper.mappingToEntity(cardRequest);
        card.setPatient(patient);
        Card result = cardDao.save(card);

        return cardMapper.mappingToDto(result);
    }

    @Override
    public CardResponseDto update(CardRequestUpdateDto cardRequest) {
        if (cardRequest == null) {
            throw new CardNotFoundException(CARD_NOT_FOUND_EXCEPTION_BY_SAVE_AND_UPDATE_MESSAGE);
        }
        Card card = cardDao.findById(cardRequest.getId()).orElseThrow(() ->
                new CardNotFoundException(CARD_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));
        Patient patient = patientDao.findById(cardRequest.getPatientId()).orElseThrow(() ->
                new PatientNotFoundException(PATIENT_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));
        if (card != null) {
            Card mapping = cardMapper.mappingByUpdateToEntity(cardRequest);
            mapping.setPatient(patient);
            Card result = cardDao.update(mapping);

            return cardMapper.mappingToDto(result);
        }

        throw new CardNotFoundException(CARD_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE);
    }

    @Override
    public CardResponseDto findById(Long id) {
        Card card = cardDao.findById(id).orElseThrow(() ->
                new CardNotFoundException(CARD_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));

        return cardMapper.mappingToDto(card);
    }

    @Override
    public List<CardResponseDto> findAll() {
        List<Card> cards = cardDao.findAll();
        if (cards.isEmpty()) {
            throw new CardNotFoundException(CARD_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        return cardMapper.mappingToListDto(cards);
    }

    @Override
    public String delete(Long id) {
        if (id == null) {
            throw new CardNotFoundException(CARD_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE);
        }

        return cardDao.delete(id);
    }
}
