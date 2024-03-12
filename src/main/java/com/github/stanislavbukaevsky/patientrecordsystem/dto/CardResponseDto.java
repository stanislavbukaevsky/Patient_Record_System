package com.github.stanislavbukaevsky.patientrecordsystem.dto;

import com.github.stanislavbukaevsky.patientrecordsystem.model.Patient;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CardResponseDto {
    private Long id;
    private Patient patient;
    private String appointments;
    private String analyzes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardResponseDto that = (CardResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(patient, that.patient) && Objects.equals(appointments, that.appointments) && Objects.equals(analyzes, that.analyzes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, appointments, analyzes);
    }
}
