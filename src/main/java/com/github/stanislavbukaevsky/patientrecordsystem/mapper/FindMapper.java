package com.github.stanislavbukaevsky.patientrecordsystem.mapper;

import com.github.stanislavbukaevsky.patientrecordsystem.dto.FindByCardsResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.FindByDoctorsAndCardsResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.FindByTicketsResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Card;
import com.github.stanislavbukaevsky.patientrecordsystem.model.DoctorAndCard;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Ticket;

import java.util.List;

public interface FindMapper {
    List<FindByCardsResponseDto> mappingForFindCardsByPatientId(List<Card> cards);

    List<FindByTicketsResponseDto> mappingForFindTicketsByForeignKey(List<Ticket> tickets);

    List<FindByDoctorsAndCardsResponseDto> mappingForFindDoctorsAndCardsByForeignKey(List<DoctorAndCard> doctorsAndCards);
}
