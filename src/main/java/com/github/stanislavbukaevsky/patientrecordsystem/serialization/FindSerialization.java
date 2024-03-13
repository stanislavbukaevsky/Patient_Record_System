package com.github.stanislavbukaevsky.patientrecordsystem.serialization;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public interface FindSerialization<T, K> {
    void doGet(T t, K k) throws IOException;

    default void responseHeader(HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
    }
}
