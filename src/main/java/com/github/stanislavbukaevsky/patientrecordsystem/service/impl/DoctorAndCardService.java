package com.github.stanislavbukaevsky.patientrecordsystem.service.impl;

import com.github.stanislavbukaevsky.patientrecordsystem.dao.Dao;
import com.github.stanislavbukaevsky.patientrecordsystem.dao.impl.CardDao;
import com.github.stanislavbukaevsky.patientrecordsystem.dao.impl.DoctorAndCardDao;
import com.github.stanislavbukaevsky.patientrecordsystem.dao.impl.DoctorDao;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorAndCardRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorAndCardRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorAndCardResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.CardNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.DoctorAndCardNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.DoctorNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.Mapper;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.impl.DoctorAndCardMapper;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Card;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Doctor;
import com.github.stanislavbukaevsky.patientrecordsystem.model.DoctorAndCard;
import com.github.stanislavbukaevsky.patientrecordsystem.service.Service;

import java.util.List;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.ExceptionTextMessageConstant.*;

/**
 * Класс-сервис, с бизнес-логикой для врача и карты пациента.
 * Реализует интерфейс {@link Service}. Параметры: <br>
 * {@link DoctorAndCardRequestDto} - DTO для запроса пользователя на сохранение врача и карты пациента <br>
 * {@link DoctorAndCardRequestUpdateDto} - DTO для запроса пользователя на изменение врача и карты пациента <br>
 * {@link DoctorAndCardResponseDto} - DTO для ответа пользователю с информацией о враче и карте пациента <br>
 * {@link Long} - идентификатор
 */
public class DoctorAndCardService implements Service<DoctorAndCardRequestDto, DoctorAndCardRequestUpdateDto, DoctorAndCardResponseDto, Long> {
    private final static DoctorAndCardService INSTANCE = new DoctorAndCardService();
    private final Dao<DoctorAndCard, Long> doctorAndCardDao = DoctorAndCardDao.getInstance();
    private final Dao<Doctor, Long> doctorDao = DoctorDao.getInstance();
    private final Dao<Card, Long> cardDao = CardDao.getInstance();
    private final Mapper<DoctorAndCardRequestDto, DoctorAndCardRequestUpdateDto, DoctorAndCardResponseDto, DoctorAndCard> doctorAndCardMapper = DoctorAndCardMapper.getInstance();

    private DoctorAndCardService() {
    }

    public static DoctorAndCardService getInstance() {
        return INSTANCE;
    }

    /**
     * Этот метод сохраняет врача и карту паиента в базу данных
     *
     * @param doctorAndCardRequest ответ пользователя
     * @return Возвращает ответ пользователю
     */
    @Override
    public DoctorAndCardResponseDto save(DoctorAndCardRequestDto doctorAndCardRequest) {
        if (doctorAndCardRequest == null) {
            throw new DoctorAndCardNotFoundException(DOCTOR_AND_CARD_NOT_FOUND_EXCEPTION_BY_SAVE_AND_UPDATE_MESSAGE);
        }

        Doctor doctor = doctorDao.findById(doctorAndCardRequest.getDoctorId()).orElseThrow(() ->
                new DoctorNotFoundException(DOCTOR_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));
        Card card = cardDao.findById(doctorAndCardRequest.getCardId()).orElseThrow(() ->
                new CardNotFoundException(CARD_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));
        DoctorAndCard doctorAndCard = doctorAndCardMapper.mappingToEntity(doctorAndCardRequest);
        doctorAndCard.setDoctor(doctor);
        doctorAndCard.setCard(card);
        DoctorAndCard result = doctorAndCardDao.save(doctorAndCard);

        return doctorAndCardMapper.mappingToDto(result);
    }

    /**
     * Этот метод изменяет врача и карту паиента в базе данных
     *
     * @param doctorAndCardRequest ответ пользователя
     * @return Возвращает ответ пользователю
     */
    @Override
    public DoctorAndCardResponseDto update(DoctorAndCardRequestUpdateDto doctorAndCardRequest) {
        if (doctorAndCardRequest == null) {
            throw new DoctorAndCardNotFoundException(DOCTOR_AND_CARD_NOT_FOUND_EXCEPTION_BY_SAVE_AND_UPDATE_MESSAGE);
        }

        DoctorAndCard doctorAndCard = doctorAndCardDao.findById(doctorAndCardRequest.getId()).orElseThrow(() ->
                new DoctorAndCardNotFoundException(DOCTOR_AND_CARD_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));
        Doctor doctor = doctorDao.findById(doctorAndCardRequest.getDoctorId()).orElseThrow(() ->
                new DoctorNotFoundException(DOCTOR_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));
        Card card = cardDao.findById(doctorAndCardRequest.getCardId()).orElseThrow(() ->
                new CardNotFoundException(CARD_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));
        if (doctorAndCard != null) {
            DoctorAndCard mapping = doctorAndCardMapper.mappingByUpdateToEntity(doctorAndCardRequest);
            mapping.setDoctor(doctor);
            mapping.setCard(card);
            DoctorAndCard result = doctorAndCardDao.update(mapping);

            return doctorAndCardMapper.mappingToDto(result);
        }

        throw new DoctorAndCardNotFoundException(DOCTOR_AND_CARD_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE);
    }

    /**
     * Этот метод ищет врача и карту паиента в базе данных по его идентификатору
     *
     * @param id идентификатор врача и карты пациента
     * @return Возвращает ответ пользователю
     */
    @Override
    public DoctorAndCardResponseDto findById(Long id) {
        DoctorAndCard doctorAndCard = doctorAndCardDao.findById(id).orElseThrow(() ->
                new DoctorAndCardNotFoundException(DOCTOR_AND_CARD_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));

        return doctorAndCardMapper.mappingToDto(doctorAndCard);
    }

    /**
     * Этот метод ищет всех врачей и карты пациента, сохраненные в базе данных
     *
     * @return Возвращает список dto
     */
    @Override
    public List<DoctorAndCardResponseDto> findAll() {
        List<DoctorAndCard> doctorAndCards = doctorAndCardDao.findAll();
        if (doctorAndCards.isEmpty()) {
            throw new DoctorAndCardNotFoundException(DOCTOR_AND_CARD_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        return doctorAndCardMapper.mappingToListDto(doctorAndCards);
    }

    /**
     * Этот метод удаляет врача и карту паиента из базы данных
     *
     * @param id идентификатор врача и карты пациента
     * @return Возвращает строку, если удаление прошло успешно
     */
    @Override
    public String delete(Long id) {
        if (id == null) {
            throw new DoctorAndCardNotFoundException(DOCTOR_AND_CARD_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE);
        }

        return doctorAndCardDao.delete(id);
    }
}
