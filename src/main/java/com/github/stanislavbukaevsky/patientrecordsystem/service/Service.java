package com.github.stanislavbukaevsky.patientrecordsystem.service;

import java.util.List;

public interface Service<T, U, K, ID> {
    K save(T t);

    K update(U u);

    K findById(ID id);

    List<K> findAll();

    String delete(ID id);
}
