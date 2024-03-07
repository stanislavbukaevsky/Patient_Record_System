package com.github.stanislavbukaevsky.patientrecordsystem;

import com.github.stanislavbukaevsky.patientrecordsystem.dao.Dao;
import com.github.stanislavbukaevsky.patientrecordsystem.dao.impl.DoctorDao;
import com.github.stanislavbukaevsky.patientrecordsystem.dao.impl.PatientDao;
import com.github.stanislavbukaevsky.patientrecordsystem.dao.impl.TicketDao;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Doctor;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Patient;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Ticket;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws SQLException {
        LocalDateTime dateTime = LocalDateTime.now();
        Dao<Ticket, Long> ticketDao = TicketDao.getInstance();
        Dao<Doctor, Long> doctorDao = DoctorDao.getInstance();
        Dao<Patient, Long> patientDao = PatientDao.getInstance();
        Doctor doctor = doctorDao.findById(2L).get();
        Patient patient = patientDao.findById(3L).get();
        Ticket ticket = new Ticket();
        ticket.setDoctor(doctor);
        ticket.setPatient(patient);
        ticket.setDateAdmission(dateTime);
        Ticket ticketUpdate = ticketDao.findById(2L).get();
        ticketUpdate.setDoctor(doctor);
        ticketUpdate.setDateAdmission(dateTime);

        System.out.println(ticketDao.delete(2L));

//        Dao<Patient, Long> dao = PatientDao.getInstance();
//        Patient patient = new Patient();
//        patient.setFirstName("Федор");
//        patient.setMiddleName("Федорович");
//        patient.setLastName("Федоров");
//        patient.setDateBirth("22.10.1991");
//        System.out.println(dao.delete(2L));
//        Patient patientUpdate = dao.findById(2L).get();
//        patientUpdate.setFirstName("Николай");
//        System.out.println(dao.update(patientUpdate));

//        System.out.println(doctorDao.save(doctor));
//        System.out.println(doctorDao.delete(1L));
//        System.out.println(doctorDao.findById(20L));
//        Doctor doctorUpdate = doctorDao.findById(2L).get();
//        doctorUpdate.setOffice(777);
//        System.out.println(doctorDao.findAll());

//        String sqlTableDoctors = """
//                CREATE TABLE IF NOT EXISTS doctors (
//                    id BIGSERIAL PRIMARY KEY,
//                    first_name VARCHAR(64) NOT NULL,
//                    middle_name VARCHAR(64) NOT NULL,
//                    last_name VARCHAR(64) NOT NULL,
//                    specialization VARCHAR(32) NOT NULL,
//                    office INTEGER NOT NULL
//                );
//                """;
//        String sqlTablePatients = """
//                CREATE TABLE IF NOT EXISTS patients (
//                    id BIGSERIAL PRIMARY KEY,
//                    first_name VARCHAR(64) NOT NULL,
//                    middle_name VARCHAR(64) NOT NULL,
//                    last_name VARCHAR(64) NOT NULL,
//                    date_birth VARCHAR(16) NOT NULL
//                );
//                """;
//        String sqlTableTickets = """
//                CREATE TABLE IF NOT EXISTS tickets (
//                    id BIGSERIAL PRIMARY KEY,
//                    doctor_id BIGINT NOT NULL REFERENCES doctors(id),
//                    patient_id BIGINT NOT NULL REFERENCES patients(id),
//                    date_admission TIMESTAMP NOT NULL default CURRENT_TIMESTAMP
//                );
//                """;
//        String sqlTableCards = """
//                CREATE TABLE IF NOT EXISTS cards (
//                    id BIGSERIAL PRIMARY KEY,
//                    patient_id BIGINT NOT NULL REFERENCES patients(id),
//                    appointments VARCHAR(1000),
//                    analyzes VARCHAR(1000)
//                );
//                """;
//        try (Connection connection = ConnectionManager.getConnection();
//             Statement statement = connection.createStatement()) {
//            statement.execute(sqlTableDoctors);
//            statement.execute(sqlTablePatients);
//            statement.execute(sqlTableTickets);
//            statement.execute(sqlTableCards);
//            System.out.println(connection.getTransactionIsolation());
//            System.out.println(statement.execute(sqlTableDoctors));
//        }
    }
}
