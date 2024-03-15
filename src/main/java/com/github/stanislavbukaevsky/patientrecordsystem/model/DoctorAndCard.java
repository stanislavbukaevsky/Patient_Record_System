package com.github.stanislavbukaevsky.patientrecordsystem.model;

import lombok.*;

import java.util.Objects;

/**
 * Модель врача и карты пациента
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DoctorAndCard {
    private Long id;
    private Doctor doctor;
    private Card card;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorAndCard that = (DoctorAndCard) o;
        return Objects.equals(id, that.id) && Objects.equals(doctor, that.doctor) && Objects.equals(card, that.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctor, card);
    }
}
