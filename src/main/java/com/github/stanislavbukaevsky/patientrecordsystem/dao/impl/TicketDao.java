package com.github.stanislavbukaevsky.patientrecordsystem.dao.impl;

import com.github.stanislavbukaevsky.patientrecordsystem.dao.Dao;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.DaoNotCompletedException;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Ticket;
import com.github.stanislavbukaevsky.patientrecordsystem.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.ExceptionTextMessageConstant.DAO_NOT_COMPLETED_EXCEPTION_MESSAGE;

public class TicketDao implements Dao<Ticket, Long> {
    private final static TicketDao INSTANCE = new TicketDao();
    private final DoctorDao doctorDao = DoctorDao.getInstance();
    private final PatientDao patientDao = PatientDao.getInstance();
    private final static String SAVE_SQL = """
            INSERT INTO tickets (doctor_id, patient_id, date_admission)
            VALUES (?, ?, ?)
            """;
    private final static String UPDATE_SQL = """
            UPDATE tickets SET 
            doctor_id = ?,
            patient_id = ?,
            date_admission = ?
            WHERE id = ?
            """;
    private final static String FIND_BY_ID_SQL = """
            SELECT t.id, t.doctor_id, t.patient_id, t.date_admission,
                   d.first_name, d.middle_name, d.last_name, d.specialization, d.office,
                   p.first_name, p.middle_name, p.last_name, p.date_birth 
            FROM tickets t
            JOIN doctors d ON d.id = t.doctor_id
            JOIN patients p ON p.id = t.patient_id 
            WHERE t.id = ?
            """;
    private final static String FIND_ALL_SQL = """
            SELECT t.id, t.doctor_id, t.patient_id, t.date_admission,
                   d.first_name, d.middle_name, d.last_name, d.specialization, d.office,
                   p.first_name, p.middle_name, p.last_name, p.date_birth 
            FROM tickets t
            JOIN doctors d ON d.id = t.doctor_id
            JOIN patients p ON p.id = t.patient_id
            """;
    private final static String DELETE_SQL = """
            DELETE FROM tickets WHERE id = ?
            """;

    private TicketDao() {
    }

    public static TicketDao getInstance() {
        return INSTANCE;
    }

    @Override
    public Ticket save(Ticket ticket) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, ticket.getDoctor().getId());
            statement.setLong(2, ticket.getPatient().getId());
            statement.setTimestamp(3, Timestamp.valueOf(ticket.getDateAdmission()));
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                ticket.setId(keys.getLong("id"));
            }

            return ticket;
        } catch (SQLException e) {
            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
        }
    }

    @Override
    public Ticket update(Ticket ticket) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setLong(1, ticket.getDoctor().getId());
            statement.setLong(2, ticket.getPatient().getId());
            statement.setTimestamp(3, Timestamp.valueOf(ticket.getDateAdmission()));
            statement.setLong(4, ticket.getId());
            statement.executeUpdate();

            return ticket;
        } catch (SQLException e) {
            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
        }
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();
            Ticket ticket = null;
            if (result.next()) {
                ticket = getNewTicket(result);
            }

            return Optional.ofNullable(ticket);
        } catch (SQLException e) {
            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
        }
    }

    @Override
    public List<Ticket> findAll() {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL)) {
            List<Ticket> tickets = new ArrayList<>();

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                tickets.add(getNewTicket(result));
            }

            return tickets;
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

    public Ticket getNewTicket(ResultSet result) throws SQLException {
        Connection connection = result.getStatement().getConnection();

        return new Ticket(
                result.getLong("id"),
                doctorDao.findById(
                        result.getLong("doctor_id"),
                        connection).orElse(null),
                patientDao.findById(
                        result.getLong("patient_id"),
                        connection).orElse(null),
                result.getTimestamp("date_admission").toLocalDateTime()
        );
    }
}
