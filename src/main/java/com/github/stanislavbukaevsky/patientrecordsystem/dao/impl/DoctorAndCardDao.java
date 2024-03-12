package com.github.stanislavbukaevsky.patientrecordsystem.dao.impl;

import com.github.stanislavbukaevsky.patientrecordsystem.dao.Dao;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.DaoNotCompletedException;
import com.github.stanislavbukaevsky.patientrecordsystem.model.DoctorAndCard;
import com.github.stanislavbukaevsky.patientrecordsystem.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.ExceptionTextMessageConstant.DAO_NOT_COMPLETED_EXCEPTION_MESSAGE;

public class DoctorAndCardDao implements Dao<DoctorAndCard, Long> {
    private final static DoctorAndCardDao INSTANCE = new DoctorAndCardDao();
    private final DoctorDao doctorDao = DoctorDao.getInstance();
    private final CardDao cardDao = CardDao.getInstance();
    private final static String SAVE_SQL = """
            INSERT INTO doctors_and_cards (doctor_id, card_id)
            VALUES (?, ?)
            """;
    private final static String UPDATE_SQL = """
            UPDATE doctors_and_cards SET 
            doctor_id = ?,
            card_id = ?
            WHERE id = ?
            """;
    private final static String FIND_BY_ID_SQL = """
            SELECT dac.id, dac.doctor_id, dac.card_id,
                   d.first_name, d.middle_name, d.last_name, d.specialization, d.office,
                   c.patient_id, c.appointments, c.analyzes,
                   p.first_name, p.middle_name, p.last_name, p.date_birth 
            FROM doctors_and_cards dac
            JOIN doctors d ON d.id = dac.doctor_id
            JOIN cards c ON c.id = dac.card_id
            JOIN patients p ON p.id = c.patient_id
            WHERE dac.id = ?
            """;
    private final static String FIND_ALL_SQL = """
            SELECT dac.id, dac.doctor_id, dac.card_id,
                   d.first_name, d.middle_name, d.last_name, d.specialization, d.office,
                   c.patient_id, c.appointments, c.analyzes,
                   p.first_name, p.middle_name, p.last_name, p.date_birth 
            FROM doctors_and_cards dac
            JOIN doctors d ON d.id = dac.doctor_id
            JOIN cards c ON c.id = dac.card_id
            JOIN patients p ON p.id = c.patient_id
            """;
    private final static String DELETE_SQL = """
            DELETE FROM doctors_and_cards WHERE id = ?
            """;

    private DoctorAndCardDao() {
    }

    public static DoctorAndCardDao getInstance() {
        return INSTANCE;
    }

    @Override
    public DoctorAndCard save(DoctorAndCard doctorAndCard) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, doctorAndCard.getDoctor().getId());
            statement.setLong(2, doctorAndCard.getCard().getId());
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                doctorAndCard.setId(keys.getLong("id"));
            }

            return doctorAndCard;
        } catch (SQLException e) {
            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
        }
    }

    @Override
    public DoctorAndCard update(DoctorAndCard doctorAndCard) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setLong(1, doctorAndCard.getDoctor().getId());
            statement.setLong(2, doctorAndCard.getCard().getId());
            statement.setLong(3, doctorAndCard.getId());
            statement.executeUpdate();

            return doctorAndCard;
        } catch (SQLException e) {
            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
        }
    }

    @Override
    public Optional<DoctorAndCard> findById(Long id) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();
            DoctorAndCard doctorAndCard = null;
            if (result.next()) {
                doctorAndCard = getNewDoctorAndCard(result);
            }

            return Optional.ofNullable(doctorAndCard);
        } catch (SQLException e) {
            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
        }
    }

    @Override
    public List<DoctorAndCard> findAll() {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL)) {
            List<DoctorAndCard> doctorsAndCards = new ArrayList<>();

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                doctorsAndCards.add(getNewDoctorAndCard(result));
            }

            return doctorsAndCards;
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

    private DoctorAndCard getNewDoctorAndCard(ResultSet result) throws SQLException {
        Connection connection = result.getStatement().getConnection();

        return new DoctorAndCard(
                result.getLong("id"),
                doctorDao.findById(
                        result.getLong("doctor_id"),
                        connection).orElse(null),
                cardDao.findById(
                        result.getLong("card_id"),
                        connection).orElse(null)
        );
    }
}
