package com.github.stanislavbukaevsky.patientrecordsystem.dto;

import com.github.stanislavbukaevsky.patientrecordsystem.model.Doctor;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Patient;
import lombok.*;

import java.util.List;
import java.util.Objects;

/**
 * DTO для ответа пользователю с информацией о талоне
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FindByTicketsResponseDto {
    private Long id;
    private List<Doctor> doctors;
    private List<Patient> patients;
    private String dateAdmission;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindByTicketsResponseDto that = (FindByTicketsResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(doctors, that.doctors) && Objects.equals(patients, that.patients) && Objects.equals(dateAdmission, that.dateAdmission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctors, patients, dateAdmission);
    }
}
