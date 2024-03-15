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

/**
 * Класс-сервис, с бизнес-логикой для пациента.
 * Реализует интерфейс {@link Service}. Параметры: <br>
 * {@link PatientRequestDto} - DTO для запроса пользователя на сохранение пациента <br>
 * {@link PatientRequestUpdateDto} - DTO для запроса пользователя на изменение пациента <br>
 * {@link PatientResponseDto} - DTO для ответа пользователю с информацией о пациенте <br>
 * {@link Long} - идентификатор
 */
public class PatientService implements Service<PatientRequestDto, PatientRequestUpdateDto, PatientResponseDto, Long> {
    private final static PatientService INSTANCE = new PatientService();
    private final Dao<Patient, Long> patientDao = PatientDao.getInstance();
    private final Mapper<PatientRequestDto, PatientRequestUpdateDto, PatientResponseDto, Patient> patientMapper = PatientMapper.getInstance();

    private PatientService() {
    }

    public static PatientService getInstance() {
        return INSTANCE;
    }

    /**
     * Этот метод сохраняет паиента в базу данных
     *
     * @param patientRequest ответ пользователя
     * @return Возвращает ответ пользователю
     */
    @Override
    public PatientResponseDto save(PatientRequestDto patientRequest) {
        if (patientRequest == null) {
            throw new PatientNotFoundException(PATIENT_NOT_FOUND_EXCEPTION_BY_SAVE_AND_UPDATE_MESSAGE);
        }

        Patient patient = patientMapper.mappingToEntity(patientRequest);
        Patient result = patientDao.save(patient);

        return patientMapper.mappingToDto(result);
    }

    /**
     * Этот метод изменяет паиента в базе данных
     *
     * @param patientRequest ответ пользователя
     * @return Возвращает ответ пользователю
     */
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

    /**
     * Этот метод ищет паиента в базе данных по его идентификатору
     *
     * @param id идентификатор пациента
     * @return Возвращает ответ пользователю
     */
    @Override
    public PatientResponseDto findById(Long id) {
        Patient patient = patientDao.findById(id).orElseThrow(() ->
                new PatientNotFoundException(PATIENT_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));

        return patientMapper.mappingToDto(patient);
    }

    /**
     * Этот метод ищет всех пациентов, сохраненных в базе данных
     *
     * @return Возвращает список dto
     */
    @Override
    public List<PatientResponseDto> findAll() {
        List<Patient> patients = patientDao.findAll();
        if (patients.isEmpty()) {
            throw new PatientNotFoundException(PATIENT_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        return patientMapper.mappingToListDto(patients);
    }

    /**
     * Этот метод удаляет паиента из базы данных
     *
     * @param id идентификатор пациента
     * @return Возвращает строку, если удаление прошло успешно
     */
    @Override
    public String delete(Long id) {
        if (id == null) {
            throw new PatientNotFoundException(PATIENT_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE);
        }

        return patientDao.delete(id);
    }
}
