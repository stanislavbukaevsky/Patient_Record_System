package com.github.stanislavbukaevsky.patientrecordsystem.constant;

import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Doctor;

/**
 * Текстовые константные переменные для тестов врача
 */
public class DoctorConstantTest {
    public static final Long ID_DOCTOR_ONE = 1L;
    public static final Long ID_DOCTOR_TWO = 2L;
    public static final Long ID_DOCTOR_NEGATIVE = 10L;
    public static final String FIRST_NAME_DOCTOR_ONE = "Test first name doctor 1";
    public static final String FIRST_NAME_DOCTOR_TWO = "Test first name doctor 2";
    public static final String MIDDLE_NAME_DOCTOR_ONE = "Test middle name doctor 1";
    public static final String MIDDLE_NAME_DOCTOR_TWO = "Test middle name doctor 2";
    public static final String LAST_NAME_DOCTOR_ONE = "Test last name doctor 1";
    public static final String LAST_NAME_DOCTOR_TWO = "Test last name doctor 2";
    public static final String SPECIALIZATION_DOCTOR_ONE = "Specialization 1";
    public static final String SPECIALIZATION_DOCTOR_TWO = "Specialization 2";
    public static final Integer OFFICE_TEST_DOCTOR_ONE = 20;
    public static final Integer OFFICE_TEST_DOCTOR_TWO = 30;
    public static final Doctor DOCTOR_ONE = new Doctor(
            ID_DOCTOR_ONE,
            FIRST_NAME_DOCTOR_ONE,
            MIDDLE_NAME_DOCTOR_ONE,
            LAST_NAME_DOCTOR_ONE,
            SPECIALIZATION_DOCTOR_ONE,
            OFFICE_TEST_DOCTOR_ONE);
    public static final Doctor DOCTOR_TWO = new Doctor(
            ID_DOCTOR_TWO,
            FIRST_NAME_DOCTOR_TWO,
            MIDDLE_NAME_DOCTOR_TWO,
            LAST_NAME_DOCTOR_TWO,
            SPECIALIZATION_DOCTOR_TWO,
            OFFICE_TEST_DOCTOR_TWO);
    public static final DoctorResponseDto DOCTOR_RESPONSE_DTO_ONE = new DoctorResponseDto(
            ID_DOCTOR_ONE,
            FIRST_NAME_DOCTOR_ONE,
            MIDDLE_NAME_DOCTOR_ONE,
            LAST_NAME_DOCTOR_ONE,
            SPECIALIZATION_DOCTOR_ONE,
            OFFICE_TEST_DOCTOR_ONE);
    public static final DoctorResponseDto DOCTOR_RESPONSE_DTO_TWO = new DoctorResponseDto(
            ID_DOCTOR_TWO,
            FIRST_NAME_DOCTOR_TWO,
            MIDDLE_NAME_DOCTOR_TWO,
            LAST_NAME_DOCTOR_TWO,
            SPECIALIZATION_DOCTOR_TWO,
            OFFICE_TEST_DOCTOR_TWO);
    public static final DoctorRequestDto DOCTOR_REQUEST_DTO_ONE = new DoctorRequestDto(
            FIRST_NAME_DOCTOR_ONE,
            MIDDLE_NAME_DOCTOR_ONE,
            LAST_NAME_DOCTOR_ONE,
            SPECIALIZATION_DOCTOR_ONE,
            OFFICE_TEST_DOCTOR_ONE);
    public static final DoctorRequestUpdateDto DOCTOR_REQUEST_UPDATE_DTO = new DoctorRequestUpdateDto(
            ID_DOCTOR_ONE,
            FIRST_NAME_DOCTOR_ONE,
            MIDDLE_NAME_DOCTOR_ONE,
            LAST_NAME_DOCTOR_ONE,
            SPECIALIZATION_DOCTOR_ONE,
            OFFICE_TEST_DOCTOR_ONE);
}
