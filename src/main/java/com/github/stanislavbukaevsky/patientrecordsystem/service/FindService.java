package com.github.stanislavbukaevsky.patientrecordsystem.service;

import com.github.stanislavbukaevsky.patientrecordsystem.dto.FindByCardsResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.FindByDoctorsAndCardsResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.FindByTicketsResponseDto;

import java.util.List;

/**
 * Интерфейс для бизнес-логики
 */
public interface FindService {
    List<FindByCardsResponseDto> findCardsByPatientId(Long patientId);

    List<FindByTicketsResponseDto> findTicketsByDoctorId(Long doctorId);

    List<FindByTicketsResponseDto> findTicketsByPatientId(Long patientId);

    List<FindByDoctorsAndCardsResponseDto> findDoctorsAndCardsByDoctorId(Long doctorId);

    List<FindByDoctorsAndCardsResponseDto> findDoctorsAndCardsByCardId(Long cardId);
}
