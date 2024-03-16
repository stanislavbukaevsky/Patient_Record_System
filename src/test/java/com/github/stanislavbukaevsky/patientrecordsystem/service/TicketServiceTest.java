package com.github.stanislavbukaevsky.patientrecordsystem.service;

import com.github.stanislavbukaevsky.patientrecordsystem.dao.Dao;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.TicketRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.TicketRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.TicketResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.TicketNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.Mapper;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Doctor;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Patient;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Ticket;
import com.github.stanislavbukaevsky.patientrecordsystem.service.impl.TicketService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.DoctorConstantTest.DOCTOR_ONE;
import static com.github.stanislavbukaevsky.patientrecordsystem.constant.PatientConstantTest.PATIENT_ONE;
import static com.github.stanislavbukaevsky.patientrecordsystem.constant.TicketConstantTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.lenient;

/**
 * Класс с тестами для талона
 */
@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {
    @Mock
    private Dao<Ticket, Long> ticketDao;
    @Mock
    private Dao<Doctor, Long> doctorDao;
    @Mock
    private Dao<Patient, Long> patientDao;
    @Mock
    private Mapper<TicketRequestDto, TicketRequestUpdateDto, TicketResponseDto, Ticket> ticketMapper;
    @InjectMocks
    private TicketService ticketService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ticketService = new TicketService(ticketDao, doctorDao, patientDao);
    }

    @Test
    public void saveTicketTest() {
        assertNotNull(ticketDao);
        lenient().when(ticketDao.save(any(Ticket.class))).thenReturn(TICKET_ONE);
        lenient().when(ticketMapper.mappingToDto(any(Ticket.class))).thenReturn(TICKET_RESPONSE_DTO_ONE);
        lenient().when(doctorDao.findById(any(Long.class))).thenReturn(Optional.of(DOCTOR_ONE));
        lenient().when(patientDao.findById(any(Long.class))).thenReturn(Optional.of(PATIENT_ONE));

        assertEquals(ID_TICKET_ONE, TICKET_ONE.getId());
        assertEquals(DOCTOR_ONE, TICKET_ONE.getDoctor());
        assertEquals(PATIENT_ONE, TICKET_ONE.getPatient());
        assertEquals(DATE_AND_TIME_ONE, TICKET_ONE.getDateAdmission());
        assertEquals(TICKET_RESPONSE_DTO_ONE, ticketService.save(TICKET_REQUEST_DTO_ONE));

        assertThrows(TicketNotFoundException.class, () -> ticketService.save(null));
    }

    @Test
    public void updateTicketTest() {
        assertNotNull(ticketDao);
        lenient().when(ticketDao.findById(any(Long.class))).thenReturn(Optional.of(TICKET_ONE));
        lenient().when(ticketDao.update(any(Ticket.class))).thenReturn(TICKET_ONE);
        lenient().when(ticketMapper.mappingToDto(any(Ticket.class))).thenReturn(TICKET_RESPONSE_DTO_ONE);
        lenient().when(doctorDao.findById(any(Long.class))).thenReturn(Optional.of(DOCTOR_ONE));
        lenient().when(patientDao.findById(any(Long.class))).thenReturn(Optional.of(PATIENT_ONE));

        assertEquals(ID_TICKET_ONE, TICKET_ONE.getId());
        assertEquals(DOCTOR_ONE, TICKET_ONE.getDoctor());
        assertEquals(PATIENT_ONE, TICKET_ONE.getPatient());
        assertEquals(DATE_AND_TIME_ONE, TICKET_ONE.getDateAdmission());
        assertEquals(TICKET_RESPONSE_DTO_ONE, ticketService.update(TICKET_REQUEST_UPDATE_DTO));
        assertEquals(ID_TICKET_ONE, TICKET_REQUEST_UPDATE_DTO.getId());

        assertThrows(TicketNotFoundException.class, () -> ticketService.findById(null));
        assertThrows(TicketNotFoundException.class, () -> ticketService.update(null));
    }

    @Test
    public void findByIdTicketTest() {
        assertNotNull(ticketDao);
        lenient().when(ticketDao.findById(ID_TICKET_ONE)).thenReturn(Optional.of(TICKET_ONE));
        lenient().when(ticketMapper.mappingToDto(any(Ticket.class))).thenReturn(TICKET_RESPONSE_DTO_ONE);
        Assertions.assertThat(TICKET_RESPONSE_DTO_ONE).isEqualTo(ticketService.findById(ID_TICKET_ONE));

        TicketResponseDto actual = ticketService.findById(ID_TICKET_ONE);
        Assertions.assertThat(actual.getId()).isEqualTo(TICKET_ONE.getId());
        Assertions.assertThat(actual.getDoctor()).isEqualTo(DOCTOR_ONE);
        Assertions.assertThat(actual.getPatient()).isEqualTo(PATIENT_ONE);
        Assertions.assertThatExceptionOfType(TicketNotFoundException.class)
                .isThrownBy(() -> ticketService.findById(ID_TICKET_NEGATIVE));
    }

    @Test
    public void findAllTicketsTest() {
        assertNotNull(ticketDao);
        lenient().when(ticketDao.findAll()).thenReturn(List.of(TICKET_ONE, TICKET_TWO));
        lenient().when(ticketMapper.mappingToListDto(anyList())).thenReturn(List.of(TICKET_RESPONSE_DTO_ONE, TICKET_RESPONSE_DTO_TWO));
        Assertions.assertThat(List.of(TICKET_RESPONSE_DTO_ONE, TICKET_RESPONSE_DTO_TWO)).isEqualTo(ticketService.findAll());

        List<TicketResponseDto> actual = ticketService.findAll();
        Assertions.assertThat(actual.get(0).getId()).isEqualTo(TICKET_ONE.getId());
        Assertions.assertThat(actual.get(1).getId()).isEqualTo(TICKET_TWO.getId());
    }

    @Test
    public void deleteTicketTest() {
        assertNotNull(ticketDao);

        lenient().when(ticketDao.findById(ID_TICKET_ONE)).thenReturn(Optional.of(TICKET_ONE));
        String expected = ticketService.delete(ID_TICKET_ONE);
        String actual = ticketService.delete(ID_TICKET_ONE);
        Assertions.assertThat(actual).isEqualTo(expected);
        lenient().when(ticketDao.findById(ID_TICKET_ONE)).thenReturn(Optional.of(TICKET_ONE));

        expected = null;
        actual = ticketService.delete(ID_TICKET_ONE);
        Assertions.assertThat(actual).isEqualTo(expected);
        assertThrows(TicketNotFoundException.class, () -> ticketService.delete(null));
    }
}
