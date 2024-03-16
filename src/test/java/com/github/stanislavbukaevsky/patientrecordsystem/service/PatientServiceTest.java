package com.github.stanislavbukaevsky.patientrecordsystem.service;

import com.github.stanislavbukaevsky.patientrecordsystem.dao.Dao;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.PatientRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.PatientRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.PatientResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.PatientNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.Mapper;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Patient;
import com.github.stanislavbukaevsky.patientrecordsystem.service.impl.PatientService;
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

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.PatientConstantTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Класс с тестами для пациента
 */
@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {
    @Mock
    private Dao<Patient, Long> patientDao;
    @Mock
    private Mapper<PatientRequestDto, PatientRequestUpdateDto, PatientResponseDto, Patient> patientMapper;
    @InjectMocks
    private PatientService patientService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        patientService = new PatientService(patientDao);
    }

    @Test
    public void savePatientTest() {
        assertNotNull(patientDao);
        lenient().when(patientDao.save(any(Patient.class))).thenReturn(PATIENT_ONE);
        lenient().when(patientMapper.mappingToDto(any(Patient.class))).thenReturn(PATIENT_RESPONSE_DTO_ONE);

        assertEquals(ID_PATIENT_ONE, PATIENT_ONE.getId());
        assertEquals(FIRST_NAME_PATIENT_ONE, PATIENT_ONE.getFirstName());
        assertEquals(MIDDLE_NAME_PATIENT_ONE, PATIENT_ONE.getMiddleName());
        assertEquals(LAST_NAME_PATIENT_ONE, PATIENT_ONE.getLastName());
        assertEquals(DATE_BIRTH_PATIENT_ONE, PATIENT_ONE.getDateBirth());
        assertEquals(PATIENT_RESPONSE_DTO_ONE, patientService.save(PATIENT_REQUEST_DTO_ONE));

        assertThrows(PatientNotFoundException.class, () -> patientService.save(null));
    }

    @Test
    public void updatePatientTest() {
        assertNotNull(patientDao);
        lenient().when(patientDao.findById(any(Long.class))).thenReturn(Optional.of(PATIENT_ONE));
        lenient().when(patientDao.update(any(Patient.class))).thenReturn(PATIENT_ONE);
        lenient().when(patientMapper.mappingToDto(any(Patient.class))).thenReturn(PATIENT_RESPONSE_DTO_ONE);

        assertEquals(ID_PATIENT_ONE, PATIENT_ONE.getId());
        assertEquals(FIRST_NAME_PATIENT_ONE, PATIENT_ONE.getFirstName());
        assertEquals(MIDDLE_NAME_PATIENT_ONE, PATIENT_ONE.getMiddleName());
        assertEquals(LAST_NAME_PATIENT_ONE, PATIENT_ONE.getLastName());
        assertEquals(DATE_BIRTH_PATIENT_ONE, PATIENT_ONE.getDateBirth());
        assertEquals(PATIENT_RESPONSE_DTO_ONE, patientService.update(PATIENT_REQUEST_UPDATE_DTO));
        assertEquals(ID_PATIENT_ONE, PATIENT_REQUEST_UPDATE_DTO.getId());

        assertThrows(PatientNotFoundException.class, () -> patientService.findById(null));
        assertThrows(PatientNotFoundException.class, () -> patientService.update(null));
    }

    @Test
    public void findByIdPatientTest() {
        assertNotNull(patientDao);
        lenient().when(patientDao.findById(ID_PATIENT_ONE)).thenReturn(Optional.of(PATIENT_ONE));
        lenient().when(patientMapper.mappingToDto(any(Patient.class))).thenReturn(PATIENT_RESPONSE_DTO_ONE);
        Assertions.assertThat(PATIENT_RESPONSE_DTO_ONE).isEqualTo(patientService.findById(ID_PATIENT_ONE));

        PatientResponseDto actual = patientService.findById(ID_PATIENT_ONE);
        Assertions.assertThat(actual.getId()).isEqualTo(PATIENT_ONE.getId());
        Assertions.assertThat(actual.getFirstName()).isEqualTo(PATIENT_ONE.getFirstName());
        Assertions.assertThat(actual.getMiddleName()).isEqualTo(PATIENT_ONE.getMiddleName());
        Assertions.assertThat(actual.getLastName()).isEqualTo(PATIENT_ONE.getLastName());
        Assertions.assertThat(actual.getDateBirth()).isEqualTo(PATIENT_ONE.getDateBirth());
        Assertions.assertThatExceptionOfType(PatientNotFoundException.class)
                .isThrownBy(() -> patientService.findById(ID_PATIENT_NEGATIVE));
    }

    @Test
    public void findAllPatientsTest() {
        assertNotNull(patientDao);
        lenient().when(patientDao.findAll()).thenReturn(List.of(PATIENT_ONE, PATIENT_TWO));
        lenient().when(patientMapper.mappingToListDto(anyList())).thenReturn(List.of(PATIENT_RESPONSE_DTO_ONE, PATIENT_RESPONSE_DTO_TWO));
        Assertions.assertThat(List.of(PATIENT_RESPONSE_DTO_ONE, PATIENT_RESPONSE_DTO_TWO)).isEqualTo(patientService.findAll());

        List<PatientResponseDto> actual = patientService.findAll();
        Assertions.assertThat(actual.get(0).getId()).isEqualTo(PATIENT_ONE.getId());
        Assertions.assertThat(actual.get(1).getId()).isEqualTo(PATIENT_TWO.getId());
    }

    @Test
    public void deletePatientTest() {
        assertNotNull(patientDao);

        lenient().when(patientDao.findById(ID_PATIENT_ONE)).thenReturn(Optional.of(PATIENT_ONE));
        String expected = patientService.delete(ID_PATIENT_ONE);
        String actual = patientService.delete(ID_PATIENT_ONE);
        Assertions.assertThat(actual).isEqualTo(expected);
        lenient().when(patientDao.findById(ID_PATIENT_ONE)).thenReturn(Optional.of(PATIENT_ONE));

        expected = null;
        actual = patientService.delete(ID_PATIENT_ONE);
        Assertions.assertThat(actual).isEqualTo(expected);
        assertThrows(PatientNotFoundException.class, () -> patientService.delete(null));
    }
}
