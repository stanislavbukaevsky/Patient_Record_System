package com.github.stanislavbukaevsky.patientrecordsystem.exception;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String message) {
        super(message);
    }
}
