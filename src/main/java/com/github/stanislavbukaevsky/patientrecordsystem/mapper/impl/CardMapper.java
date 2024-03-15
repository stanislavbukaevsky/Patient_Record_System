package com.github.stanislavbukaevsky.patientrecordsystem.mapper.impl;

import com.github.stanislavbukaevsky.patientrecordsystem.dto.CardRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.CardRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.CardResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.Mapper;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс-маппер, который преобразует модель в dto и обратно.
 * Реализует интерфейс {@link Mapper}. Параметры: <br>
 * {@link CardRequestDto} - DTO для запроса пользователя на сохранение карты пациента <br>
 * {@link CardRequestUpdateDto} - DTO для запроса пользователя на изменение карты пациента <br>
 * {@link CardResponseDto} - DTO для ответа пользователю с информацией о карте пациента <br>
 * {@link Card} - модель карты пациента <br>
 */
public class CardMapper implements Mapper<CardRequestDto, CardRequestUpdateDto, CardResponseDto, Card> {
    private final static CardMapper INSTANCE = new CardMapper();

    private CardMapper() {
    }

    public static CardMapper getInstance() {
        return INSTANCE;
    }

    /**
     * Этот метод преобразует ответ пользователя из dto в модель
     *
     * @param cardRequest ответ пользователя
     * @return Возвращает модель карты пациента
     */
    @Override
    public Card mappingToEntity(CardRequestDto cardRequest) {
        if (cardRequest == null) {
            return null;
        }

        Card card = new Card();
        card.setAppointments(cardRequest.getAppointments());
        card.setAnalyzes(cardRequest.getAnalyzes());

        return card;
    }

    /**
     * Этот метод преобразует ответ пользователя из dto в модель
     *
     * @param cardRequest ответ пользователя
     * @return Возвращает модель карты пациента
     */
    @Override
    public Card mappingByUpdateToEntity(CardRequestUpdateDto cardRequest) {
        if (cardRequest == null) {
            return null;
        }

        Card card = new Card();
        card.setId(cardRequest.getId());
        card.setAppointments(cardRequest.getAppointments());
        card.setAnalyzes(cardRequest.getAnalyzes());

        return card;
    }

    /**
     * Этот метод преобразует модель карты пациента в dto
     *
     * @param card модель карты пациента
     * @return Возвращает ответ пользователю
     */
    @Override
    public CardResponseDto mappingToDto(Card card) {
        if (card == null) {
            return null;
        }

        CardResponseDto cardResponse = new CardResponseDto();
        cardResponse.setId(card.getId());
        cardResponse.setPatient(card.getPatient());
        cardResponse.setAppointments(card.getAppointments());
        cardResponse.setAnalyzes(card.getAnalyzes());

        return cardResponse;
    }

    /**
     * Этот метод преобразует список моделей карт пациента в dto
     *
     * @param cards список карт пациента
     * @return Возвращает список dto
     */
    @Override
    public List<CardResponseDto> mappingToListDto(List<Card> cards) {
        if (cards == null) {
            return null;
        }

        List<CardResponseDto> cardResponse = new ArrayList<>(cards.size());
        for (Card card : cards) {
            cardResponse.add(mappingToDto(card));
        }

        return cardResponse;
    }
}
