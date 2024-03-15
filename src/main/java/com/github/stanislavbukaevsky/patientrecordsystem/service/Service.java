package com.github.stanislavbukaevsky.patientrecordsystem.service;

import java.util.List;

/**
 * Обобщенный интерфейс для бизнес-логики
 *
 * @param <T>  запроса пользователя на сохранение
 * @param <U>  запроса пользователя на изменение
 * @param <K>  ответа пользователю
 * @param <ID> идентификатор
 */
public interface Service<T, U, K, ID> {
    K save(T t);

    K update(U u);

    K findById(ID id);

    List<K> findAll();

    String delete(ID id);
}
