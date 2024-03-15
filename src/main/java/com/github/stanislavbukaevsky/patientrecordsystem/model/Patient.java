package com.github.stanislavbukaevsky.patientrecordsystem.model;

import lombok.*;

import java.util.Objects;

/**
 * Модель пациента
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String dateBirth;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id) && Objects.equals(firstName, patient.firstName) && Objects.equals(middleName, patient.middleName) && Objects.equals(lastName, patient.lastName) && Objects.equals(dateBirth, patient.dateBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, middleName, lastName, dateBirth);
    }
}
