package com.github.stanislavbukaevsky.patientrecordsystem.exception;

public class TableNotCreatedException extends RuntimeException {
    public TableNotCreatedException(String message) {
        super(message);
    }
}
