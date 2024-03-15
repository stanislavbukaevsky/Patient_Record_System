package com.github.stanislavbukaevsky.patientrecordsystem.util;

import com.github.stanislavbukaevsky.patientrecordsystem.exception.ConnectionNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.DriverNotFoundException;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static com.github.stanislavbukaevsky.patientrecordsystem.constant.ExceptionTextMessageConstant.CONNECTION_NOT_FOUND_EXCEPTION_MESSAGE;
import static com.github.stanislavbukaevsky.patientrecordsystem.constant.ExceptionTextMessageConstant.DRIVER_NOT_FOUND_EXCEPTION_MESSAGE;

/**
 * Утилитный класс для создания подключения к базе данных
 */
public final class ConnectionManager {
    private static final String URL = "datasource.url";
    private static final String USERNAME = "datasource.username";
    private static final String PASSWORD = "datasource.password";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String POOL_SIZE = "datasource.pool.size";
    private static final int DEFAULT_POOL_SIZE = 10;
    private static BlockingQueue<Connection> pool;

    private ConnectionManager() {
    }

    static {
        loadDriver();
        initConnectionPool();
    }

    /**
     * Этот метод показывает подключение к базе данных
     *
     * @return Возвращает подключение к базе данных
     */
    public static Connection getConnection() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            throw new ConnectionNotFoundException(CONNECTION_NOT_FOUND_EXCEPTION_MESSAGE + e);
        }
    }

    private static Connection openConnection() {
        try {
            return DriverManager
                    .getConnection(
                            PropertiesConnection.getProperties(URL),
                            PropertiesConnection.getProperties(USERNAME),
                            PropertiesConnection.getProperties(PASSWORD));
        } catch (SQLException e) {
            throw new ConnectionNotFoundException(CONNECTION_NOT_FOUND_EXCEPTION_MESSAGE + e);
        }
    }

    private static void initConnectionPool() {
        String poolSize = PropertiesConnection.getProperties(POOL_SIZE);
        int size = poolSize == null ? DEFAULT_POOL_SIZE : Integer.parseInt(poolSize);
        pool = new ArrayBlockingQueue<>(size);

        for (int i = 0; i < size; i++) {
            Connection connection = openConnection();
            Connection proxyConnection = (Connection) Proxy.newProxyInstance(
                    ConnectionManager.class.getClassLoader(),
                    new Class[]{Connection.class},
                    (proxy, method, args) ->
                            method.getName().equals("close") ? pool.add((Connection) proxy)
                                    : method.invoke(connection, args)
            );
            pool.add(proxyConnection);
        }
    }

    private static void loadDriver() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new DriverNotFoundException(DRIVER_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }
}
