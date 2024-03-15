package com.github.stanislavbukaevsky.patientrecordsystem.dao;

import java.util.List;
import java.util.Optional;

/**
 * Обобщенный интерфейс для dao
 *
 * @param <T>  модель
 * @param <ID> идентификатор
 */
public interface Dao<T, ID> {
    T save(T t);

    T update(T t);

    Optional<T> findById(ID id);

    List<T> findAll();

    String delete(ID id);
}
