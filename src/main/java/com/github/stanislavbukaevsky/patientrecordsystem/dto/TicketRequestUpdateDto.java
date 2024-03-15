package com.github.stanislavbukaevsky.patientrecordsystem.dto;

import lombok.*;

import java.util.Objects;

/**
 * DTO для запроса пользователя на изменение талона
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequestUpdateDto {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private String dateAdmission;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketRequestUpdateDto that = (TicketRequestUpdateDto) o;
        return Objects.equals(id, that.id) && Objects.equals(doctorId, that.doctorId) && Objects.equals(patientId, that.patientId) && Objects.equals(dateAdmission, that.dateAdmission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctorId, patientId, dateAdmission);
    }
}
