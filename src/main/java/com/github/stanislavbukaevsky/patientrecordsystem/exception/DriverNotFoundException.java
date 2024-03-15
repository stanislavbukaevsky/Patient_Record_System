package com.github.stanislavbukaevsky.patientrecordsystem.exception;

/**
 * Класс-исключение драйвера для базы данных. <br>
 * Наследуется от класса {@link RuntimeException}
 */
public class DriverNotFoundException extends RuntimeException {
    public DriverNotFoundException(String message) {
        super(message);
    }
}
