package com.github.stanislavbukaevsky.patientrecordsystem.service.impl;

import com.github.stanislavbukaevsky.patientrecordsystem.dao.Dao;
import com.github.stanislavbukaevsky.patientrecordsystem.dao.impl.PatientDao;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.PatientRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.PatientRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.PatientResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.PatientNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.Mapper;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.impl.PatientMapper;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Patient;
import com.github.stanislavbukaevsky.patientrecordsystem.service.Service;

import java.util.List;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.ExceptionTextMessageConstant.*;

public class PatientService implements Service<PatientRequestDto, PatientRequestUpdateDto, PatientResponseDto, Long> {
    private final static PatientService INSTANCE = new PatientService();
    private final Dao<Patient, Long> patientDao = PatientDao.getInstance();
    private final Mapper<PatientRequestDto, PatientRequestUpdateDto, PatientResponseDto, Patient> patientMapper = PatientMapper.getInstance();

    private PatientService() {
    }

    public static PatientService getInstance() {
        return INSTANCE;
    }

    @Override
    public PatientResponseDto save(PatientRequestDto patientRequest) {
        if (patientRequest == null) {
            throw new PatientNotFoundException(PATIENT_NOT_FOUND_EXCEPTION_BY_SAVE_AND_UPDATE_MESSAGE);
        }
        Patient patient = patientMapper.mappingToEntity(patientRequest);
        Patient result = patientDao.save(patient);

        return patientMapper.mappingToDto(result);
    }

    @Override
    public PatientResponseDto update(PatientRequestUpdateDto patientRequest) {
        if (patientRequest == null) {
            throw new PatientNotFoundException(PATIENT_NOT_FOUND_EXCEPTION_BY_SAVE_AND_UPDATE_MESSAGE);
        }
        Patient patient = patientDao.findById(patientRequest.getId()).orElseThrow(() ->
                new PatientNotFoundException(PATIENT_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));
        if (patient != null) {
            Patient mapping = patientMapper.mappingByUpdateToEntity(patientRequest);
            Patient result = patientDao.update(mapping);

            return patientMapper.mappingToDto(result);
        }

        throw new PatientNotFoundException(PATIENT_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE);
    }

    @Override
    public PatientResponseDto findById(Long id) {
        Patient patient = patientDao.findById(id).orElseThrow(() ->
                new PatientNotFoundException(PATIENT_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));

        return patientMapper.mappingToDto(patient);
    }

    @Override
    public List<PatientResponseDto> findAll() {
        List<Patient> patients = patientDao.findAll();
        if (patients.isEmpty()) {
            throw new PatientNotFoundException(PATIENT_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        return patientMapper.mappingToListDto(patients);
    }

    @Override
    public String delete(Long id) {
        if (id == null) {
            throw new PatientNotFoundException(PATIENT_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE);
        }

        return patientDao.delete(id);
    }
}
