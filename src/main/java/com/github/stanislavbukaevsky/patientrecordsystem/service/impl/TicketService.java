package com.github.stanislavbukaevsky.patientrecordsystem.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    @Override
    public TicketResponseDto save(TicketRequestDto ticketRequest) throws JsonProcessingException {
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

    @Override
    public TicketResponseDto update(TicketRequestUpdateDto ticketRequest) throws JsonProcessingException {
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

    @Override
    public TicketResponseDto findById(Long id) throws JsonProcessingException {
        Ticket ticket = ticketDao.findById(id).orElseThrow(() ->
                new TicketNotFoundException(TICKET_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE));

        return ticketMapper.mappingToDto(ticket);
    }

    @Override
    public List<TicketResponseDto> findAll() throws JsonProcessingException {
        List<Ticket> tickets = ticketDao.findAll();
        if (tickets.isEmpty()) {
            throw new TicketNotFoundException(TICKET_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        return ticketMapper.mappingToListDto(tickets);
    }

    @Override
    public String delete(Long id) {
        if (id == null) {
            throw new TicketNotFoundException(TICKET_NOT_FOUND_EXCEPTION_BY_FIND_AND_DELETE_MESSAGE);
        }

        return ticketDao.delete(id);
    }
}
