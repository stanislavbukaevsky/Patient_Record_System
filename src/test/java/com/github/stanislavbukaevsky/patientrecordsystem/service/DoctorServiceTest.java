package com.github.stanislavbukaevsky.patientrecordsystem.service;

import com.github.stanislavbukaevsky.patientrecordsystem.dao.Dao;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.DoctorNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.mapper.Mapper;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Doctor;
import com.github.stanislavbukaevsky.patientrecordsystem.service.impl.DoctorService;
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

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.DoctorConstantTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.lenient;

/**
 * Класс с тестами для врача
 */
@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {
    @Mock
    private Dao<Doctor, Long> doctorDao;
    @Mock
    private Mapper<DoctorRequestDto, DoctorRequestUpdateDto, DoctorResponseDto, Doctor> doctorMapper;
    @InjectMocks
    private DoctorService doctorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        doctorService = new DoctorService(doctorDao);
    }

    @Test
    public void saveDoctorTest() {
        assertNotNull(doctorDao);
        lenient().when(doctorDao.save(any(Doctor.class))).thenReturn(DOCTOR_ONE);
        lenient().when(doctorMapper.mappingToDto(any(Doctor.class))).thenReturn(DOCTOR_RESPONSE_DTO_ONE);

        assertEquals(ID_DOCTOR_ONE, DOCTOR_ONE.getId());
        assertEquals(FIRST_NAME_DOCTOR_ONE, DOCTOR_ONE.getFirstName());
        assertEquals(MIDDLE_NAME_DOCTOR_ONE, DOCTOR_ONE.getMiddleName());
        assertEquals(LAST_NAME_DOCTOR_ONE, DOCTOR_ONE.getLastName());
        assertEquals(SPECIALIZATION_DOCTOR_ONE, DOCTOR_ONE.getSpecialization());
        assertEquals(OFFICE_TEST_DOCTOR_ONE, DOCTOR_ONE.getOffice());
        assertEquals(DOCTOR_RESPONSE_DTO_ONE, doctorService.save(DOCTOR_REQUEST_DTO_ONE));

        assertThrows(DoctorNotFoundException.class, () -> doctorService.save(null));
    }

    @Test
    public void updateDoctorTest() {
        assertNotNull(doctorDao);
        lenient().when(doctorDao.findById(any(Long.class))).thenReturn(Optional.of(DOCTOR_ONE));
        lenient().when(doctorDao.update(any(Doctor.class))).thenReturn(DOCTOR_ONE);
        lenient().when(doctorMapper.mappingToDto(any(Doctor.class))).thenReturn(DOCTOR_RESPONSE_DTO_ONE);

        assertEquals(ID_DOCTOR_ONE, DOCTOR_ONE.getId());
        assertEquals(FIRST_NAME_DOCTOR_ONE, DOCTOR_ONE.getFirstName());
        assertEquals(MIDDLE_NAME_DOCTOR_ONE, DOCTOR_ONE.getMiddleName());
        assertEquals(LAST_NAME_DOCTOR_ONE, DOCTOR_ONE.getLastName());
        assertEquals(SPECIALIZATION_DOCTOR_ONE, DOCTOR_ONE.getSpecialization());
        assertEquals(OFFICE_TEST_DOCTOR_ONE, DOCTOR_ONE.getOffice());
        assertEquals(DOCTOR_RESPONSE_DTO_ONE, doctorService.update(DOCTOR_REQUEST_UPDATE_DTO));
        assertEquals(ID_DOCTOR_ONE, DOCTOR_REQUEST_UPDATE_DTO.getId());

        assertThrows(DoctorNotFoundException.class, () -> doctorService.findById(null));
        assertThrows(DoctorNotFoundException.class, () -> doctorService.update(null));
    }

    @Test
    public void findByIdDoctorTest() {
        assertNotNull(doctorDao);
        lenient().when(doctorDao.findById(ID_DOCTOR_ONE)).thenReturn(Optional.of(DOCTOR_ONE));
        lenient().when(doctorMapper.mappingToDto(any(Doctor.class))).thenReturn(DOCTOR_RESPONSE_DTO_ONE);
        Assertions.assertThat(DOCTOR_RESPONSE_DTO_ONE).isEqualTo(doctorService.findById(ID_DOCTOR_ONE));

        DoctorResponseDto actual = doctorService.findById(ID_DOCTOR_ONE);
        Assertions.assertThat(actual.getId()).isEqualTo(DOCTOR_ONE.getId());
        Assertions.assertThat(actual.getFirstName()).isEqualTo(DOCTOR_ONE.getFirstName());
        Assertions.assertThat(actual.getMiddleName()).isEqualTo(DOCTOR_ONE.getMiddleName());
        Assertions.assertThat(actual.getLastName()).isEqualTo(DOCTOR_ONE.getLastName());
        Assertions.assertThat(actual.getSpecialization()).isEqualTo(DOCTOR_ONE.getSpecialization());
        Assertions.assertThat(actual.getOffice()).isEqualTo(DOCTOR_ONE.getOffice());
        Assertions.assertThatExceptionOfType(DoctorNotFoundException.class)
                .isThrownBy(() -> doctorService.findById(ID_DOCTOR_NEGATIVE));
    }

    @Test
    public void findAllDoctorsTest() {
        assertNotNull(doctorDao);
        lenient().when(doctorDao.findAll()).thenReturn(List.of(DOCTOR_ONE, DOCTOR_TWO));
        lenient().when(doctorMapper.mappingToListDto(anyList())).thenReturn(List.of(DOCTOR_RESPONSE_DTO_ONE, DOCTOR_RESPONSE_DTO_TWO));
        Assertions.assertThat(List.of(DOCTOR_RESPONSE_DTO_ONE, DOCTOR_RESPONSE_DTO_TWO)).isEqualTo(doctorService.findAll());

        List<DoctorResponseDto> actual = doctorService.findAll();
        Assertions.assertThat(actual.get(0).getId()).isEqualTo(DOCTOR_ONE.getId());
        Assertions.assertThat(actual.get(1).getId()).isEqualTo(DOCTOR_TWO.getId());
    }

    @Test
    public void deleteDoctorTest() {
        assertNotNull(doctorDao);

        lenient().when(doctorDao.findById(ID_DOCTOR_ONE)).thenReturn(Optional.of(DOCTOR_ONE));
        String expected = doctorService.delete(ID_DOCTOR_ONE);
        String actual = doctorService.delete(ID_DOCTOR_ONE);
        Assertions.assertThat(actual).isEqualTo(expected);
        lenient().when(doctorDao.findById(ID_DOCTOR_ONE)).thenReturn(Optional.of(DOCTOR_ONE));

        expected = null;
        actual = doctorService.delete(ID_DOCTOR_ONE);
        Assertions.assertThat(actual).isEqualTo(expected);
        assertThrows(DoctorNotFoundException.class, () -> doctorService.delete(null));
    }
}
