package com.github.stanislavbukaevsky.patientrecordsystem.dto;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CardRequestUpdateDto {
    private Long id;
    private Long patientId;
    private String appointments;
    private String analyzes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardRequestUpdateDto that = (CardRequestUpdateDto) o;
        return Objects.equals(id, that.id) && Objects.equals(patientId, that.patientId) && Objects.equals(appointments, that.appointments) && Objects.equals(analyzes, that.analyzes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientId, appointments, analyzes);
    }
}
