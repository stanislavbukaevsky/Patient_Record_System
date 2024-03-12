package com.github.stanislavbukaevsky.patientrecordsystem.dto;

import com.github.stanislavbukaevsky.patientrecordsystem.model.Doctor;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Patient;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDto {
    private Long id;
    private Doctor doctor;
    private Patient patient;
    private LocalDateTime dateAdmission;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketResponseDto that = (TicketResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(doctor, that.doctor) && Objects.equals(patient, that.patient) && Objects.equals(dateAdmission, that.dateAdmission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctor, patient, dateAdmission);
    }
}
