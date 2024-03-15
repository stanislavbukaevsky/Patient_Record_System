package com.github.stanislavbukaevsky.patientrecordsystem.exception;

/**
 * Класс-исключение для карты пациента. <br>
 * Наследуется от класса {@link RuntimeException}
 */
public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(String message) {
        super(message);
    }
}
