package com.github.stanislavbukaevsky.patientrecordsystem.mapper.impl;

import com.github.stanislavbukaevsky.patientrecordsystem.dto.CardRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.CardRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.CardResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.Mapper;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Card;

import java.util.ArrayList;
import java.util.List;

public class CardMapper implements Mapper<CardRequestDto, CardRequestUpdateDto, CardResponseDto, Card> {
    private final static CardMapper INSTANCE = new CardMapper();

    private CardMapper() {
    }

    public static CardMapper getInstance() {
        return INSTANCE;
    }

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
