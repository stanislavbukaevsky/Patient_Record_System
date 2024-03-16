package com.github.stanislavbukaevsky.patientrecordsystem.constant;

import com.github.stanislavbukaevsky.patientrecordsystem.dto.FindByCardsResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.FindByDoctorsAndCardsResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.FindByTicketsResponseDto;

import java.util.List;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.CardConstantTest.*;
import static com.github.stanislavbukaevsky.patientrecordsystem.constant.DoctorAndCardConstantTest.ID_DOCTOR_AND_CARD_ONE;
import static com.github.stanislavbukaevsky.patientrecordsystem.constant.DoctorAndCardConstantTest.ID_DOCTOR_AND_CARD_TWO;
import static com.github.stanislavbukaevsky.patientrecordsystem.constant.DoctorConstantTest.DOCTOR_ONE;
import static com.github.stanislavbukaevsky.patientrecordsystem.constant.DoctorConstantTest.DOCTOR_TWO;
import static com.github.stanislavbukaevsky.patientrecordsystem.constant.PatientConstantTest.PATIENT_ONE;
import static com.github.stanislavbukaevsky.patientrecordsystem.constant.PatientConstantTest.PATIENT_TWO;
import static com.github.stanislavbukaevsky.patientrecordsystem.constant.TicketConstantTest.DATE_AND_TIME_STRING_ONE;
import static com.github.stanislavbukaevsky.patientrecordsystem.constant.TicketConstantTest.DATE_AND_TIME_STRING_TWO;

/**
 * Текстовые константные переменные для тестов поиска по внешнему ключу
 */
public class FindByForeignKeyConstantTest {
    public static final Long ID_FIND_BY_FOREIGN_KEY_ONE = 1L;
    public static final Long ID_FIND_BY_FOREIGN_KEY_TWO = 2L;
    public static final Long ID_FIND_BY_FOREIGN_KEY_NEGATIVE = 10L;
    public static final FindByCardsResponseDto FIND_BY_CARDS_RESPONSE_DTO_ONE = new FindByCardsResponseDto(
            ID_FIND_BY_FOREIGN_KEY_ONE,
            List.of(PATIENT_ONE, PATIENT_TWO),
            APPOINTMENTS_CARD_ONE,
            ANALYZES_CARD_ONE);
    public static final FindByCardsResponseDto FIND_BY_CARDS_RESPONSE_DTO_TWO = new FindByCardsResponseDto(
            ID_FIND_BY_FOREIGN_KEY_TWO,
            List.of(PATIENT_ONE, PATIENT_TWO),
            APPOINTMENTS_CARD_TWO,
            ANALYZES_CARD_TWO);

    public static final FindByTicketsResponseDto FIND_BY_TICKETS_RESPONSE_DTO_ONE = new FindByTicketsResponseDto(
            ID_FIND_BY_FOREIGN_KEY_ONE,
            List.of(DOCTOR_ONE, DOCTOR_TWO),
            List.of(PATIENT_ONE, PATIENT_TWO),
            DATE_AND_TIME_STRING_ONE);
    public static final FindByTicketsResponseDto FIND_BY_TICKETS_RESPONSE_DTO_TWO = new FindByTicketsResponseDto(
            ID_FIND_BY_FOREIGN_KEY_TWO,
            List.of(DOCTOR_ONE, DOCTOR_TWO),
            List.of(PATIENT_ONE, PATIENT_TWO),
            DATE_AND_TIME_STRING_TWO);
    public static final FindByDoctorsAndCardsResponseDto FIND_BY_DOCTORS_AND_CARDS_RESPONSE_DTO_ONE = new FindByDoctorsAndCardsResponseDto(
            ID_DOCTOR_AND_CARD_ONE,
            List.of(DOCTOR_ONE, DOCTOR_TWO),
            List.of(CARD_ONE, CARD_TWO));
    public static final FindByDoctorsAndCardsResponseDto FIND_BY_DOCTORS_AND_CARDS_RESPONSE_DTO_TWO = new FindByDoctorsAndCardsResponseDto(
            ID_DOCTOR_AND_CARD_TWO,
            List.of(DOCTOR_ONE, DOCTOR_TWO),
            List.of(CARD_ONE, CARD_TWO));
}
