package com.github.stanislavbukaevsky.patientrecordsystem.serialization;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Обобщенный интерфейс для сериализации
 *
 * @param <T> объект с запросом пользователя
 * @param <K> объект с ответом пользователю
 * @param <S> объект заголовка запроса
 */
public interface Serialization<T, K, S> {
    void doPost(T t, K k, S s) throws IOException;

    void doPut(T t, K k, S s) throws IOException;

    void doGet(T t, K k) throws IOException;

    void doDelete(T t, K k) throws IOException;

    /**
     * Дефолтный метод, который формирует заголовок ответа
     *
     * @param response ответ пользователю
     */
    default void responseHeader(HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
    }

    /**
     * Дефолтный метод, который формирует из пользовательского запроса строку
     *
     * @param request запрос пользователя
     * @return Возвращает запрос от пользователя в виде строки
     * @throws IOException исключение ввода/вывода
     */
    default String requestHeader(HttpServletRequest request) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = request.getReader();
        String textString;

        while ((textString = reader.readLine()) != null) {
            builder.append(textString);
        }

        return builder.toString();
    }
}
