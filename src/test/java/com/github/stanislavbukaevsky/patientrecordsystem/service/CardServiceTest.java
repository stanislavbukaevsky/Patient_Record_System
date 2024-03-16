package com.github.stanislavbukaevsky.patientrecordsystem.service;

import com.github.stanislavbukaevsky.patientrecordsystem.dao.Dao;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.CardRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.CardRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.CardResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.CardNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.Mapper;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Card;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Patient;
import com.github.stanislavbukaevsky.patientrecordsystem.service.impl.CardService;
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

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.CardConstantTest.*;
import static com.github.stanislavbukaevsky.patientrecordsystem.constant.PatientConstantTest.PATIENT_ONE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.lenient;

/**
 * Класс с тестами для карты пациента
 */
@ExtendWith(MockitoExtension.class)
public class CardServiceTest {
    @Mock
    private Dao<Card, Long> cardDao;
    @Mock
    private Dao<Patient, Long> patientDao;
    @Mock
    private Mapper<CardRequestDto, CardRequestUpdateDto, CardResponseDto, Card> cardMapper;
    @InjectMocks
    private CardService cardService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cardService = new CardService(cardDao, patientDao);
    }

    @Test
    public void saveCardTest() {
        assertNotNull(cardDao);
        lenient().when(cardDao.save(any(Card.class))).thenReturn(CARD_ONE);
        lenient().when(cardMapper.mappingToDto(any(Card.class))).thenReturn(CARD_RESPONSE_DTO_ONE);
        lenient().when(patientDao.findById(any(Long.class))).thenReturn(Optional.of(PATIENT_ONE));

        assertEquals(ID_CARD_ONE, CARD_ONE.getId());
        assertEquals(APPOINTMENTS_CARD_ONE, CARD_ONE.getAppointments());
        assertEquals(ANALYZES_CARD_ONE, CARD_ONE.getAnalyzes());
        assertEquals(CARD_RESPONSE_DTO_ONE, cardService.save(CARD_REQUEST_DTO_ONE));

        assertThrows(CardNotFoundException.class, () -> cardService.save(null));
    }

    @Test
    public void updateCardTest() {
        assertNotNull(cardDao);
        lenient().when(cardDao.findById(any(Long.class))).thenReturn(Optional.of(CARD_ONE));
        lenient().when(cardDao.update(any(Card.class))).thenReturn(CARD_ONE);
        lenient().when(cardMapper.mappingToDto(any(Card.class))).thenReturn(CARD_RESPONSE_DTO_ONE);
        lenient().when(patientDao.findById(any(Long.class))).thenReturn(Optional.of(PATIENT_ONE));

        assertEquals(ID_CARD_ONE, CARD_ONE.getId());
        assertEquals(APPOINTMENTS_CARD_ONE, CARD_ONE.getAppointments());
        assertEquals(ANALYZES_CARD_ONE, CARD_ONE.getAnalyzes());
        assertEquals(CARD_RESPONSE_DTO_ONE, cardService.update(CARD_REQUEST_UPDATE_DTO));
        assertEquals(ID_CARD_ONE, CARD_REQUEST_UPDATE_DTO.getId());

        assertThrows(CardNotFoundException.class, () -> cardService.findById(null));
        assertThrows(CardNotFoundException.class, () -> cardService.update(null));
    }

    @Test
    public void findByIdCardTest() {
        assertNotNull(cardDao);
        lenient().when(cardDao.findById(ID_CARD_ONE)).thenReturn(Optional.of(CARD_ONE));
        lenient().when(cardMapper.mappingToDto(any(Card.class))).thenReturn(CARD_RESPONSE_DTO_ONE);
        Assertions.assertThat(CARD_RESPONSE_DTO_ONE).isEqualTo(cardService.findById(ID_CARD_ONE));

        CardResponseDto actual = cardService.findById(ID_CARD_ONE);
        Assertions.assertThat(actual.getId()).isEqualTo(CARD_ONE.getId());
        Assertions.assertThat(actual.getPatient()).isEqualTo(PATIENT_ONE);
        Assertions.assertThat(actual.getAppointments()).isEqualTo(CARD_ONE.getAppointments());
        Assertions.assertThat(actual.getAnalyzes()).isEqualTo(CARD_ONE.getAnalyzes());
        Assertions.assertThatExceptionOfType(CardNotFoundException.class)
                .isThrownBy(() -> cardService.findById(ID_CARD_NEGATIVE));
    }

    @Test
    public void findAllCardsTest() {
        assertNotNull(cardDao);
        lenient().when(cardDao.findAll()).thenReturn(List.of(CARD_ONE, CARD_TWO));
        lenient().when(cardMapper.mappingToListDto(anyList())).thenReturn(List.of(CARD_RESPONSE_DTO_ONE, CARD_RESPONSE_DTO_TWO));
        Assertions.assertThat(List.of(CARD_RESPONSE_DTO_ONE, CARD_RESPONSE_DTO_TWO)).isEqualTo(cardService.findAll());

        List<CardResponseDto> actual = cardService.findAll();
        Assertions.assertThat(actual.get(0).getId()).isEqualTo(CARD_ONE.getId());
        Assertions.assertThat(actual.get(1).getId()).isEqualTo(CARD_TWO.getId());
    }

    @Test
    public void deleteCardTest() {
        assertNotNull(cardDao);

        lenient().when(cardDao.findById(ID_CARD_ONE)).thenReturn(Optional.of(CARD_ONE));
        String expected = cardService.delete(ID_CARD_ONE);
        String actual = cardService.delete(ID_CARD_ONE);
        Assertions.assertThat(actual).isEqualTo(expected);
        lenient().when(cardDao.findById(ID_CARD_ONE)).thenReturn(Optional.of(CARD_ONE));

        expected = null;
        actual = cardService.delete(ID_CARD_ONE);
        Assertions.assertThat(actual).isEqualTo(expected);
        assertThrows(CardNotFoundException.class, () -> cardService.delete(null));
    }
}
