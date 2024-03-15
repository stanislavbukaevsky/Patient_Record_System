package com.github.stanislavbukaevsky.patientrecordsystem.dto;

import lombok.*;

import java.util.Objects;

/**
 * DTO для запроса пользователя на сохранение врача
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequestDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private String specialization;
    private Integer office;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorRequestDto that = (DoctorRequestDto) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(middleName, that.middleName) && Objects.equals(lastName, that.lastName) && Objects.equals(specialization, that.specialization) && Objects.equals(office, that.office);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, lastName, specialization, office);
    }
}
