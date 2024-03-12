package com.github.stanislavbukaevsky.patientrecordsystem.dto;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequestDto {
    private Long doctorId;
    private Long patientId;
    private String dateAdmission;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketRequestDto that = (TicketRequestDto) o;
        return Objects.equals(doctorId, that.doctorId) && Objects.equals(patientId, that.patientId) && Objects.equals(dateAdmission, that.dateAdmission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, patientId, dateAdmission);
    }
}
