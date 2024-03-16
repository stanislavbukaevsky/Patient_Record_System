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

/**
 * Класс-сервис, с бизнес-логикой для врача.
 * Реализует интерфейс {@link Service}. Параметры: <br>
 * {@link DoctorRequestDto} - DTO для запроса пользователя на сохранение врача <br>
 * {@link DoctorRequestUpdateDto} - DTO для запроса пользователя на изменение врача <br>
 * {@link DoctorResponseDto} - DTO для ответа пользователю с информацией о враче <br>
 * {@link Long} - идентификатор
 */
public class DoctorService implements Service<DoctorRequestDto, DoctorRequestUpdateDto, DoctorResponseDto, Long> {
    private final static DoctorService INSTANCE = new DoctorService();
    private final Dao<Doctor, Long> doctorDao;
    private final Mapper<DoctorRequestDto, DoctorRequestUpdateDto, DoctorResponseDto, Doctor> doctorMapper = DoctorMapper.getInstance();

    public DoctorService(Dao<Doctor, Long> doctorDao) {
        this.doctorDao = doctorDao;
    }

    private DoctorService() {
        this.doctorDao = DoctorDao.getInstance();
    }

    public static DoctorService getInstance() {
        return INSTANCE;
    }

    /**
     * Этот метод сохраняет врача в базу данных
     *
     * @param doctorRequest ответ пользователя
     * @return Возвращает ответ пользователю
     */
    @Override
    public DoctorResponseDto save(DoctorRequestDto doctorRequest) {
        if (doctorRequest == null) {
            throw new DoctorNotFoundException(DOCTOR_NOT_FOUND_EXCEPTION_BY_SAVE_AND_UPDATE_MESSAGE);
        }

        Doctor doctor = doctorMapper.mappingToEntity(doctorRequest);
        Doctor result = doctorDao.save(doctor);

        return doctorMapper.mappingToDto(result);
    }

    /**
     * Этот метод изменяет врача в базе данных
     *
     * @param doctorRequest ответ пользователя
     * @return Возвращает ответ пользователю
     */
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

    /**
     * Этот метод ищет врача в базе данных по его идентификатору
     *
     * @param id идентификатор врача
     * @return Возвращает ответ пользователю
     */
    @Override
    public DoctorResponseDto findById(Long id) {
        Doctor doctor = doctorDao.findById(id).orElseThrow(() ->
                new DoctorNotFoundException(DOCTOR_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));

        return doctorMapper.mappingToDto(doctor);
    }

    /**
     * Этот метод ищет всех врачей, сохраненных в базе данных
     *
     * @return Возвращает список dto
     */
    @Override
    public List<DoctorResponseDto> findAll() {
        List<Doctor> doctors = doctorDao.findAll();
        if (doctors.isEmpty()) {
            throw new DoctorNotFoundException(DOCTOR_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        return doctorMapper.mappingToListDto(doctors);
    }

    /**
     * Этот метод удаляет врача из базы данных
     *
     * @param id идентификатор врача
     * @return Возвращает строку, если удаление прошло успешно
     */
    @Override
    public String delete(Long id) {
        if (id == null) {
            throw new DoctorNotFoundException(DOCTOR_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE);
        }

        return doctorDao.delete(id);
    }
}
