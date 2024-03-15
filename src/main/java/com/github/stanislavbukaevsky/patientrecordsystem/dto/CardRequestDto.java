package com.github.stanislavbukaevsky.patientrecordsystem.dto;

import lombok.*;

import java.util.Objects;

/**
 * DTO для запроса пользователя на сохранение карты пациента
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CardRequestDto {
    private Long patientId;
    private String appointments;
    private String analyzes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardRequestDto that = (CardRequestDto) o;
        return Objects.equals(patientId, that.patientId) && Objects.equals(appointments, that.appointments) && Objects.equals(analyzes, that.analyzes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, appointments, analyzes);
    }
}
