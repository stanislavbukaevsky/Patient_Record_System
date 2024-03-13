package com.github.stanislavbukaevsky.patientrecordsystem.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.stanislavbukaevsky.patientrecordsystem.dao.Dao;
import com.github.stanislavbukaevsky.patientrecordsystem.dao.impl.CardDao;
import com.github.stanislavbukaevsky.patientrecordsystem.dao.impl.DoctorAndCardDao;
import com.github.stanislavbukaevsky.patientrecordsystem.dao.impl.DoctorDao;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorAndCardRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorAndCardRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorAndCardResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.CardNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.DoctorAndCardNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.DoctorNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.Mapper;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.impl.DoctorAndCardMapper;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Card;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Doctor;
import com.github.stanislavbukaevsky.patientrecordsystem.model.DoctorAndCard;
import com.github.stanislavbukaevsky.patientrecordsystem.service.Service;

import java.util.List;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.ExceptionTextMessageConstant.*;

public class DoctorAndCardService implements Service<DoctorAndCardRequestDto, DoctorAndCardRequestUpdateDto, DoctorAndCardResponseDto, Long> {
    private final static DoctorAndCardService INSTANCE = new DoctorAndCardService();
    private final Dao<DoctorAndCard, Long> doctorAndCardDao = DoctorAndCardDao.getInstance();
    private final Dao<Doctor, Long> doctorDao = DoctorDao.getInstance();
    private final Dao<Card, Long> cardDao = CardDao.getInstance();
    private final Mapper<DoctorAndCardRequestDto, DoctorAndCardRequestUpdateDto, DoctorAndCardResponseDto, DoctorAndCard> doctorAndCardMapper = DoctorAndCardMapper.getInstance();

    private DoctorAndCardService() {
    }

    public static DoctorAndCardService getInstance() {
        return INSTANCE;
    }

    @Override
    public DoctorAndCardResponseDto save(DoctorAndCardRequestDto doctorAndCardRequest) throws JsonProcessingException {
        if (doctorAndCardRequest == null) {
            throw new DoctorAndCardNotFoundException(DOCTOR_AND_CARD_NOT_FOUND_EXCEPTION_BY_SAVE_AND_UPDATE_MESSAGE);
        }
        Doctor doctor = doctorDao.findById(doctorAndCardRequest.getDoctorId()).orElseThrow(() ->
                new DoctorNotFoundException(DOCTOR_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));
        Card card = cardDao.findById(doctorAndCardRequest.getCardId()).orElseThrow(() ->
                new CardNotFoundException(CARD_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));
        DoctorAndCard doctorAndCard = doctorAndCardMapper.mappingToEntity(doctorAndCardRequest);
        doctorAndCard.setDoctor(doctor);
        doctorAndCard.setCard(card);
        DoctorAndCard result = doctorAndCardDao.save(doctorAndCard);

        return doctorAndCardMapper.mappingToDto(result);
    }

    @Override
    public DoctorAndCardResponseDto update(DoctorAndCardRequestUpdateDto doctorAndCardRequest) throws JsonProcessingException {
        if (doctorAndCardRequest == null) {
            throw new DoctorAndCardNotFoundException(DOCTOR_AND_CARD_NOT_FOUND_EXCEPTION_BY_SAVE_AND_UPDATE_MESSAGE);
        }
        DoctorAndCard doctorAndCard = doctorAndCardDao.findById(doctorAndCardRequest.getId()).orElseThrow(() ->
                new DoctorAndCardNotFoundException(DOCTOR_AND_CARD_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));
        Doctor doctor = doctorDao.findById(doctorAndCardRequest.getDoctorId()).orElseThrow(() ->
                new DoctorNotFoundException(DOCTOR_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));
        Card card = cardDao.findById(doctorAndCardRequest.getCardId()).orElseThrow(() ->
                new CardNotFoundException(CARD_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));
        if (doctorAndCard != null) {
            DoctorAndCard mapping = doctorAndCardMapper.mappingByUpdateToEntity(doctorAndCardRequest);
            mapping.setDoctor(doctor);
            mapping.setCard(card);
            DoctorAndCard result = doctorAndCardDao.update(mapping);

            return doctorAndCardMapper.mappingToDto(result);
        }

        throw new DoctorAndCardNotFoundException(DOCTOR_AND_CARD_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE);
    }

    @Override
    public DoctorAndCardResponseDto findById(Long id) throws JsonProcessingException {
        DoctorAndCard doctorAndCard = doctorAndCardDao.findById(id).orElseThrow(() ->
                new DoctorAndCardNotFoundException(DOCTOR_AND_CARD_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));

        return doctorAndCardMapper.mappingToDto(doctorAndCard);
    }

    @Override
    public List<DoctorAndCardResponseDto> findAll() throws JsonProcessingException {
        List<DoctorAndCard> doctorAndCards = doctorAndCardDao.findAll();
        if (doctorAndCards.isEmpty()) {
            throw new DoctorAndCardNotFoundException(DOCTOR_AND_CARD_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        return doctorAndCardMapper.mappingToListDto(doctorAndCards);
    }

    @Override
    public String delete(Long id) {
        if (id == null) {
            throw new DoctorAndCardNotFoundException(DOCTOR_AND_CARD_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE);
        }

        return doctorAndCardDao.delete(id);
    }
}
