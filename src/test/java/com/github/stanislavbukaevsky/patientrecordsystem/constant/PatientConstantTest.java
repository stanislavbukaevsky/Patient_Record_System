package com.github.stanislavbukaevsky.patientrecordsystem.constant;

import com.github.stanislavbukaevsky.patientrecordsystem.dto.PatientRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.PatientRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.PatientResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Patient;

/**
 * Текстовые константные переменные для тестов пациента
 */
public class PatientConstantTest {
    public static final Long ID_PATIENT_ONE = 1L;
    public static final Long ID_PATIENT_TWO = 2L;
    public static final Long ID_PATIENT_NEGATIVE = 10L;
    public static final String FIRST_NAME_PATIENT_ONE = "Test first name patient 1";
    public static final String FIRST_NAME_PATIENT_TWO = "Test first name patient 2";
    public static final String MIDDLE_NAME_PATIENT_ONE = "Test middle name patient 1";
    public static final String MIDDLE_NAME_PATIENT_TWO = "Test middle name patient 2";
    public static final String LAST_NAME_PATIENT_ONE = "Test last name patient 1";
    public static final String LAST_NAME_PATIENT_TWO = "Test last name patient 2";
    public static final String DATE_BIRTH_PATIENT_ONE = "Date birth 1";
    public static final String DATE_BIRTH_PATIENT_TWO = "Date birth 2";
    public static final Patient PATIENT_ONE = new Patient(
            ID_PATIENT_ONE,
            FIRST_NAME_PATIENT_ONE,
            MIDDLE_NAME_PATIENT_ONE,
            LAST_NAME_PATIENT_ONE,
            DATE_BIRTH_PATIENT_ONE);
    public static final Patient PATIENT_TWO = new Patient(
            ID_PATIENT_TWO,
            FIRST_NAME_PATIENT_TWO,
            MIDDLE_NAME_PATIENT_TWO,
            LAST_NAME_PATIENT_TWO,
            DATE_BIRTH_PATIENT_TWO);
    public static final PatientResponseDto PATIENT_RESPONSE_DTO_ONE = new PatientResponseDto(
            ID_PATIENT_ONE,
            FIRST_NAME_PATIENT_ONE,
            MIDDLE_NAME_PATIENT_ONE,
            LAST_NAME_PATIENT_ONE,
            DATE_BIRTH_PATIENT_ONE);
    public static final PatientResponseDto PATIENT_RESPONSE_DTO_TWO = new PatientResponseDto(
            ID_PATIENT_TWO,
            FIRST_NAME_PATIENT_TWO,
            MIDDLE_NAME_PATIENT_TWO,
            LAST_NAME_PATIENT_TWO,
            DATE_BIRTH_PATIENT_TWO);
    public static final PatientRequestDto PATIENT_REQUEST_DTO_ONE = new PatientRequestDto(
            FIRST_NAME_PATIENT_ONE,
            MIDDLE_NAME_PATIENT_ONE,
            LAST_NAME_PATIENT_ONE,
            DATE_BIRTH_PATIENT_ONE);
    public static final PatientRequestUpdateDto PATIENT_REQUEST_UPDATE_DTO = new PatientRequestUpdateDto(
            ID_PATIENT_ONE,
            FIRST_NAME_PATIENT_ONE,
            MIDDLE_NAME_PATIENT_ONE,
            LAST_NAME_PATIENT_ONE,
            DATE_BIRTH_PATIENT_ONE);
}
