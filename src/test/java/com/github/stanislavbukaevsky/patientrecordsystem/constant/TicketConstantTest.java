package com.github.stanislavbukaevsky.patientrecordsystem.constant;

import com.github.stanislavbukaevsky.patientrecordsystem.dto.TicketRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.TicketRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.TicketResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Ticket;

import java.time.LocalDateTime;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.DoctorConstantTest.*;
import static com.github.stanislavbukaevsky.patientrecordsystem.constant.PatientConstantTest.*;

/**
 * Текстовые константные переменные для тестов талона
 */
public class TicketConstantTest {
    public static final Long ID_TICKET_ONE = 1L;
    public static final Long ID_TICKET_TWO = 2L;
    public static final Long ID_TICKET_NEGATIVE = 10L;
    public static final LocalDateTime DATE_AND_TIME_ONE = LocalDateTime.parse("2000-01-01T12:00");
    public static final LocalDateTime DATE_AND_TIME_TWO = LocalDateTime.parse("2001-01-01T12:00");
    public static final String DATE_AND_TIME_STRING_ONE = "2000-01-01 12:00";
    public static final String DATE_AND_TIME_STRING_TWO = "2001-01-01 12:00";
    public static final Ticket TICKET_ONE = new Ticket(
            ID_TICKET_ONE,
            DOCTOR_ONE,
            PATIENT_ONE,
            DATE_AND_TIME_ONE);
    public static final Ticket TICKET_TWO = new Ticket(
            ID_TICKET_TWO,
            DOCTOR_TWO,
            PATIENT_TWO,
            DATE_AND_TIME_TWO);
    public static final TicketResponseDto TICKET_RESPONSE_DTO_ONE = new TicketResponseDto(
            ID_TICKET_ONE,
            DOCTOR_ONE,
            PATIENT_ONE,
            DATE_AND_TIME_STRING_ONE);
    public static final TicketResponseDto TICKET_RESPONSE_DTO_TWO = new TicketResponseDto(
            ID_TICKET_TWO,
            DOCTOR_TWO,
            PATIENT_TWO,
            DATE_AND_TIME_STRING_TWO);
    public static final TicketRequestDto TICKET_REQUEST_DTO_ONE = new TicketRequestDto(
            ID_DOCTOR_ONE,
            ID_PATIENT_ONE,
            DATE_AND_TIME_STRING_ONE);
    public static final TicketRequestUpdateDto TICKET_REQUEST_UPDATE_DTO = new TicketRequestUpdateDto(
            ID_TICKET_ONE,
            ID_DOCTOR_ONE,
            ID_PATIENT_ONE,
            DATE_AND_TIME_STRING_ONE);
}
