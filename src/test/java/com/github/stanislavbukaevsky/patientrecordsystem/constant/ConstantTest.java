package com.github.stanislavbukaevsky.patientrecordsystem.constant;

import com.github.stanislavbukaevsky.patientrecordsystem.dto.PatientRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.PatientResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Patient;

/**
 * Текстовые константные переменные для тестов
 */
public class ConstantTest {
    public static final Long ID_PATIENT_ONE = 1L;
    public static final String FIRST_NAME_PATIENT_ONE = "Test first name patient 1";
    public static final String MIDDLE_NAME_PATIENT_ONE = "Test middle name patient 1";
    public static final String LAST_NAME_PATIENT_ONE = "Test last name patient 1";
    public static final String DATE_BIRTH_PATIENT_ONE = "Date birth 1";
    public static final Patient PATIENT_ONE = new Patient(
            ID_PATIENT_ONE,
            FIRST_NAME_PATIENT_ONE,
            MIDDLE_NAME_PATIENT_ONE,
            LAST_NAME_PATIENT_ONE,
            DATE_BIRTH_PATIENT_ONE);
    public static final PatientResponseDto PATIENT_RESPONSE_DTO_ONE = new PatientResponseDto(
            ID_PATIENT_ONE,
            FIRST_NAME_PATIENT_ONE,
            MIDDLE_NAME_PATIENT_ONE,
            LAST_NAME_PATIENT_ONE,
            DATE_BIRTH_PATIENT_ONE);
    public static final PatientRequestDto PATIENT_REQUEST_DTO_ONE = new PatientRequestDto(
            FIRST_NAME_PATIENT_ONE,
            MIDDLE_NAME_PATIENT_ONE,
            LAST_NAME_PATIENT_ONE,
            DATE_BIRTH_PATIENT_ONE);
}
