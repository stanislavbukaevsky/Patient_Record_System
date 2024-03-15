package com.github.stanislavbukaevsky.patientrecordsystem.exception;

/**
 * Класс-исключение для талона. <br>
 * Наследуется от класса {@link RuntimeException}
 */
public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String message) {
        super(message);
    }
}
