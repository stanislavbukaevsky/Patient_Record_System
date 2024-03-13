package com.github.stanislavbukaevsky.patientrecordsystem.dto;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DoctorAndCardRequestUpdateDto {
    private Long id;
    private Long doctorId;
    private Long cardId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorAndCardRequestUpdateDto that = (DoctorAndCardRequestUpdateDto) o;
        return Objects.equals(id, that.id) && Objects.equals(doctorId, that.doctorId) && Objects.equals(cardId, that.cardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctorId, cardId);
    }
}
