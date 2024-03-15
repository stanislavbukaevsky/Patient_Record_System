package com.github.stanislavbukaevsky.patientrecordsystem.exception;

/**
 * Класс-исключение для подключения к базе данных. <br>
 * Наследуется от класса {@link RuntimeException}
 */
public class ConnectionNotFoundException extends RuntimeException {
    public ConnectionNotFoundException(String message) {
        super(message);
    }
}
