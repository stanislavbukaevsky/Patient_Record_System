package com.github.stanislavbukaevsky.patientrecordsystem.mapper;

import java.util.List;

public interface Mapper<T, U, K, Q> {
    Q mappingToEntity(T t);

    Q mappingByUpdateToEntity(U u);

    K mappingToDto(Q q);

    List<K> mappingToListDto(List<Q> q);
}
