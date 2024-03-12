package com.github.stanislavbukaevsky.patientrecordsystem.exception;

public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(String message) {
        super(message);
    }
}
