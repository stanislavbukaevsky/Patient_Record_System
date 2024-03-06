package com.github.stanislavbukaevsky.patientrecordsystem.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private Long id;
    private Doctor doctor;
    private Patient patient;
    private LocalDateTime dateAdmission;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) && Objects.equals(doctor, ticket.doctor) && Objects.equals(patient, ticket.patient) && Objects.equals(dateAdmission, ticket.dateAdmission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctor, patient, dateAdmission);
    }
}
