package com.github.stanislavbukaevsky.patientrecordsystem.dao.impl;

import com.github.stanislavbukaevsky.patientrecordsystem.dao.FindDao;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.DaoNotCompletedException;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Card;
import com.github.stanislavbukaevsky.patientrecordsystem.model.DoctorAndCard;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Ticket;
import com.github.stanislavbukaevsky.patientrecordsystem.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.ExceptionTextMessageConstant.DAO_NOT_COMPLETED_EXCEPTION_MESSAGE;

/**
 * Класс-репозиторий с методами поиска по внешнему ключу.
 * Реализует интерфейс {@link FindDao}. Параметры: <br>
 */
public class FindByForeignKeyDao implements FindDao {
    private final static FindByForeignKeyDao INSTANCE = new FindByForeignKeyDao();
    private final CardDao cardDao = CardDao.getInstance();
    private final TicketDao ticketDao = TicketDao.getInstance();
    private final DoctorAndCardDao doctorAndCardDao = DoctorAndCardDao.getInstance();
    private final static String FIND_CARDS_BY_PATIENT_ID_SQL = """
            SELECT c.id, c.patient_id, c.appointments, c.analyzes,
                   p.first_name, p.middle_name, p.last_name, p.date_birth 
            FROM cards c
            JOIN patients p ON p.id = c.patient_id
            WHERE p.id = ?
            """;
    private final static String FIND_TICKETS_BY_DOCTOR_ID_SQL = """
            SELECT t.id, t.doctor_id, t.patient_id, t.date_admission,
                   d.first_name, d.middle_name, d.last_name, d.specialization, d.office,
                   p.first_name, p.middle_name, p.last_name, p.date_birth 
            FROM tickets t
            JOIN doctors d ON d.id = t.doctor_id
            JOIN patients p ON p.id = t.patient_id
            WHERE d.id = ?
            """;
    private final static String FIND_TICKETS_BY_PATIENT_ID_SQL = """
            SELECT t.id, t.doctor_id, t.patient_id, t.date_admission,
                   d.first_name, d.middle_name, d.last_name, d.specialization, d.office,
                   p.first_name, p.middle_name, p.last_name, p.date_birth 
            FROM tickets t
            JOIN doctors d ON d.id = t.doctor_id
            JOIN patients p ON p.id = t.patient_id
            WHERE p.id = ?
            """;
    private final static String FIND_DOCTORS_AND_CARDS_BY_DOCTOR_ID_SQL = """
            SELECT dac.id, dac.doctor_id, dac.card_id,
                   d.first_name, d.middle_name, d.last_name, d.specialization, d.office,
                   c.patient_id, c.appointments, c.analyzes,
                   p.first_name, p.middle_name, p.last_name, p.date_birth 
            FROM doctors_and_cards dac
            JOIN doctors d ON d.id = dac.doctor_id
            JOIN cards c ON c.id = dac.card_id
            JOIN patients p ON p.id = c.patient_id
            WHERE d.id = ?
            """;
    private final static String FIND_DOCTORS_AND_CARDS_BY_CARD_ID_SQL = """
            SELECT dac.id, dac.doctor_id, dac.card_id,
                   d.first_name, d.middle_name, d.last_name, d.specialization, d.office,
                   c.patient_id, c.appointments, c.analyzes,
                   p.first_name, p.middle_name, p.last_name, p.date_birth 
            FROM doctors_and_cards dac
            JOIN doctors d ON d.id = dac.doctor_id
            JOIN cards c ON c.id = dac.card_id
            JOIN patients p ON p.id = c.patient_id
            WHERE c.id = ?
            """;

    private FindByForeignKeyDao() {
    }

    public static FindByForeignKeyDao getInstance() {
        return INSTANCE;
    }

    /**
     * Этот метод ищет все карты паиента по идентификатору пациента
     *
     * @param patientId идентификатор пациента
     * @return Возвращает список карт пациента
     */
    @Override
    public List<Card> findCardsByPatientId(Long patientId) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_CARDS_BY_PATIENT_ID_SQL)) {
            statement.setLong(1, patientId);

            List<Card> cards = new ArrayList<>();
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                cards.add(cardDao.getNewCard(result));
            }

            return cards;
        } catch (SQLException e) {
            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
        }
    }

    /**
     * Этот метод ищет все талоны по идентификатору врача
     *
     * @param doctorId идентификатор врача
     * @return Возвращает список талонов
     */
    @Override
    public List<Ticket> findTicketsByDoctorId(Long doctorId) {
        return getResponseTickets(doctorId, FIND_TICKETS_BY_DOCTOR_ID_SQL);
    }

    /**
     * Этот метод ищет все талоны по идентификатору пациента
     *
     * @param patientId идентификатор пациента
     * @return Возвращает список талонов
     */
    @Override
    public List<Ticket> findTicketsByPatientId(Long patientId) {
        return getResponseTickets(patientId, FIND_TICKETS_BY_PATIENT_ID_SQL);
    }

    /**
     * Этот метод ищет всех врачей и карт пациента по идентификатору врача
     *
     * @param doctorId идентификатор врача
     * @return Возвращает список врачей и карт пациента
     */
    @Override
    public List<DoctorAndCard> findDoctorsAndCardsByDoctorId(Long doctorId) {
        return getResponseDoctorAndCards(doctorId, FIND_DOCTORS_AND_CARDS_BY_DOCTOR_ID_SQL);
    }

    /**
     * Этот метод ищет всех врачей и карт пациента по идентификатору карты пациента
     *
     * @param cardId идентификатор карты пациента
     * @return Возвращает список врачей и карт пациента
     */
    @Override
    public List<DoctorAndCard> findDoctorsAndCardsByCardId(Long cardId) {
        return getResponseDoctorAndCards(cardId, FIND_DOCTORS_AND_CARDS_BY_CARD_ID_SQL);
    }

    private List<Ticket> getResponseTickets(Long id, String sql) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);

            List<Ticket> tickets = new ArrayList<>();
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                tickets.add(ticketDao.getNewTicket(result));
            }

            return tickets;
        } catch (SQLException e) {
            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
        }
    }

    private List<DoctorAndCard> getResponseDoctorAndCards(Long id, String sql) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);

            List<DoctorAndCard> doctorAndCards = new ArrayList<>();
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                doctorAndCards.add(doctorAndCardDao.getNewDoctorAndCard(result));
            }

            return doctorAndCards;
        } catch (SQLException e) {
            throw new DaoNotCompletedException(DAO_NOT_COMPLETED_EXCEPTION_MESSAGE + e);
        }
    }
}
