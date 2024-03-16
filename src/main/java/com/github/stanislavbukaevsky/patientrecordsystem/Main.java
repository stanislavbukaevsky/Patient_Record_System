package com.github.stanislavbukaevsky.patientrecordsystem;

import com.github.stanislavbukaevsky.patientrecordsystem.util.CreateTablesManager;

public class Main {
    public static void main(String[] args) {
        CreateTablesManager tablesManager = CreateTablesManager.getInstance();
        tablesManager.initTables();
    }
}
