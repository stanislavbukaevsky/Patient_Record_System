package com.github.stanislavbukaevsky.patientrecordsystem.util;

import com.github.stanislavbukaevsky.patientrecordsystem.exception.PropertiesNotLoadException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.ExceptionTextMessageConstant.PROPERTIES_NOT_LOAD_EXCEPTION_MESSAGE;

/**
 * Утилитный класс для получения настроек из конфигурационного файла
 */
public final class PropertiesConnection {
    private static final Properties PROPERTIES = new Properties();

    private PropertiesConnection() {
    }

    static {
        loadProperties();
    }

    /**
     * Этот метод получает настройки к базе данных по ключу
     *
     * @param key ключ для получения настроек
     * @return Возвращает строку с настройками к базе данных
     */
    public static String getProperties(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties() {
        try (InputStream inputStream = PropertiesConnection.class
                .getClassLoader()
                .getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new PropertiesNotLoadException(PROPERTIES_NOT_LOAD_EXCEPTION_MESSAGE + e);
        }
    }
}
