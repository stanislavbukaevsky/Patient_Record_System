package com.github.stanislavbukaevsky.patientrecordsystem.dao;

import com.github.stanislavbukaevsky.patientrecordsystem.exception.DaoNotCompletedException;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Doctor;
import com.github.stanislavbukaevsky.patientrecordsystem.util.ConnectionManager;

import java.sql.*;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.ExceptionTextMessageConstant.DAO_NOT_COMPLETED_EXCEPTION_MESSAGE;

public class DoctorDao {
    private final static DoctorDao INSTANCE = new DoctorDao();
    private final static String SAVE_SQL = """
            INSERT INTO doctors (first_name, middle_name, last_name, specialization, office)
            VALUES (?, ?, ?, ?, ?)
            """;

    private DoctorDao() {
    }

    public static DoctorDao getInstance() {
        return INSTANCE;
    }

    public Doctor save(Doctor doctor) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, doctor.getFirstName());
            statement.setString(2, doctor.getMiddleName());
            statement.setString(3, doctor.getLastName());
            statement.setString(4, doctor.getSpecialization());
            statement.setInt(5, doctor.getOffice());

            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                doctor.setId(keys.getLong("id"));
            }

            return doctor;
        } catch (SQLException e) {
            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
        }
    }
}
