package com.github.stanislavbukaevsky.patientrecordsystem.exception;

/**
 * Класс-исключение для врача. <br>
 * Наследуется от класса {@link RuntimeException}
 */
public class DoctorNotFoundException extends RuntimeException {
    public DoctorNotFoundException(String message) {
        super(message);
    }
}
