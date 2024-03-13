package com.github.stanislavbukaevsky.patientrecordsystem.parser.impl;

import com.github.stanislavbukaevsky.patientrecordsystem.parser.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTimeParser implements Parser<LocalDateTime, String> {
    private final static DateAndTimeParser INSTANCE = new DateAndTimeParser();
    private final static String PATTERN = "yyyy-MM-dd HH:mm";

    private DateAndTimeParser() {
    }

    public static DateAndTimeParser getInstance() {
        return INSTANCE;
    }

    @Override
    public String parseString(LocalDateTime dateTime) {

        return dateTime.format(DateTimeFormatter.ofPattern(PATTERN));
    }

    @Override
    public LocalDateTime parseDateAndTime(String dateAdmission) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);

        return LocalDateTime.parse(dateAdmission, formatter);
    }
}
