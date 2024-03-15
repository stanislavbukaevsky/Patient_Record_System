package com.github.stanislavbukaevsky.patientrecordsystem.service;

import com.github.stanislavbukaevsky.patientrecordsystem.dao.Dao;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.PatientNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.impl.PatientMapper;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Patient;
import com.github.stanislavbukaevsky.patientrecordsystem.service.impl.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.ConstantTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.lenient;

/**
 * Класс с тестами для пациента
 */
@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {
    @Mock
    private Dao<Patient, Long> patientDao;
    @Mock
    private PatientMapper patientMapper;

    @InjectMocks
    private PatientService patientService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        patientService = PatientService.getInstance();
    }

    @Test
    public void saveTest() {
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
}
