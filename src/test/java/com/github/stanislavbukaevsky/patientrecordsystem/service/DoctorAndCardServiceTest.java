package com.github.stanislavbukaevsky.patientrecordsystem.service;

import com.github.stanislavbukaevsky.patientrecordsystem.dao.Dao;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorAndCardRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorAndCardRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorAndCardResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.DoctorAndCardNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.Mapper;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Card;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Doctor;
import com.github.stanislavbukaevsky.patientrecordsystem.model.DoctorAndCard;
import com.github.stanislavbukaevsky.patientrecordsystem.service.impl.DoctorAndCardService;
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

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.CardConstantTest.CARD_ONE;
import static com.github.stanislavbukaevsky.patientrecordsystem.constant.DoctorAndCardConstantTest.*;
import static com.github.stanislavbukaevsky.patientrecordsystem.constant.DoctorConstantTest.DOCTOR_ONE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.lenient;

/**
 * Класс с тестами для врача и карты пациента
 */
@ExtendWith(MockitoExtension.class)
public class DoctorAndCardServiceTest {
    @Mock
    private Dao<DoctorAndCard, Long> doctorAndCardDao;
    @Mock
    private Dao<Doctor, Long> doctorDao;
    @Mock
    private Dao<Card, Long> cardDao;
    @Mock
    private Mapper<DoctorAndCardRequestDto, DoctorAndCardRequestUpdateDto, DoctorAndCardResponseDto, DoctorAndCard> doctorAndCardMapper;
    @InjectMocks
    private DoctorAndCardService doctorAndCardService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        doctorAndCardService = new DoctorAndCardService(doctorAndCardDao, doctorDao, cardDao);
    }

    @Test
    public void saveDoctorAndCardTest() {
        assertNotNull(doctorAndCardDao);
        lenient().when(doctorAndCardDao.save(any(DoctorAndCard.class))).thenReturn(DOCTOR_AND_CARD_ONE);
        lenient().when(doctorAndCardMapper.mappingToDto(any(DoctorAndCard.class))).thenReturn(DOCTOR_AND_CARD_RESPONSE_DTO_ONE);
        lenient().when(doctorDao.findById(any(Long.class))).thenReturn(Optional.of(DOCTOR_ONE));
        lenient().when(cardDao.findById(any(Long.class))).thenReturn(Optional.of(CARD_ONE));

        assertEquals(ID_DOCTOR_AND_CARD_ONE, DOCTOR_AND_CARD_ONE.getId());
        assertEquals(DOCTOR_ONE, DOCTOR_AND_CARD_ONE.getDoctor());
        assertEquals(CARD_ONE, DOCTOR_AND_CARD_ONE.getCard());
        assertEquals(DOCTOR_AND_CARD_RESPONSE_DTO_ONE, doctorAndCardService.save(DOCTOR_AND_CARD_REQUEST_DTO_ONE));

        assertThrows(DoctorAndCardNotFoundException.class, () -> doctorAndCardService.save(null));
    }

    @Test
    public void updateDoctorAndCardTest() {
        assertNotNull(doctorAndCardDao);
        lenient().when(doctorAndCardDao.findById(any(Long.class))).thenReturn(Optional.of(DOCTOR_AND_CARD_ONE));
        lenient().when(doctorAndCardDao.update(any(DoctorAndCard.class))).thenReturn(DOCTOR_AND_CARD_ONE);
        lenient().when(doctorAndCardMapper.mappingToDto(any(DoctorAndCard.class))).thenReturn(DOCTOR_AND_CARD_RESPONSE_DTO_ONE);
        lenient().when(doctorDao.findById(any(Long.class))).thenReturn(Optional.of(DOCTOR_ONE));
        lenient().when(cardDao.findById(any(Long.class))).thenReturn(Optional.of(CARD_ONE));

        assertEquals(ID_DOCTOR_AND_CARD_ONE, DOCTOR_AND_CARD_ONE.getId());
        assertEquals(DOCTOR_ONE, DOCTOR_AND_CARD_ONE.getDoctor());
        assertEquals(CARD_ONE, DOCTOR_AND_CARD_ONE.getCard());
        assertEquals(DOCTOR_AND_CARD_RESPONSE_DTO_ONE, doctorAndCardService.update(DOCTOR_AND_CARD_REQUEST_UPDATE_DTO));
        assertEquals(ID_DOCTOR_AND_CARD_ONE, DOCTOR_AND_CARD_REQUEST_UPDATE_DTO.getId());

        assertThrows(DoctorAndCardNotFoundException.class, () -> doctorAndCardService.findById(null));
        assertThrows(DoctorAndCardNotFoundException.class, () -> doctorAndCardService.update(null));
    }

    @Test
    public void findByIdDoctorAndCardTest() {
        assertNotNull(doctorAndCardDao);
        lenient().when(doctorAndCardDao.findById(ID_DOCTOR_AND_CARD_ONE)).thenReturn(Optional.of(DOCTOR_AND_CARD_ONE));
        lenient().when(doctorAndCardMapper.mappingToDto(any(DoctorAndCard.class))).thenReturn(DOCTOR_AND_CARD_RESPONSE_DTO_ONE);
        Assertions.assertThat(DOCTOR_AND_CARD_RESPONSE_DTO_ONE).isEqualTo(doctorAndCardService.findById(ID_DOCTOR_AND_CARD_ONE));

        DoctorAndCardResponseDto actual = doctorAndCardService.findById(ID_DOCTOR_AND_CARD_ONE);
        Assertions.assertThat(actual.getId()).isEqualTo(DOCTOR_AND_CARD_ONE.getId());
        Assertions.assertThat(actual.getDoctor()).isEqualTo(DOCTOR_ONE);
        Assertions.assertThat(actual.getCard()).isEqualTo(CARD_ONE);
        Assertions.assertThatExceptionOfType(DoctorAndCardNotFoundException.class)
                .isThrownBy(() -> doctorAndCardService.findById(ID_DOCTOR_AND_CARD_NEGATIVE));
    }

    @Test
    public void findAllDoctorsAndCardsTest() {
        assertNotNull(doctorAndCardDao);
        lenient().when(doctorAndCardDao.findAll()).thenReturn(List.of(DOCTOR_AND_CARD_ONE, DOCTOR_AND_CARD_TWO));
        lenient().when(doctorAndCardMapper.mappingToListDto(anyList())).thenReturn(List.of(DOCTOR_AND_CARD_RESPONSE_DTO_ONE, DOCTOR_AND_CARD_RESPONSE_DTO_TWO));
        Assertions.assertThat(List.of(DOCTOR_AND_CARD_RESPONSE_DTO_ONE, DOCTOR_AND_CARD_RESPONSE_DTO_TWO)).isEqualTo(doctorAndCardService.findAll());

        List<DoctorAndCardResponseDto> actual = doctorAndCardService.findAll();
        Assertions.assertThat(actual.get(0).getId()).isEqualTo(DOCTOR_AND_CARD_ONE.getId());
        Assertions.assertThat(actual.get(1).getId()).isEqualTo(DOCTOR_AND_CARD_TWO.getId());
    }

    @Test
    public void deleteDoctorsAndCardsTest() {
        assertNotNull(doctorAndCardDao);

        lenient().when(doctorAndCardDao.findById(ID_DOCTOR_AND_CARD_ONE)).thenReturn(Optional.of(DOCTOR_AND_CARD_ONE));
        String expected = doctorAndCardService.delete(ID_DOCTOR_AND_CARD_ONE);
        String actual = doctorAndCardService.delete(ID_DOCTOR_AND_CARD_ONE);
        Assertions.assertThat(actual).isEqualTo(expected);
        lenient().when(doctorAndCardDao.findById(ID_DOCTOR_AND_CARD_ONE)).thenReturn(Optional.of(DOCTOR_AND_CARD_ONE));

        expected = null;
        actual = doctorAndCardService.delete(ID_DOCTOR_AND_CARD_ONE);
        Assertions.assertThat(actual).isEqualTo(expected);
        assertThrows(DoctorAndCardNotFoundException.class, () -> doctorAndCardService.delete(null));
    }
}
