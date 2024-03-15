package com.github.stanislavbukaevsky.patientrecordsystem.exception;

/**
 * Класс-исключение для свойств базы данных. <br>
 * Наследуется от класса {@link RuntimeException}
 */
public class PropertiesNotLoadException extends RuntimeException {
    public PropertiesNotLoadException(String message) {
        super(message);
    }
}
