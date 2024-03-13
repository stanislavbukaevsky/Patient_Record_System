package com.github.stanislavbukaevsky.patientrecordsystem;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
//        String str = "/find/cards-patient/";
//        String[] split = str.split("/");
//        System.out.println(split[1]);

//        Dao<Doctor, Long> doctorFind = DoctorDao.getInstance();
//        CardDao cardFind = CardDao.getInstance();
//        FindByForeignKeyDao cards = FindByForeignKeyDao.getInstance();
//        System.out.println(cards.findDoctorsAndCardsByDoctorId(2L));
//        Dao<DoctorAndCard, Long> doctorAndCardDao = DoctorAndCardDao.getInstance();
//        Doctor doctor = doctorFind.findById(6L).get();
//        Card card = cardFind.findById(3L).get();
//        DoctorAndCard doctorAndCard = doctorAndCardDao.findById(2L).get();
//        doctorAndCard.setDoctor(doctor);
//        doctorAndCard.setCard(card);
//        System.out.println(doctorAndCardDao.delete(2L));
//        DaoSaveCardId<Doctor, Card> doctorDao = DoctorSaveCardIdDao.getInstance();
//        Doctor doctor = doctorFind.findById(3L).get();
//        Card card = cardFind.findById(4L).get();
//        System.out.println(doctorDao.save(doctor, card));
//        Doctor doctor = new Doctor();
//        doctor.setFirstName("Светлана");
//        doctor.setMiddleName("Ивановна");
//        doctor.setLastName("Лукашова");
//        doctor.setSpecialization("Хирург");
//        doctor.setOffice(320);
//        System.out.println(doctorFind.save(doctor));

//        Dao<Patient, Long> patientDao = PatientDao.getInstance();
//        Patient patient = patientDao.findById(4L).get();
//        Card card = new Card();
//        card.setPatient(patient);
//        card.setAppointments("Клизма");
//        card.setAnalyzes("Хорошие");
//        System.out.println(dao.save(card));
//        Card card = dao.findById(1L).get();
//        card.setAppointments("Больше ничего не нужно");
//        System.out.println(dao.delete(1L));

//        LocalDateTime dateTime = LocalDateTime.now();
//        Dao<Ticket, Long> dao = TicketDao.getInstance();
//        Dao<Doctor, Long> doctorDao = DoctorDao.getInstance();
//        Dao<Patient, Long> patientDao = PatientDao.getInstance();
//        Doctor doctor = doctorDao.findById(4L).get();
//        Patient patient = patientDao.findById(4L).get();
//        Ticket ticket = new Ticket();
//        ticket.setDoctor(doctor);
//        ticket.setPatient(patient);
//        ticket.setDateAdmission(dateTime);
//        System.out.println(dao.save(ticket));

//        LocalDateTime dateTime = LocalDateTime.now();
//        Dao<Ticket, Long> ticketDao = TicketDao.getInstance();
//        Dao<Doctor, Long> doctorDao = DoctorDao.getInstance();
//        Dao<Patient, Long> patientDao = PatientDao.getInstance();
//        Doctor doctor = doctorDao.findById(2L).get();
//        Patient patient = patientDao.findById(3L).get();
//        Ticket ticket = new Ticket();
//        ticket.setDoctor(doctor);
//        ticket.setPatient(patient);
//        ticket.setDateAdmission(dateTime);
//        Ticket ticketUpdate = ticketDao.findById(2L).get();
//        ticketUpdate.setDoctor(doctor);
//        ticketUpdate.setDateAdmission(dateTime);
//
//        System.out.println(ticketDao.delete(2L));

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

//        String sqlTablePatients = """
//                CREATE TABLE IF NOT EXISTS patients (
//                    id BIGSERIAL PRIMARY KEY,
//                    first_name VARCHAR(64) NOT NULL,
//                    middle_name VARCHAR(64) NOT NULL,
//                    last_name VARCHAR(64) NOT NULL,
//                    date_birth VARCHAR(16) NOT NULL
//                );
//                """;
//        String sqlTableCards = """
//                CREATE TABLE IF NOT EXISTS cards (
//                    id BIGSERIAL PRIMARY KEY,
//                    patient_id BIGINT NOT NULL REFERENCES patients(id) ON DELETE CASCADE,
//                    appointments VARCHAR(1000),
//                    analyzes VARCHAR(1000)
//                );
//                """;
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
//        String sqlTableTickets = """
//                CREATE TABLE IF NOT EXISTS tickets (
//                    id BIGSERIAL PRIMARY KEY,
//                    doctor_id BIGINT NOT NULL REFERENCES doctors(id) ON DELETE CASCADE,
//                    patient_id BIGINT NOT NULL REFERENCES patients(id) ON DELETE CASCADE,
//                    date_admission TIMESTAMP NOT NULL default CURRENT_TIMESTAMP
//                );
//                """;
//        String sqlTableDoctorsAndCards = """
//                CREATE TABLE IF NOT EXISTS doctors_and_cards (
//                    id BIGSERIAL PRIMARY KEY,
//                    doctor_id BIGINT NOT NULL REFERENCES doctors(id) ON DELETE CASCADE,
//                    card_id BIGINT NOT NULL REFERENCES cards(id) ON DELETE CASCADE
//                );
//                """;
//        try (Connection connection = ConnectionManager.getConnection();
//             Statement statement = connection.createStatement()) {
//            statement.execute(sqlTablePatients);
//            statement.execute(sqlTableCards);
//            statement.execute(sqlTableDoctors);
//            statement.execute(sqlTableTickets);
//            statement.execute(sqlTableDoctorsAndCards);
//            System.out.println(connection.getTransactionIsolation());
//            System.out.println(statement.execute(sqlTableTickets));
//        }
    }
}
