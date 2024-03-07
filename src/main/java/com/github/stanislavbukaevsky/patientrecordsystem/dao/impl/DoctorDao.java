package com.github.stanislavbukaevsky.patientrecordsystem.dao.impl;

import com.github.stanislavbukaevsky.patientrecordsystem.dao.Dao;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.DaoNotCompletedException;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Doctor;
import com.github.stanislavbukaevsky.patientrecordsystem.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.ExceptionTextMessageConstant.DAO_NOT_COMPLETED_EXCEPTION_MESSAGE;

public class DoctorDao implements Dao<Doctor, Long> {
    private final static DoctorDao INSTANCE = new DoctorDao();
    private final static String SAVE_SQL = """
            INSERT INTO doctors (first_name, middle_name, last_name, specialization, office)
            VALUES (?, ?, ?, ?, ?)
            """;
    private final static String UPDATE_SQL = """
            UPDATE doctors SET 
            first_name = ?,
            middle_name = ?,
            last_name = ?,
            specialization = ?, 
            office = ?
            WHERE id = ?
            """;
    private final static String FIND_BY_ID_SQL = """
            SELECT id, first_name, middle_name, last_name, specialization, office FROM doctors WHERE id = ?
            """;
    private final static String FIND_ALL_SQL = """
            SELECT id, first_name, middle_name, last_name, specialization, office FROM doctors
            """;
    private final static String DELETE_SQL = """
            DELETE FROM doctors WHERE id = ?
            """;

    private DoctorDao() {
    }

    public static DoctorDao getInstance() {
        return INSTANCE;
    }

    @Override
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

    @Override
    public Doctor update(Doctor doctor) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, doctor.getFirstName());
            statement.setString(2, doctor.getMiddleName());
            statement.setString(3, doctor.getLastName());
            statement.setString(4, doctor.getSpecialization());
            statement.setInt(5, doctor.getOffice());
            statement.setLong(6, doctor.getId());
            statement.executeUpdate();

            return doctor;
        } catch (SQLException e) {
            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
        }
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return findById(id, connection);
        } catch (SQLException e) {
            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
        }
    }

    @Override
    public List<Doctor> findAll() {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL)) {
            List<Doctor> doctors = new ArrayList<>();

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                doctors.add(getNewDoctor(result));
            }

            return doctors;
        } catch (SQLException e) {
            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
        }
    }

    @Override
    public String delete(Long id) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setLong(1, id);
            statement.executeUpdate();

            return "Удаление записи произошло успешно!";
        } catch (SQLException e) {
            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
        }
    }

    public Optional<Doctor> findById(Long id, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();
            Doctor doctor = null;
            if (result.next()) {
                doctor = getNewDoctor(result);
            }

            return Optional.ofNullable(doctor);
        } catch (SQLException e) {
            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
        }
    }

    private Doctor getNewDoctor(ResultSet result) throws SQLException {
        return new Doctor(
                result.getLong("id"),
                result.getString("first_name"),
                result.getString("middle_name"),
                result.getString("last_name"),
                result.getString("specialization"),
                result.getInt("office")
        );
    }
}
