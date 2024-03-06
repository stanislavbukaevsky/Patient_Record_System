package com.github.stanislavbukaevsky.patientrecordsystem.exception;

public class DaoNotCompletedException extends RuntimeException {
    public DaoNotCompletedException(String message) {
        super(message);
    }
}
