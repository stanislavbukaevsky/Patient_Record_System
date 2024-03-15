package com.github.stanislavbukaevsky.patientrecordsystem.exception;

/**
 * Класс-исключение для ошибок dao. <br>
 * Наследуется от класса {@link RuntimeException}
 */
public class DaoNotCompletedException extends RuntimeException {
    public DaoNotCompletedException(String message) {
        super(message);
    }
}
