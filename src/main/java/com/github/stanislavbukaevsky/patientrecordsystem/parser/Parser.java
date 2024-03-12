package com.github.stanislavbukaevsky.patientrecordsystem.parser;

public interface Parser<L, S> {
    L parseDateAndTime(S s);
}
