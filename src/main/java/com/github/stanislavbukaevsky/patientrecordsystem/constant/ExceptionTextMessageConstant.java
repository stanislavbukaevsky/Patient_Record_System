package com.github.stanislavbukaevsky.patientrecordsystem.constant;

/**
 * Этот класс содержит текстовые константные переменные для всех исключений в приложении
 */
public class ExceptionTextMessageConstant {
    public static final String CONNECTION_NOT_FOUND_EXCEPTION_MESSAGE = "Не удалось подключиться к базе данных! ";
    public static final String PROPERTIES_NOT_LOAD_EXCEPTION_MESSAGE = "Не удалось загрузить свойства для базы данных! ";
    public static final String TABLE_NOT_CREATED_EXCEPTION_MESSAGE = "Не удалось создать таблицы для базы данных! ";
    public static final String DAO_NOT_COMPLETED_EXCEPTION_MESSAGE = "Не удалось завершить операцию с базой данных! ";
    public static final String DRIVER_NOT_FOUND_EXCEPTION_MESSAGE = "Не удалось загрузить драйвер для базы данных! ";
    public static final String PATIENT_NOT_FOUND_EXCEPTION_MESSAGE = "Не найдено ни одного пациента в базе данных!";
    public static final String PATIENT_NOT_FOUND_EXCEPTION_BY_SAVE_AND_UPDATE_MESSAGE = "Ваш запрос на сохранение или изменение пациента содержит пустые поля! Заполните все поля для удачного завершения операции";
    public static final String PATIENT_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE = "Пациента с таким идентификатором нет в базе данных! Попробуйте ввести другой идентификатор";
    public static final String DOCTOR_NOT_FOUND_EXCEPTION_MESSAGE = "Не найдено ни одного врача в базе данных!";
    public static final String DOCTOR_NOT_FOUND_EXCEPTION_BY_SAVE_AND_UPDATE_MESSAGE = "Ваш запрос на сохранение или изменение врача содержит пустые поля! Заполните все поля для удачного завершения операции";
    public static final String DOCTOR_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE = "Врача с таким идентификатором нет в базе данных! Попробуйте ввести другой идентификатор";
    public static final String CARD_NOT_FOUND_EXCEPTION_MESSAGE = "Не найдено ни одной карты пациента в базе данных!";
    public static final String CARD_NOT_FOUND_EXCEPTION_BY_SAVE_AND_UPDATE_MESSAGE = "Ваш запрос на сохранение или изменение карты пациента содержит пустые поля! Заполните все поля для удачного завершения операции";
    public static final String CARD_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE = "Карты пациента с таким идентификатором нет в базе данных! Попробуйте ввести другой идентификатор";
    public static final String TICKET_NOT_FOUND_EXCEPTION_MESSAGE = "Не найдено ни одного талона в базе данных!";
    public static final String TICKET_NOT_FOUND_EXCEPTION_BY_SAVE_AND_UPDATE_MESSAGE = "Ваш запрос на сохранение или изменение талона содержит пустые поля! Заполните все поля для удачного завершения операции";
    public static final String TICKET_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE = "Талона с таким идентификатором нет в базе данных! Попробуйте ввести другой идентификатор";
    public static final String DOCTOR_AND_CARD_NOT_FOUND_EXCEPTION_MESSAGE = "Не найдено ни одного врача и карты пациента в базе данных!";
    public static final String DOCTOR_AND_CARD_NOT_FOUND_EXCEPTION_BY_SAVE_AND_UPDATE_MESSAGE = "Ваш запрос на сохранение или изменение врача и карты пациента содержит пустые поля! Заполните все поля для удачного завершения операции";
    public static final String DOCTOR_AND_CARD_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE = "Врача и карты пациента с таким идентификатором нет в базе данных! Попробуйте ввести другой идентификатор";
}
