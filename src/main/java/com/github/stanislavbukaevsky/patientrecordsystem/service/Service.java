package com.github.stanislavbukaevsky.patientrecordsystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface Service<T, U, K, ID> {
    K save(T t) throws JsonProcessingException;

    K update(U u) throws JsonProcessingException;

    K findById(ID id) throws JsonProcessingException;

    List<K> findAll() throws JsonProcessingException;

    String delete(ID id);
}
