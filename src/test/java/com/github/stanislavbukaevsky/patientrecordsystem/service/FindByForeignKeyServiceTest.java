package com.github.stanislavbukaevsky.patientrecordsystem.service;

import com.github.stanislavbukaevsky.patientrecordsystem.dao.FindDao;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.FindByCardsResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.FindByDoctorsAndCardsResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.FindByTicketsResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.DoctorAndCardNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.TicketNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.FindMapper;
import com.github.stanislavbukaevsky.patientrecordsystem.service.impl.FindByForeignKeyService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.CardConstantTest.*;
import static com.github.stanislavbukaevsky.patientrecordsystem.constant.DoctorAndCardConstantTest.DOCTOR_AND_CARD_ONE;
import static com.github.stanislavbukaevsky.patientrecordsystem.constant.DoctorAndCardConstantTest.DOCTOR_AND_CARD_TWO;
import static com.github.stanislavbukaevsky.patientrecordsystem.constant.DoctorConstantTest.ID_DOCTOR_ONE;
import static com.github.stanislavbukaevsky.patientrecordsystem.constant.FindByForeignKeyConstantTest.*;
import static com.github.stanislavbukaevsky.patientrecordsystem.constant.PatientConstantTest.ID_PATIENT_ONE;
import static com.github.stanislavbukaevsky.patientrecordsystem.constant.PatientConstantTest.ID_PATIENT_TWO;
import static com.github.stanislavbukaevsky.patientrecordsystem.constant.TicketConstantTest.TICKET_ONE;
import static com.github.stanislavbukaevsky.patientrecordsystem.constant.TicketConstantTest.TICKET_TWO;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.lenient;

/**
 * Класс с тестами для поиска по внешнему ключу
 */
@ExtendWith(MockitoExtension.class)
public class FindByForeignKeyServiceTest {
    @Mock
    private FindDao findDao;
    @Mock
    private FindMapper findMapper;
    @InjectMocks
    private FindByForeignKeyService findByForeignKeyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        findByForeignKeyService = new FindByForeignKeyService(findDao);
    }

    @Test
    public void findCardsByPatientIdTest() {
        assertNotNull(findDao);
        lenient().when(findDao.findCardsByPatientId(ID_PATIENT_ONE)).thenReturn(List.of(CARD_ONE, CARD_TWO));
        lenient().when(findMapper.mappingForFindCardsByPatientId(anyList()))
                .thenReturn(List.of(FIND_BY_CARDS_RESPONSE_DTO_ONE, FIND_BY_CARDS_RESPONSE_DTO_TWO));

        List<FindByCardsResponseDto> actual = findByForeignKeyService.findCardsByPatientId(ID_PATIENT_ONE);
        Assertions.assertThat(actual.get(0).getId()).isEqualTo(FIND_BY_CARDS_RESPONSE_DTO_ONE.getId());
        Assertions.assertThat(actual.get(1).getId()).isEqualTo(FIND_BY_CARDS_RESPONSE_DTO_TWO.getId());
    }

    @Test
    public void findTicketsByDoctorIdTest() {
        assertNotNull(findDao);
        lenient().when(findDao.findTicketsByDoctorId(ID_DOCTOR_ONE)).thenReturn(List.of(TICKET_ONE, TICKET_TWO));
        lenient().when(findMapper.mappingForFindTicketsByForeignKey(anyList()))
                .thenReturn(List.of(FIND_BY_TICKETS_RESPONSE_DTO_ONE, FIND_BY_TICKETS_RESPONSE_DTO_TWO));

        List<FindByTicketsResponseDto> actual = findByForeignKeyService.findTicketsByDoctorId(ID_DOCTOR_ONE);
        Assertions.assertThat(actual.get(0).getId()).isEqualTo(FIND_BY_TICKETS_RESPONSE_DTO_ONE.getId());
        Assertions.assertThat(actual.get(1).getId()).isEqualTo(FIND_BY_TICKETS_RESPONSE_DTO_TWO.getId());
    }

    @Test
    public void findTicketsByPatientIdTest() {
        assertNotNull(findDao);
        lenient().when(findDao.findTicketsByDoctorId(ID_PATIENT_TWO)).thenReturn(List.of(TICKET_ONE, TICKET_TWO));
        lenient().when(findMapper.mappingForFindTicketsByForeignKey(anyList()))
                .thenReturn(List.of(FIND_BY_TICKETS_RESPONSE_DTO_ONE, FIND_BY_TICKETS_RESPONSE_DTO_TWO));

        assertThrows(TicketNotFoundException.class, () -> findByForeignKeyService.findTicketsByPatientId(ID_PATIENT_TWO));
    }

    @Test
    public void findDoctorsAndCardsByDoctorIdTest() {
        assertNotNull(findDao);
        lenient().when(findDao.findDoctorsAndCardsByDoctorId(ID_DOCTOR_ONE)).thenReturn(List.of(DOCTOR_AND_CARD_ONE, DOCTOR_AND_CARD_TWO));
        lenient().when(findMapper.mappingForFindDoctorsAndCardsByForeignKey(anyList()))
                .thenReturn(List.of(FIND_BY_DOCTORS_AND_CARDS_RESPONSE_DTO_ONE, FIND_BY_DOCTORS_AND_CARDS_RESPONSE_DTO_TWO));

        List<FindByDoctorsAndCardsResponseDto> actual = findByForeignKeyService.findDoctorsAndCardsByDoctorId(ID_DOCTOR_ONE);
        Assertions.assertThat(actual.get(0).getId()).isEqualTo(FIND_BY_DOCTORS_AND_CARDS_RESPONSE_DTO_ONE.getId());
        Assertions.assertThat(actual.get(1).getId()).isEqualTo(FIND_BY_DOCTORS_AND_CARDS_RESPONSE_DTO_TWO.getId());
    }

    @Test
    public void findDoctorsAndCardsByCardIdTest() {
        assertNotNull(findDao);
        lenient().when(findDao.findDoctorsAndCardsByDoctorId(ID_CARD_ONE)).thenReturn(List.of(DOCTOR_AND_CARD_ONE, DOCTOR_AND_CARD_TWO));
        lenient().when(findMapper.mappingForFindDoctorsAndCardsByForeignKey(anyList()))
                .thenReturn(List.of(FIND_BY_DOCTORS_AND_CARDS_RESPONSE_DTO_ONE, FIND_BY_DOCTORS_AND_CARDS_RESPONSE_DTO_TWO));

        List<FindByDoctorsAndCardsResponseDto> actual = findByForeignKeyService.findDoctorsAndCardsByDoctorId(ID_CARD_ONE);
        Assertions.assertThat(actual.get(0).getId()).isEqualTo(FIND_BY_DOCTORS_AND_CARDS_RESPONSE_DTO_ONE.getId());
        Assertions.assertThat(actual.get(1).getId()).isEqualTo(FIND_BY_DOCTORS_AND_CARDS_RESPONSE_DTO_TWO.getId());
        assertThrows(DoctorAndCardNotFoundException.class, () -> findByForeignKeyService.findDoctorsAndCardsByCardId(ID_FIND_BY_FOREIGN_KEY_NEGATIVE));
    }
}
