package com.github.stanislavbukaevsky.patientrecordsystem.dao.impl;

import com.github.stanislavbukaevsky.patientrecordsystem.dao.Dao;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.DaoNotCompletedException;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Patient;
import com.github.stanislavbukaevsky.patientrecordsystem.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.ExceptionTextMessageConstant.DAO_NOT_COMPLETED_EXCEPTION_MESSAGE;

/**
 * Класс-репозиторий, который обращается к базе данных для пациента.
 * Реализует интерфейс {@link Dao}. Параметры: <br>
 * {@link Patient} - модель пациента <br>
 * {@link Long} - идентификатор <br>
 */
public class PatientDao implements Dao<Patient, Long> {
    private final static PatientDao INSTANCE = new PatientDao();
    private final static String SAVE_SQL = """
            INSERT INTO patients (first_name, middle_name, last_name, date_birth)
            VALUES (?, ?, ?, ?)
            """;
    private final static String UPDATE_SQL = """
            UPDATE patients SET 
            first_name = ?,
            middle_name = ?,
            last_name = ?,
            date_birth = ?
            WHERE id = ?
            """;
    private final static String FIND_BY_ID_SQL = """
            SELECT id, first_name, middle_name, last_name, date_birth FROM patients WHERE id = ?
            """;
    private final static String FIND_ALL_SQL = """
            SELECT id, first_name, middle_name, last_name, date_birth FROM patients
            """;
    private final static String DELETE_SQL = """
            DELETE FROM patients WHERE id = ?
            """;

    private PatientDao() {
    }

    public static PatientDao getInstance() {
        return INSTANCE;
    }

    /**
     * Этот метод сохраняет паиента в базу данных
     *
     * @param patient модель пациента
     * @return Возвращает сохраненного паиента
     */
    @Override
    public Patient save(Patient patient) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, patient.getFirstName());
            statement.setString(2, patient.getMiddleName());
            statement.setString(3, patient.getLastName());
            statement.setString(4, patient.getDateBirth());
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                patient.setId(keys.getLong("id"));
            }

            return patient;
        } catch (SQLException e) {
            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
        }
    }

    /**
     * Этот метод изменяет паиента в базе данных
     *
     * @param patient модель пациента
     * @return Возвращает измененного паиента
     */
    @Override
    public Patient update(Patient patient) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, patient.getFirstName());
            statement.setString(2, patient.getMiddleName());
            statement.setString(3, patient.getLastName());
            statement.setString(4, patient.getDateBirth());
            statement.setLong(5, patient.getId());
            statement.executeUpdate();

            return patient;
        } catch (SQLException e) {
            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
        }
    }

    /**
     * Этот метод ищет паиента в базе данных по его идентификатору
     *
     * @param id идентификатор пациента
     * @return Возвращает найденного пациента, обернутого в {@link Optional}
     */
    @Override
    public Optional<Patient> findById(Long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return findById(id, connection);
        } catch (SQLException e) {
            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
        }
    }

    /**
     * Этот метод ищет всех пациентов, сохраненных в базе данных
     *
     * @return Возвращает список паицентов
     */
    @Override
    public List<Patient> findAll() {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL)) {
            List<Patient> patients = new ArrayList<>();

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                patients.add(getNewPatient(result));
            }

            return patients;
        } catch (SQLException e) {
            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
        }
    }

    /**
     * Этот метод удаляет паиента из базы данных
     *
     * @param id идентификатор пациента
     * @return Возвращает строку, если удаление прошло успешно
     */
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

    /**
     * Перегруженный метод, который ищет паиента в базе данных по его идентификатору
     *
     * @param id         идентификатор пациента
     * @param connection соединение с базой данных
     * @return Возвращает найденного пациента, обернутого в {@link Optional}
     */
    public Optional<Patient> findById(Long id, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();
            Patient patient = null;
            if (result.next()) {
                patient = getNewPatient(result);
            }

            return Optional.ofNullable(patient);
        } catch (SQLException e) {
            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
        }
    }

    /**
     * Этот метод конструирует ответ пользователю
     *
     * @param result результирующий набор данных для пользователя
     * @return Возвращает модель {@link Patient} с ответом пользователю
     * @throws SQLException исключение, если возникла ошибка доступа к базе данных
     */
    public Patient getNewPatient(ResultSet result) throws SQLException {
        return new Patient(
                result.getLong("id"),
                result.getString("first_name"),
                result.getString("middle_name"),
                result.getString("last_name"),
                result.getString("date_birth")
        );
    }
}
