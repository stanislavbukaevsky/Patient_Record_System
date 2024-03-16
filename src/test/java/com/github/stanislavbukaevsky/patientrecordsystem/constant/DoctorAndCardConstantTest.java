package com.github.stanislavbukaevsky.patientrecordsystem.constant;

import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorAndCardRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorAndCardRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorAndCardResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.model.DoctorAndCard;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.CardConstantTest.*;
import static com.github.stanislavbukaevsky.patientrecordsystem.constant.DoctorConstantTest.*;

/**
 * Текстовые константные переменные для тестов врача и карты пациента
 */
public class DoctorAndCardConstantTest {
    public static final Long ID_DOCTOR_AND_CARD_ONE = 1L;
    public static final Long ID_DOCTOR_AND_CARD_TWO = 2L;
    public static final Long ID_DOCTOR_AND_CARD_NEGATIVE = 10L;
    public static final DoctorAndCard DOCTOR_AND_CARD_ONE = new DoctorAndCard(
            ID_DOCTOR_AND_CARD_ONE,
            DOCTOR_ONE,
            CARD_ONE);
    public static final DoctorAndCard DOCTOR_AND_CARD_TWO = new DoctorAndCard(
            ID_DOCTOR_AND_CARD_TWO,
            DOCTOR_TWO,
            CARD_TWO);
    public static final DoctorAndCardResponseDto DOCTOR_AND_CARD_RESPONSE_DTO_ONE = new DoctorAndCardResponseDto(
            ID_DOCTOR_AND_CARD_ONE,
            DOCTOR_ONE,
            CARD_ONE);
    public static final DoctorAndCardResponseDto DOCTOR_AND_CARD_RESPONSE_DTO_TWO = new DoctorAndCardResponseDto(
            ID_DOCTOR_AND_CARD_TWO,
            DOCTOR_TWO,
            CARD_TWO);
    public static final DoctorAndCardRequestDto DOCTOR_AND_CARD_REQUEST_DTO_ONE = new DoctorAndCardRequestDto(
            ID_DOCTOR_ONE,
            ID_CARD_ONE);
    public static final DoctorAndCardRequestUpdateDto DOCTOR_AND_CARD_REQUEST_UPDATE_DTO = new DoctorAndCardRequestUpdateDto(
            ID_DOCTOR_AND_CARD_ONE,
            ID_DOCTOR_ONE,
            ID_CARD_ONE);
}
