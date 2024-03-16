package com.github.stanislavbukaevsky.patientrecordsystem.util;

import com.github.stanislavbukaevsky.patientrecordsystem.exception.TableNotCreatedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.ExceptionTextMessageConstant.TABLE_NOT_CREATED_EXCEPTION_MESSAGE;

/**
 * Утилитный класс для создания таблиц в базе данных
 */
public final class CreateTablesManager {
    private final static CreateTablesManager INSTANCE = new CreateTablesManager();
    private final static String PATIENTS_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS patients (
                id BIGSERIAL PRIMARY KEY,
                first_name VARCHAR(64) NOT NULL,
                middle_name VARCHAR(64) NOT NULL,
                last_name VARCHAR(64) NOT NULL,
                date_birth VARCHAR(16) NOT NULL
            );
            """;
    private final static String CARDS_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS cards (
                id BIGSERIAL PRIMARY KEY,
                patient_id BIGINT NOT NULL REFERENCES patients(id) ON DELETE CASCADE,
                appointments VARCHAR(1000),
                analyzes VARCHAR(1000)
            );
            """;
    private final static String DOCTORS_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS doctors (
                id BIGSERIAL PRIMARY KEY,
                first_name VARCHAR(64) NOT NULL,
                middle_name VARCHAR(64) NOT NULL,
                last_name VARCHAR(64) NOT NULL,
                specialization VARCHAR(32) NOT NULL,
                office INTEGER NOT NULL
            );
            """;
    private final static String TICKETS_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS tickets (
                id BIGSERIAL PRIMARY KEY,
                doctor_id BIGINT NOT NULL REFERENCES doctors(id) ON DELETE CASCADE,
                patient_id BIGINT NOT NULL REFERENCES patients(id) ON DELETE CASCADE,
                date_admission TIMESTAMP NOT NULL default CURRENT_TIMESTAMP
            );
            """;
    private final static String DOCTORS_AND_CARDS_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS doctors_and_cards (
                id BIGSERIAL PRIMARY KEY,
                doctor_id BIGINT NOT NULL REFERENCES doctors(id) ON DELETE CASCADE,
                card_id BIGINT NOT NULL REFERENCES cards(id) ON DELETE CASCADE
            );
            """;

    private CreateTablesManager() {
    }

    public static CreateTablesManager getInstance() {
        return INSTANCE;
    }

    /**
     * Этот метод инициализирует таблицы для базы данных
     */
    public void initTables() {
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(PATIENTS_TABLE_SQL);
            statement.execute(CARDS_TABLE_SQL);
            statement.execute(DOCTORS_TABLE_SQL);
            statement.execute(TICKETS_TABLE_SQL);
            statement.execute(DOCTORS_AND_CARDS_TABLE_SQL);
            System.out.println("Таблицы для базы данных созданы!");
        } catch (SQLException e) {
            throw new TableNotCreatedException(TABLE_NOT_CREATED_EXCEPTION_MESSAGE + e);
        }
    }
}
