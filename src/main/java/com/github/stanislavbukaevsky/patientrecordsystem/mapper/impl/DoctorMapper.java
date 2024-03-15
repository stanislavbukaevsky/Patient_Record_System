package com.github.stanislavbukaevsky.patientrecordsystem.mapper.impl;

import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.Mapper;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Doctor;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс-маппер, который преобразует модель в dto и обратно.
 * Реализует интерфейс {@link Mapper}. Параметры: <br>
 * {@link DoctorRequestDto} - DTO для запроса пользователя на сохранение врача <br>
 * {@link DoctorRequestUpdateDto} - DTO для запроса пользователя на изменение врача <br>
 * {@link DoctorResponseDto} - DTO для ответа пользователю с информацией о враче <br>
 * {@link Doctor} - модель врача <br>
 */
public class DoctorMapper implements Mapper<DoctorRequestDto, DoctorRequestUpdateDto, DoctorResponseDto, Doctor> {
    private final static DoctorMapper INSTANCE = new DoctorMapper();

    private DoctorMapper() {
    }

    public static DoctorMapper getInstance() {
        return INSTANCE;
    }

    /**
     * Этот метод преобразует ответ пользователя из dto в модель
     *
     * @param doctorRequest ответ пользователя
     * @return Возвращает модель врача
     */
    @Override
    public Doctor mappingToEntity(DoctorRequestDto doctorRequest) {
        if (doctorRequest == null) {
            return null;
        }

        Doctor doctor = new Doctor();
        doctor.setFirstName(doctorRequest.getFirstName());
        doctor.setMiddleName(doctorRequest.getMiddleName());
        doctor.setLastName(doctorRequest.getLastName());
        doctor.setSpecialization(doctorRequest.getSpecialization());
        doctor.setOffice(doctorRequest.getOffice());

        return doctor;
    }

    /**
     * Этот метод преобразует ответ пользователя из dto в модель
     *
     * @param doctorRequest ответ пользователя
     * @return Возвращает модель врача
     */
    @Override
    public Doctor mappingByUpdateToEntity(DoctorRequestUpdateDto doctorRequest) {
        if (doctorRequest == null) {
            return null;
        }

        Doctor doctor = new Doctor();
        doctor.setId(doctorRequest.getId());
        doctor.setFirstName(doctorRequest.getFirstName());
        doctor.setMiddleName(doctorRequest.getMiddleName());
        doctor.setLastName(doctorRequest.getLastName());
        doctor.setSpecialization(doctorRequest.getSpecialization());
        doctor.setOffice(doctorRequest.getOffice());

        return doctor;
    }

    /**
     * Этот метод преобразует модель врача в dto
     *
     * @param doctor модель врача
     * @return Возвращает ответ пользователю
     */
    @Override
    public DoctorResponseDto mappingToDto(Doctor doctor) {
        if (doctor == null) {
            return null;
        }

        DoctorResponseDto doctorResponse = new DoctorResponseDto();
        doctorResponse.setId(doctor.getId());
        doctorResponse.setFirstName(doctor.getFirstName());
        doctorResponse.setMiddleName(doctor.getMiddleName());
        doctorResponse.setLastName(doctor.getLastName());
        doctorResponse.setSpecialization(doctor.getSpecialization());
        doctorResponse.setOffice(doctor.getOffice());

        return doctorResponse;
    }

    /**
     * Этот метод преобразует список моделей врача в dto
     *
     * @param doctors список врачей
     * @return Возвращает список dto
     */
    @Override
    public List<DoctorResponseDto> mappingToListDto(List<Doctor> doctors) {
        if (doctors == null) {
            return null;
        }

        List<DoctorResponseDto> doctorResponse = new ArrayList<>(doctors.size());
        for (Doctor doctor : doctors) {
            doctorResponse.add(mappingToDto(doctor));
        }

        return doctorResponse;
    }
}
