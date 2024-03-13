package com.github.stanislavbukaevsky.patientrecordsystem.exception;

public class DoctorAndCardNotFoundException extends RuntimeException {
    public DoctorAndCardNotFoundException(String message) {
        super(message);
    }
}
