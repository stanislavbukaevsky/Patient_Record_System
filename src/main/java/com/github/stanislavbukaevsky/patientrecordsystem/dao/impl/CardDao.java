package com.github.stanislavbukaevsky.patientrecordsystem.dao.impl;

import com.github.stanislavbukaevsky.patientrecordsystem.dao.Dao;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.DaoNotCompletedException;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Card;
import com.github.stanislavbukaevsky.patientrecordsystem.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.ExceptionTextMessageConstant.DAO_NOT_COMPLETED_EXCEPTION_MESSAGE;

public class CardDao implements Dao<Card, Long> {
    private final static CardDao INSTANCE = new CardDao();
    private final PatientDao patientDao = PatientDao.getInstance();
    private final static String SAVE_SQL = """
            INSERT INTO cards (patient_id, appointments, analyzes)
            VALUES (?, ?, ?)
            """;
    private final static String UPDATE_SQL = """
            UPDATE cards SET 
            patient_id = ?,
            appointments = ?,
            analyzes = ?
            WHERE id = ?
            """;
    private final static String FIND_BY_ID_SQL = """
            SELECT c.id, c.patient_id, c.appointments, c.analyzes,
                   p.first_name, p.middle_name, p.last_name, p.date_birth 
            FROM cards c
            JOIN patients p ON p.id = c.patient_id
            WHERE c.id = ?
            """;
    private final static String FIND_ALL_SQL = """
            SELECT c.id, c.patient_id, c.appointments, c.analyzes,
                   p.first_name, p.middle_name, p.last_name, p.date_birth 
            FROM cards c
            JOIN patients p ON p.id = c.patient_id
            """;
    //    private final static String FIND_CARDS_BY_PATIENT_ID_SQL = """
//            SELECT c.id, c.patient_id, c.appointments, c.analyzes,
//                   p.first_name, p.middle_name, p.last_name, p.date_birth
//            FROM cards c
//            JOIN patients p ON p.id = c.patient_id
//            WHERE p.id = ?
//            """;
    private final static String DELETE_SQL = """
            DELETE FROM cards WHERE id = ?
            """;

    private CardDao() {
    }

    public static CardDao getInstance() {
        return INSTANCE;
    }

    @Override
    public Card save(Card card) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, card.getPatient().getId());
            statement.setString(2, card.getAppointments());
            statement.setString(3, card.getAnalyzes());
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                card.setId(keys.getLong("id"));
            }

            return card;
        } catch (SQLException e) {
            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
        }
    }

    @Override
    public Card update(Card card) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setLong(1, card.getPatient().getId());
            statement.setString(2, card.getAppointments());
            statement.setString(3, card.getAnalyzes());
            statement.setLong(4, card.getId());
            statement.executeUpdate();

            return card;
        } catch (SQLException e) {
            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
        }
    }

    @Override
    public Optional<Card> findById(Long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return findById(id, connection);
        } catch (SQLException e) {
            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
        }
    }

    @Override
    public List<Card> findAll() {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL)) {
            List<Card> cards = new ArrayList<>();

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                cards.add(getNewCard(result));
            }

            return cards;
        } catch (SQLException e) {
            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
        }
    }

//    public List<Card> findCardsByPatientId(Long patientId) {
//        try (Connection connection = ConnectionManager.getConnection();
//             PreparedStatement statement = connection.prepareStatement(FIND_CARDS_BY_PATIENT_ID_SQL)) {
//            statement.setLong(1, patientId);
//
//            List<Card> cards = new ArrayList<>();
//            ResultSet result = statement.executeQuery();
//            while (result.next()) {
//                cards.add(getNewCard(result));
//            }
//
//            return cards;
//        } catch (SQLException e) {
//            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
//        }
//    }

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

    public Optional<Card> findById(Long id, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();
            Card card = null;
            if (result.next()) {
                card = getNewCard(result);
            }

            return Optional.ofNullable(card);
        } catch (SQLException e) {
            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
        }
    }

    public Card getNewCard(ResultSet result) throws SQLException {
        Connection connection = result.getStatement().getConnection();

        return new Card(
                result.getLong("id"),
                patientDao.findById(
                        result.getLong("patient_id"),
                        connection).orElse(null),
                result.getString("appointments"),
                result.getString("analyzes")
        );
    }
}
