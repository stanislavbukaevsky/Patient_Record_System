package com.github.stanislavbukaevsky.patientrecordsystem.exception;

public class ConnectionNotFoundException extends RuntimeException {
    public ConnectionNotFoundException(String message) {
        super(message);
    }
}
