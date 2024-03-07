package com.github.stanislavbukaevsky.patientrecordsystem.util;

import com.github.stanislavbukaevsky.patientrecordsystem.exception.TableNotCreatedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.ExceptionTextMessageConstant.TABLE_NOT_CREATED_EXCEPTION_MESSAGE;

public final class CreateTablesManager {
    //    private final static CreateTablesManager INSTANCE = new CreateTablesManager();
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
    private final static String PATIENTS_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS patients (
                id BIGSERIAL PRIMARY KEY,
                first_name VARCHAR(64) NOT NULL,
                middle_name VARCHAR(64) NOT NULL,
                last_name VARCHAR(64) NOT NULL,
                date_birth VARCHAR(16) NOT NULL
            );
            """;
    private final static String TICKETS_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS tickets (
                id BIGSERIAL PRIMARY KEY,
                doctor_id BIGINT NOT NULL REFERENCES doctors(id),
                patient_id BIGINT NOT NULL REFERENCES patients(id),
                date_admission TIMESTAMP NOT NULL default CURRENT_TIMESTAMP
            );
            """;
    private final static String CARDS_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS cards (
                id BIGSERIAL PRIMARY KEY,
                patient_id BIGINT NOT NULL REFERENCES patients(id),
                appointments VARCHAR(1000),
                analyzes VARCHAR(1000)
            );
            """;

    private CreateTablesManager() {
    }

    static {
        initTables();
    }

//    public static CreateTablesManager getInstance() {
//        return INSTANCE;
//    }

    private static void initTables() {
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            connection.getTransactionIsolation();
            statement.execute(DOCTORS_TABLE_SQL);
            statement.execute(PATIENTS_TABLE_SQL);
            statement.execute(TICKETS_TABLE_SQL);
            statement.execute(CARDS_TABLE_SQL);
            System.out.println(connection.getTransactionIsolation());
            System.out.println(statement.execute(DOCTORS_TABLE_SQL));
        } catch (SQLException e) {
            throw new TableNotCreatedException(TABLE_NOT_CREATED_EXCEPTION_MESSAGE + e);
        }
    }
}
