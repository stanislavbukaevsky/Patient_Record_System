package com.github.stanislavbukaevsky.patientrecordsystem.mapper.impl;

import com.github.stanislavbukaevsky.patientrecordsystem.dto.PatientRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.PatientRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.PatientResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.Mapper;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Patient;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс-маппер, который преобразует модель в dto и обратно.
 * Реализует интерфейс {@link Mapper}. Параметры: <br>
 * {@link PatientRequestDto} - DTO для запроса пользователя на сохранение пациента <br>
 * {@link PatientRequestUpdateDto} - DTO для запроса пользователя на изменение пациента <br>
 * {@link PatientResponseDto} - DTO для ответа пользователю с информацией о пациенте <br>
 * {@link Patient} - модель пациента <br>
 */
public class PatientMapper implements Mapper<PatientRequestDto, PatientRequestUpdateDto, PatientResponseDto, Patient> {
    private final static PatientMapper INSTANCE = new PatientMapper();

    private PatientMapper() {
    }

    public static PatientMapper getInstance() {
        return INSTANCE;
    }

    /**
     * Этот метод преобразует ответ пользователя из dto в модель
     *
     * @param patientRequest ответ пользователя
     * @return Возвращает модель пациента
     */
    @Override
    public Patient mappingToEntity(PatientRequestDto patientRequest) {
        if (patientRequest == null) {
            return null;
        }

        Patient patient = new Patient();
        patient.setFirstName(patientRequest.getFirstName());
        patient.setMiddleName(patientRequest.getMiddleName());
        patient.setLastName(patientRequest.getLastName());
        patient.setDateBirth(patientRequest.getDateBirth());

        return patient;
    }

    /**
     * Этот метод преобразует ответ пользователя из dto в модель
     *
     * @param patientRequest ответ пользователя
     * @return Возвращает модель пациента
     */
    @Override
    public Patient mappingByUpdateToEntity(PatientRequestUpdateDto patientRequest) {
        if (patientRequest == null) {
            return null;
        }

        Patient patient = new Patient();
        patient.setId(patientRequest.getId());
        patient.setFirstName(patientRequest.getFirstName());
        patient.setMiddleName(patientRequest.getMiddleName());
        patient.setLastName(patientRequest.getLastName());
        patient.setDateBirth(patientRequest.getDateBirth());

        return patient;
    }

    /**
     * Этот метод преобразует модель пациента в dto
     *
     * @param patient модель пациента
     * @return Возвращает ответ пользователю
     */
    @Override
    public PatientResponseDto mappingToDto(Patient patient) {
        if (patient == null) {
            return null;
        }

        PatientResponseDto patientResponse = new PatientResponseDto();
        patientResponse.setId(patient.getId());
        patientResponse.setFirstName(patient.getFirstName());
        patientResponse.setMiddleName(patient.getMiddleName());
        patientResponse.setLastName(patient.getLastName());
        patientResponse.setDateBirth(patient.getDateBirth());

        return patientResponse;
    }

    /**
     * Этот метод преобразует список моделей пациента в dto
     *
     * @param patients список пациентов
     * @return Возвращает список dto
     */
    @Override
    public List<PatientResponseDto> mappingToListDto(List<Patient> patients) {
        if (patients == null) {
            return null;
        }

        List<PatientResponseDto> patientResponse = new ArrayList<>(patients.size());
        for (Patient patient : patients) {
            patientResponse.add(mappingToDto(patient));
        }

        return patientResponse;
    }
}
