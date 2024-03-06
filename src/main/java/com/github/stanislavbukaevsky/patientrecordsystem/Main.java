package com.github.stanislavbukaevsky.patientrecordsystem;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
//        String sqlTableDoctors = """
//                CREATE TABLE IF NOT EXISTS doctors (
//                    id BIGINT PRIMARY KEY,
//                    first_name VARCHAR(64) NOT NULL,
//                    middle_name VARCHAR(64) NOT NULL,
//                    last_name VARCHAR(64) NOT NULL,
//                    specialization VARCHAR(32) NOT NULL,
//                    office INTEGER NOT NULL
//                );
//                """;
//        String sqlTablePatients = """
//                CREATE TABLE IF NOT EXISTS patients (
//                    id BIGINT PRIMARY KEY,
//                    first_name VARCHAR(64) NOT NULL,
//                    middle_name VARCHAR(64) NOT NULL,
//                    last_name VARCHAR(64) NOT NULL,
//                    date_birth VARCHAR(16) NOT NULL
//                );
//                """;
//        String sqlTableTickets = """
//                CREATE TABLE IF NOT EXISTS tickets (
//                    id BIGINT PRIMARY KEY,
//                    doctor_id BIGINT NOT NULL REFERENCES doctors(id),
//                    patient_id BIGINT NOT NULL REFERENCES patients(id),
//                    date_admission TIMESTAMP NOT NULL default CURRENT_TIMESTAMP
//                );
//                """;
//        String sqlTableCards = """
//                CREATE TABLE IF NOT EXISTS cards (
//                    id BIGINT PRIMARY KEY,
//                    patient_id BIGINT NOT NULL REFERENCES patients(id),
//                    appointments VARCHAR(1000),
//                    analyzes VARCHAR(1000)
//                );
//                """;
//        try (Connection connection = ConnectionManager.getConnection();
//             Statement statement = connection.createStatement()) {
//            connection.getTransactionIsolation();
//            statement.execute(sqlTableDoctors);
//            statement.execute(sqlTablePatients);
//            statement.execute(sqlTableTickets);
//            statement.execute(sqlTableCards);
//            System.out.println(connection.getTransactionIsolation());
//            System.out.println(statement.execute(sqlTableDoctors));
//        }
    }
}
