package com.github.stanislavbukaevsky.patientrecordsystem.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
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
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id) && Objects.equals(firstName, doctor.firstName) && Objects.equals(middleName, doctor.middleName) && Objects.equals(lastName, doctor.lastName) && Objects.equals(specialization, doctor.specialization) && Objects.equals(office, doctor.office);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, middleName, lastName, specialization, office);
    }
}
