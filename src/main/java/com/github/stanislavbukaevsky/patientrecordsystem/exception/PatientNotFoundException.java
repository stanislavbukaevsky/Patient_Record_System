package com.github.stanislavbukaevsky.patientrecordsystem.exception;

/**
 * Класс-исключение для пациента. <br>
 * Наследуется от класса {@link RuntimeException}
 */
public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(String message) {
        super(message);
    }
}
