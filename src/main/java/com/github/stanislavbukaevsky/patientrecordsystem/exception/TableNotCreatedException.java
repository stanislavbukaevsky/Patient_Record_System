package com.github.stanislavbukaevsky.patientrecordsystem.exception;

/**
 * Класс-исключение для ошибок создания таблиц в базе данных. <br>
 * Наследуется от класса {@link RuntimeException}
 */
public class TableNotCreatedException extends RuntimeException {
    public TableNotCreatedException(String message) {
        super(message);
    }
}
