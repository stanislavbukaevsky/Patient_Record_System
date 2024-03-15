package com.github.stanislavbukaevsky.patientrecordsystem.mapper.impl;

import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorAndCardRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorAndCardRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorAndCardResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.Mapper;
import com.github.stanislavbukaevsky.patientrecordsystem.model.DoctorAndCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс-маппер, который преобразует модель в dto и обратно.
 * Реализует интерфейс {@link Mapper}. Параметры: <br>
 * {@link DoctorAndCardRequestDto} - DTO для запроса пользователя на сохранение врача и карты пациента <br>
 * {@link DoctorAndCardRequestUpdateDto} - DTO для запроса пользователя на изменение врача и карты пациента <br>
 * {@link DoctorAndCardResponseDto} - DTO для ответа пользователю с информацией о враче и карте пациента <br>
 * {@link DoctorAndCard} - модель врача и карты пациента <br>
 */
public class DoctorAndCardMapper implements Mapper<DoctorAndCardRequestDto, DoctorAndCardRequestUpdateDto, DoctorAndCardResponseDto, DoctorAndCard> {
    private final static DoctorAndCardMapper INSTANCE = new DoctorAndCardMapper();

    private DoctorAndCardMapper() {
    }

    public static DoctorAndCardMapper getInstance() {
        return INSTANCE;
    }

    /**
     * Этот метод преобразует ответ пользователя из dto в модель
     *
     * @param doctorAndCardRequest ответ пользователя
     * @return Возвращает модель врача и карты пациента
     */
    @Override
    public DoctorAndCard mappingToEntity(DoctorAndCardRequestDto doctorAndCardRequest) {
        if (doctorAndCardRequest == null) {
            return null;
        }

        return new DoctorAndCard();
    }

    /**
     * Этот метод преобразует ответ пользователя из dto в модель
     *
     * @param doctorAndCardRequest ответ пользователя
     * @return Возвращает модель врача и карты пациента
     */
    @Override
    public DoctorAndCard mappingByUpdateToEntity(DoctorAndCardRequestUpdateDto doctorAndCardRequest) {
        if (doctorAndCardRequest == null) {
            return null;
        }

        DoctorAndCard doctorAndCard = new DoctorAndCard();
        doctorAndCard.setId(doctorAndCardRequest.getId());

        return doctorAndCard;
    }

    /**
     * Этот метод преобразует модель врача и карты пациента в dto
     *
     * @param doctorAndCard модель врача и карты пациента
     * @return Возвращает ответ пользователю
     */
    @Override
    public DoctorAndCardResponseDto mappingToDto(DoctorAndCard doctorAndCard) {
        if (doctorAndCard == null) {
            return null;
        }

        DoctorAndCardResponseDto doctorAndCardResponse = new DoctorAndCardResponseDto();
        doctorAndCardResponse.setId(doctorAndCard.getId());
        doctorAndCardResponse.setDoctor(doctorAndCard.getDoctor());
        doctorAndCardResponse.setCard(doctorAndCard.getCard());

        return doctorAndCardResponse;
    }

    /**
     * Этот метод преобразует список моделей врачей и карт пациента в dto
     *
     * @param doctorAndCards список врачей и карт пациента
     * @return Возвращает список dto
     */
    @Override
    public List<DoctorAndCardResponseDto> mappingToListDto(List<DoctorAndCard> doctorAndCards) {
        if (doctorAndCards == null) {
            return null;
        }

        List<DoctorAndCardResponseDto> doctorAndCardResponse = new ArrayList<>(doctorAndCards.size());
        for (DoctorAndCard doctorAndCard : doctorAndCards) {
            doctorAndCardResponse.add(mappingToDto(doctorAndCard));
        }

        return doctorAndCardResponse;
    }
}
