package com.github.stanislavbukaevsky.patientrecordsystem.parser.impl;

import com.github.stanislavbukaevsky.patientrecordsystem.exception.DateAndTimeParserException;
import com.github.stanislavbukaevsky.patientrecordsystem.parser.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.ExceptionTextMessageConstant.DATE_AND_TIME_PARSER_EXCEPTION_MESSAGE;

/**
 * Класс-парсер, который преобразует дату и время в строку и обратно.
 * Реализует интерфейс {@link Parser}. Параметры: <br>
 * {@link LocalDateTime} - объект даты и времени <br>
 * {@link String} - строка
 */
public class DateAndTimeParser implements Parser<LocalDateTime, String> {
    private final static DateAndTimeParser INSTANCE = new DateAndTimeParser();
    private final static String PATTERN = "yyyy-MM-dd HH:mm";

    private DateAndTimeParser() {
    }

    public static DateAndTimeParser getInstance() {
        return INSTANCE;
    }

    /**
     * Этот метод преобразует дату и время в строку
     *
     * @param dateTime объект даты и времени
     * @return Возвращает дату и время в виде строки
     */
    @Override
    public String parseString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(PATTERN));
    }

    /**
     * Этот метод преобразует дату и время в виде строки в объект LocalDateTime
     *
     * @param dateAdmission дата и время в виде строки
     * @return Возвращает объект даты и времени
     */
    @Override
    public LocalDateTime parseDateAndTime(String dateAdmission) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
            return LocalDateTime.parse(dateAdmission, formatter);
        } catch (DateTimeParseException dat) {
            throw new DateAndTimeParserException(DATE_AND_TIME_PARSER_EXCEPTION_MESSAGE + dat);
        }
    }
}
