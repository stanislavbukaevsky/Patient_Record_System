package com.github.stanislavbukaevsky.patientrecordsystem.service.impl;

import com.github.stanislavbukaevsky.patientrecordsystem.dao.Dao;
import com.github.stanislavbukaevsky.patientrecordsystem.dao.impl.DoctorDao;
import com.github.stanislavbukaevsky.patientrecordsystem.dao.impl.PatientDao;
import com.github.stanislavbukaevsky.patientrecordsystem.dao.impl.TicketDao;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.TicketRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.TicketRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.TicketResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.DoctorNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.PatientNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.TicketNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.Mapper;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.impl.TicketMapper;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Doctor;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Patient;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Ticket;
import com.github.stanislavbukaevsky.patientrecordsystem.service.Service;

import java.util.List;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.ExceptionTextMessageConstant.*;

/**
 * Класс-сервис, с бизнес-логикой для карты талона.
 * Реализует интерфейс {@link Service}. Параметры: <br>
 * {@link TicketRequestDto} - DTO для запроса пользователя на сохранение талона <br>
 * {@link TicketRequestUpdateDto} - DTO для запроса пользователя на изменение талона <br>
 * {@link TicketResponseDto} - DTO для ответа пользователю с информацией о талоне <br>
 * {@link Long} - идентификатор
 */
public class TicketService implements Service<TicketRequestDto, TicketRequestUpdateDto, TicketResponseDto, Long> {
    private final static TicketService INSTANCE = new TicketService();
    private final Dao<Ticket, Long> ticketDao = TicketDao.getInstance();
    private final Dao<Doctor, Long> doctorDao = DoctorDao.getInstance();
    private final Dao<Patient, Long> patientDao = PatientDao.getInstance();
    private final Mapper<TicketRequestDto, TicketRequestUpdateDto, TicketResponseDto, Ticket> ticketMapper = TicketMapper.getInstance();

    private TicketService() {
    }

    public static TicketService getInstance() {
        return INSTANCE;
    }

    /**
     * Этот метод сохраняет талон в базу данных
     *
     * @param ticketRequest ответ пользователя
     * @return Возвращает ответ пользователю
     */
    @Override
    public TicketResponseDto save(TicketRequestDto ticketRequest) {
        if (ticketRequest == null) {
            throw new TicketNotFoundException(TICKET_NOT_FOUND_EXCEPTION_BY_SAVE_AND_UPDATE_MESSAGE);
        }

        Doctor doctor = doctorDao.findById(ticketRequest.getDoctorId()).orElseThrow(() ->
                new DoctorNotFoundException(DOCTOR_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));
        Patient patient = patientDao.findById(ticketRequest.getPatientId()).orElseThrow(() ->
                new PatientNotFoundException(PATIENT_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));
        Ticket ticket = ticketMapper.mappingToEntity(ticketRequest);
        ticket.setDoctor(doctor);
        ticket.setPatient(patient);
        Ticket result = ticketDao.save(ticket);

        return ticketMapper.mappingToDto(result);
    }

    /**
     * Этот метод изменяет талон в базе данных
     *
     * @param ticketRequest ответ пользователя
     * @return Возвращает ответ пользователю
     */
    @Override
    public TicketResponseDto update(TicketRequestUpdateDto ticketRequest) {
        if (ticketRequest == null) {
            throw new TicketNotFoundException(TICKET_NOT_FOUND_EXCEPTION_BY_SAVE_AND_UPDATE_MESSAGE);
        }

        Ticket ticket = ticketDao.findById(ticketRequest.getId()).orElseThrow(() ->
                new TicketNotFoundException(TICKET_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));
        Doctor doctor = doctorDao.findById(ticketRequest.getDoctorId()).orElseThrow(() ->
                new DoctorNotFoundException(DOCTOR_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));
        Patient patient = patientDao.findById(ticketRequest.getPatientId()).orElseThrow(() ->
                new PatientNotFoundException(PATIENT_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));
        if (ticket != null) {
            Ticket mapping = ticketMapper.mappingByUpdateToEntity(ticketRequest);
            mapping.setDoctor(doctor);
            mapping.setPatient(patient);
            Ticket result = ticketDao.update(mapping);

            return ticketMapper.mappingToDto(result);
        }

        throw new TicketNotFoundException(TICKET_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE);
    }

    /**
     * Этот метод ищет талон в базе данных по его идентификатору
     *
     * @param id идентификатор талона
     * @return Возвращает ответ пользователю
     */
    @Override
    public TicketResponseDto findById(Long id) {
        Ticket ticket = ticketDao.findById(id).orElseThrow(() ->
                new TicketNotFoundException(TICKET_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));

        return ticketMapper.mappingToDto(ticket);
    }

    /**
     * Этот метод ищет все талоны, сохраненные в базе данных
     *
     * @return Возвращает список dto
     */
    @Override
    public List<TicketResponseDto> findAll() {
        List<Ticket> tickets = ticketDao.findAll();
        if (tickets.isEmpty()) {
            throw new TicketNotFoundException(TICKET_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        return ticketMapper.mappingToListDto(tickets);
    }

    /**
     * Этот метод удаляет талон из базы данных
     *
     * @param id идентификатор талона
     * @return Возвращает строку, если удаление прошло успешно
     */
    @Override
    public String delete(Long id) {
        if (id == null) {
            throw new TicketNotFoundException(TICKET_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE);
        }

        return ticketDao.delete(id);
    }
}
