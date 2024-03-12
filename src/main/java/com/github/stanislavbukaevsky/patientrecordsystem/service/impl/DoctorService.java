package com.github.stanislavbukaevsky.patientrecordsystem.service.impl;

import com.github.stanislavbukaevsky.patientrecordsystem.dao.Dao;
import com.github.stanislavbukaevsky.patientrecordsystem.dao.impl.DoctorDao;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.DoctorNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.Mapper;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.impl.DoctorMapper;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Doctor;
import com.github.stanislavbukaevsky.patientrecordsystem.service.Service;

import java.util.List;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.ExceptionTextMessageConstant.*;

public class DoctorService implements Service<DoctorRequestDto, DoctorRequestUpdateDto, DoctorResponseDto, Long> {
    private final static DoctorService INSTANCE = new DoctorService();
    private final Dao<Doctor, Long> doctorDao = DoctorDao.getInstance();
    private final Mapper<DoctorRequestDto, DoctorRequestUpdateDto, DoctorResponseDto, Doctor> doctorMapper = DoctorMapper.getInstance();

    private DoctorService() {
    }

    public static DoctorService getInstance() {
        return INSTANCE;
    }

    @Override
    public DoctorResponseDto save(DoctorRequestDto doctorRequest) {
        if (doctorRequest == null) {
            throw new DoctorNotFoundException(DOCTOR_NOT_FOUND_EXCEPTION_BY_SAVE_AND_UPDATE_MESSAGE);
        }
        Doctor doctor = doctorMapper.mappingToEntity(doctorRequest);
        Doctor result = doctorDao.save(doctor);

        return doctorMapper.mappingToDto(result);
    }

    @Override
    public DoctorResponseDto update(DoctorRequestUpdateDto doctorRequest) {
        if (doctorRequest == null) {
            throw new DoctorNotFoundException(DOCTOR_NOT_FOUND_EXCEPTION_BY_SAVE_AND_UPDATE_MESSAGE);
        }
        Doctor doctor = doctorDao.findById(doctorRequest.getId()).orElseThrow(() ->
                new DoctorNotFoundException(DOCTOR_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));
        if (doctor != null) {
            Doctor mapping = doctorMapper.mappingByUpdateToEntity(doctorRequest);
            Doctor result = doctorDao.update(mapping);

            return doctorMapper.mappingToDto(result);
        }

        throw new DoctorNotFoundException(DOCTOR_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE);
    }

    @Override
    public DoctorResponseDto findById(Long id) {
        Doctor doctor = doctorDao.findById(id).orElseThrow(() ->
                new DoctorNotFoundException(DOCTOR_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));

        return doctorMapper.mappingToDto(doctor);
    }

    @Override
    public List<DoctorResponseDto> findAll() {
        List<Doctor> doctors = doctorDao.findAll();
        if (doctors.isEmpty()) {
            throw new DoctorNotFoundException(DOCTOR_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        return doctorMapper.mappingToListDto(doctors);
    }

    @Override
    public String delete(Long id) {
        if (id == null) {
            throw new DoctorNotFoundException(DOCTOR_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE);
        }

        return doctorDao.delete(id);
    }
}
