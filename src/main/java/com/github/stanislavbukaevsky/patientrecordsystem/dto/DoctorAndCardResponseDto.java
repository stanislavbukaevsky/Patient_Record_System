package com.github.stanislavbukaevsky.patientrecordsystem.dto;

import com.github.stanislavbukaevsky.patientrecordsystem.model.Card;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Doctor;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DoctorAndCardResponseDto {
    private Long id;
    private Doctor doctor;
    private Card card;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorAndCardResponseDto that = (DoctorAndCardResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(doctor, that.doctor) && Objects.equals(card, that.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctor, card);
    }
}
