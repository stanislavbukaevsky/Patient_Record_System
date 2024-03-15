package com.github.stanislavbukaevsky.patientrecordsystem.parser;

/**
 * Обобщенный интерфейс для парсинга
 *
 * @param <L> объект, который хотим получить
 * @param <S> объект, который хотим преобразовать
 */
public interface Parser<L, S> {
    S parseString(L l);

    L parseDateAndTime(S s);
}
