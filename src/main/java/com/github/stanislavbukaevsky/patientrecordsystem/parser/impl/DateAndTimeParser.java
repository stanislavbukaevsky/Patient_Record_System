package com.github.stanislavbukaevsky.patientrecordsystem.parser.impl;

import com.github.stanislavbukaevsky.patientrecordsystem.parser.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTimeParser implements Parser<LocalDateTime, String> {
    private final static DateAndTimeParser INSTANCE = new DateAndTimeParser();

    private DateAndTimeParser() {
    }

    public static DateAndTimeParser getInstance() {
        return INSTANCE;
    }

    @Override
    public LocalDateTime parseDateAndTime(String dateAdmission) {
        String pattern = "yyyy-MM-dd HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        return LocalDateTime.parse(dateAdmission, formatter);
    }
}
