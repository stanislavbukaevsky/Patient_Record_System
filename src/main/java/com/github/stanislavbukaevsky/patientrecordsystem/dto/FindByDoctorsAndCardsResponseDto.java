package com.github.stanislavbukaevsky.patientrecordsystem.dto;

import com.github.stanislavbukaevsky.patientrecordsystem.model.Card;
import com.github.stanislavbukaevsky.patientrecordsystem.model.Doctor;
import lombok.*;

import java.util.List;
import java.util.Objects;

/**
 * DTO для ответа пользователю с информацией о враче и карте пациента
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FindByDoctorsAndCardsResponseDto {
    private Long id;
    private List<Doctor> doctors;
    private List<Card> cards;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindByDoctorsAndCardsResponseDto that = (FindByDoctorsAndCardsResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(doctors, that.doctors) && Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctors, cards);
    }
}
