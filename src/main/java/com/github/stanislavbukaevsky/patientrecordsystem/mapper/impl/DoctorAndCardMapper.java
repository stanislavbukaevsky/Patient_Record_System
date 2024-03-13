package com.github.stanislavbukaevsky.patientrecordsystem.mapper.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorAndCardRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorAndCardRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorAndCardResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.Mapper;
import com.github.stanislavbukaevsky.patientrecordsystem.model.DoctorAndCard;

import java.util.ArrayList;
import java.util.List;

public class DoctorAndCardMapper implements Mapper<DoctorAndCardRequestDto, DoctorAndCardRequestUpdateDto, DoctorAndCardResponseDto, DoctorAndCard> {
    private final static DoctorAndCardMapper INSTANCE = new DoctorAndCardMapper();

    private DoctorAndCardMapper() {
    }

    public static DoctorAndCardMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public DoctorAndCard mappingToEntity(DoctorAndCardRequestDto doctorAndCardRequest) throws JsonProcessingException {
        if (doctorAndCardRequest == null) {
            return null;
        }

        return new DoctorAndCard();
    }

    @Override
    public DoctorAndCard mappingByUpdateToEntity(DoctorAndCardRequestUpdateDto doctorAndCardRequest) throws JsonProcessingException {
        if (doctorAndCardRequest == null) {
            return null;
        }

        DoctorAndCard doctorAndCard = new DoctorAndCard();
        doctorAndCard.setId(doctorAndCardRequest.getId());

        return doctorAndCard;
    }

    @Override
    public DoctorAndCardResponseDto mappingToDto(DoctorAndCard doctorAndCard) throws JsonProcessingException {
        if (doctorAndCard == null) {
            return null;
        }

        DoctorAndCardResponseDto doctorAndCardResponse = new DoctorAndCardResponseDto();
        doctorAndCardResponse.setId(doctorAndCard.getId());
        doctorAndCardResponse.setDoctor(doctorAndCard.getDoctor());
        doctorAndCardResponse.setCard(doctorAndCard.getCard());

        return doctorAndCardResponse;
    }

    @Override
    public List<DoctorAndCardResponseDto> mappingToListDto(List<DoctorAndCard> doctorAndCards) throws JsonProcessingException {
        if (doctorAndCards == null) {
            return null;
        }

        List<DoctorAndCardResponseDto> doctorAndCardResponse = new ArrayList<>(doctorAndCards.size());
        for (DoctorAndCard doctorAndCard : doctorAndCards) {
            doctorAndCardResponse.add(mappingToDto(doctorAndCard));
        }

        return doctorAndCardResponse;
    }
}
