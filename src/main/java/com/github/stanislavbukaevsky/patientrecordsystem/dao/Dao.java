package com.github.stanislavbukaevsky.patientrecordsystem.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T, ID> {
    T save(T t);

    T update(T t);

    Optional<T> findById(ID id);

    List<T> findAll();

    String delete(ID id);
}
