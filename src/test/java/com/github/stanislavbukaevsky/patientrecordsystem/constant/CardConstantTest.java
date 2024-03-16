package com.github.stanislavbukaevsky.patientrecordsystem.constant;

import com.github.stanislavbukaevsky.patientrecordsystem.dto.CardRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.CardRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.CardResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Card;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.PatientConstantTest.*;

/**
 * Текстовые константные переменные для тестов карты пациента
 */
public class CardConstantTest {
    public static final Long ID_CARD_ONE = 1L;
    public static final Long ID_CARD_TWO = 2L;
    public static final Long ID_CARD_NEGATIVE = 10L;
    public static final String APPOINTMENTS_CARD_ONE = "Test appointments card 1";
    public static final String APPOINTMENTS_CARD_TWO = "Test appointments card 2";
    public static final String ANALYZES_CARD_ONE = "Test analyzes card 1";
    public static final String ANALYZES_CARD_TWO = "Test analyzes card 2";
    public static final Card CARD_ONE = new Card(
            ID_CARD_ONE,
            PATIENT_ONE,
            APPOINTMENTS_CARD_ONE,
            ANALYZES_CARD_ONE);
    public static final Card CARD_TWO = new Card(
            ID_CARD_TWO,
            PATIENT_TWO,
            APPOINTMENTS_CARD_TWO,
            ANALYZES_CARD_TWO);
    public static final CardResponseDto CARD_RESPONSE_DTO_ONE = new CardResponseDto(
            ID_CARD_ONE,
            PATIENT_ONE,
            APPOINTMENTS_CARD_ONE,
            ANALYZES_CARD_ONE);
    public static final CardResponseDto CARD_RESPONSE_DTO_TWO = new CardResponseDto(
            ID_CARD_TWO,
            PATIENT_TWO,
            APPOINTMENTS_CARD_TWO,
            ANALYZES_CARD_TWO);
    public static final CardRequestDto CARD_REQUEST_DTO_ONE = new CardRequestDto(
            ID_PATIENT_ONE,
            APPOINTMENTS_CARD_ONE,
            ANALYZES_CARD_ONE);
    public static final CardRequestUpdateDto CARD_REQUEST_UPDATE_DTO = new CardRequestUpdateDto(
            ID_CARD_ONE,
            ID_PATIENT_ONE,
            APPOINTMENTS_CARD_ONE,
            ANALYZES_CARD_ONE);
}
