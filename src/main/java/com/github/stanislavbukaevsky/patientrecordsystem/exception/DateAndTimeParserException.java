package com.github.stanislavbukaevsky.patientrecordsystem.exception;

/**
 * Класс-исключение для парсинга даты и времени. <br>
 * Наследуется от класса {@link RuntimeException}
 */
public class DateAndTimeParserException extends RuntimeException {
    public DateAndTimeParserException(String message) {
        super(message);
    }
}
