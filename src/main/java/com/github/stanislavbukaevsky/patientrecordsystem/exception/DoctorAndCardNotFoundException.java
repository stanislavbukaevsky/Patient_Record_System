package com.github.stanislavbukaevsky.patientrecordsystem.exception;

/**
 * Класс-исключение для врача и карты пациента. <br>
 * Наследуется от класса {@link RuntimeException}
 */
public class DoctorAndCardNotFoundException extends RuntimeException {
    public DoctorAndCardNotFoundException(String message) {
        super(message);
    }
}
