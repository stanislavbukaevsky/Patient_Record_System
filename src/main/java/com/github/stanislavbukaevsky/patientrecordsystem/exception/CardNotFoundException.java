package com.github.stanislavbukaevsky.patientrecordsystem.exception;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(String message) {
        super(message);
    }
}
