package com.github.stanislavbukaevsky.patientrecordsystem.dto;

import lombok.*;

import java.util.Objects;

/**
 * DTO для ответа пользователю с информацией о враче
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResponseDto {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String specialization;
    private Integer office;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorResponseDto that = (DoctorResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(middleName, that.middleName) && Objects.equals(lastName, that.lastName) && Objects.equals(specialization, that.specialization) && Objects.equals(office, that.office);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, middleName, lastName, specialization, office);
    }
}
