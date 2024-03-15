package com.github.stanislavbukaevsky.patientrecordsystem.dto;

import lombok.*;

import java.util.Objects;

/**
 * DTO для запроса пользователя на сохранение врача и карты пациента
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DoctorAndCardRequestDto {
    private Long doctorId;
    private Long cardId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorAndCardRequestDto that = (DoctorAndCardRequestDto) o;
        return Objects.equals(doctorId, that.doctorId) && Objects.equals(cardId, that.cardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, cardId);
    }
}
