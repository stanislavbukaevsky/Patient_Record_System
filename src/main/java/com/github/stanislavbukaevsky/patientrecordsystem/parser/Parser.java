package com.github.stanislavbukaevsky.patientrecordsystem.parser;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Parser<L, S> {
    S parseString(L l);

    L parseDateAndTime(S s) throws JsonProcessingException;
}
