package com.github.stanislavbukaevsky.patientrecordsystem.model;

import lombok.*;

import java.util.List;
import java.util.Objects;

/**
 * Модель врача
 */
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
    private List<Card> cards;

    public Doctor(Long id, String firstName, String middleName, String lastName, String specialization, Integer office) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.office = office;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id) && Objects.equals(firstName, doctor.firstName) && Objects.equals(middleName, doctor.middleName) && Objects.equals(lastName, doctor.lastName) && Objects.equals(specialization, doctor.specialization) && Objects.equals(office, doctor.office) && Objects.equals(cards, doctor.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, middleName, lastName, specialization, office, cards);
    }
}
