package com.github.stanislavbukaevsky.patientrecordsystem.serialization;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Обобщенный интерфейс для сериализации
 *
 * @param <T> объект с запросом пользователя
 * @param <K> объект с ответом пользователю
 */
public interface FindSerialization<T, K> {
    void doGet(T t, K k) throws IOException;

    /**
     * Дефолтный метод, который формирует заголовок ответа
     *
     * @param response ответ пользователю
     */
    default void responseHeader(HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
    }
}
