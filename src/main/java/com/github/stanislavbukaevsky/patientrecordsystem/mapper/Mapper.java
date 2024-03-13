package com.github.stanislavbukaevsky.patientrecordsystem.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface Mapper<T, U, K, Q> {
    Q mappingToEntity(T t) throws JsonProcessingException;

    Q mappingByUpdateToEntity(U u) throws JsonProcessingException;

    K mappingToDto(Q q) throws JsonProcessingException;

    List<K> mappingToListDto(List<Q> q) throws JsonProcessingException;
}
