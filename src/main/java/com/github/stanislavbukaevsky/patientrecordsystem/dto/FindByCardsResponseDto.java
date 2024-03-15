package com.github.stanislavbukaevsky.patientrecordsystem.dto;

import com.github.stanislavbukaevsky.patientrecordsystem.model.Patient;
import lombok.*;

import java.util.List;
import java.util.Objects;

/**
 * DTO для ответа пользователю с информацией о карте пациента
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FindByCardsResponseDto {
    private Long id;
    private List<Patient> patients;
    private String appointments;
    private String analyzes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindByCardsResponseDto that = (FindByCardsResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(patients, that.patients) && Objects.equals(appointments, that.appointments) && Objects.equals(analyzes, that.analyzes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patients, appointments, analyzes);
    }
}
