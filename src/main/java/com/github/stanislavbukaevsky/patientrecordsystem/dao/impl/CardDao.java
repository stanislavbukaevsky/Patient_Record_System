package com.github.stanislavbukaevsky.patientrecordsystem.dao.impl;

import com.github.stanislavbukaevsky.patientrecordsystem.dao.Dao;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Card;

import java.util.List;
import java.util.Optional;

public class CardDao implements Dao<Card, Long> {
    @Override
    public Card save(Card card) {
        return null;
    }

    @Override
    public Card update(Card card) {
        return null;
    }

    @Override
    public Optional<Card> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Card> findAll() {
        return null;
    }

    @Override
    public String delete(Long aLong) {
        return null;
    }
}
