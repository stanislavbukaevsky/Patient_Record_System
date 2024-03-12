package com.github.stanislavbukaevsky.patientrecordsystem.exception;

public class DoctorNotFoundException extends RuntimeException {
    public DoctorNotFoundException(String message) {
        super(message);
    }
}
