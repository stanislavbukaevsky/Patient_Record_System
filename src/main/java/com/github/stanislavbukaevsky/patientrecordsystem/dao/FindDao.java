package com.github.stanislavbukaevsky.patientrecordsystem.dao;

import com.github.stanislavbukaevsky.patientrecordsystem.model.Card;
import com.github.stanislavbukaevsky.patientrecordsystem.model.DoctorAndCard;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Ticket;

import java.util.List;

public interface FindDao {
    List<Card> findCardsByPatientId(Long patientId);

    List<Ticket> findTicketsByDoctorId(Long doctorId);

    List<Ticket> findTicketsByPatientId(Long patientId);

    List<DoctorAndCard> findDoctorsAndCardsByDoctorId(Long doctorId);

    List<DoctorAndCard> findDoctorsAndCardsByCardId(Long cardId);
}
