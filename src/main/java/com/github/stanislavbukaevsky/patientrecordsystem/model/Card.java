package com.github.stanislavbukaevsky.patientrecordsystem.model;

import lombok.*;

import java.util.List;
import java.util.Objects;

/**
 * Модель карты пациента
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private Long id;
    private Patient patient;
    private List<Doctor> doctors;
    private String appointments;
    private String analyzes;

    public Card(Long id, Patient patient, String appointments, String analyzes) {
        this.id = id;
        this.patient = patient;
        this.appointments = appointments;
        this.analyzes = analyzes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(id, card.id) && Objects.equals(patient, card.patient) && Objects.equals(doctors, card.doctors) && Objects.equals(appointments, card.appointments) && Objects.equals(analyzes, card.analyzes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, doctors, appointments, analyzes);
    }
}
