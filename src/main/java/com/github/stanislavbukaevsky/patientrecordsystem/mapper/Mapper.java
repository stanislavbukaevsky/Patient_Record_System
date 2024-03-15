package com.github.stanislavbukaevsky.patientrecordsystem.mapper;

import java.util.List;

/**
 * Обобщенный интерфейс для маппера
 *
 * @param <T> запроса пользователя на сохранение
 * @param <U> запроса пользователя на изменение
 * @param <K> ответа пользователю
 * @param <Q> модель
 */
public interface Mapper<T, U, K, Q> {
    Q mappingToEntity(T t);

    Q mappingByUpdateToEntity(U u);

    K mappingToDto(Q q);

    List<K> mappingToListDto(List<Q> q);
}
