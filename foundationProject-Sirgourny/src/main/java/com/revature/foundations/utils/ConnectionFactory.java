package com.revature.foundations.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static com.revature.foundations.logger.CustomLogger.logError;

public class ConnectionFactory {

    private static ConnectionFactory connectionFactory;

    public static ConnectionFactory getInstance() {

        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }

        return connectionFactory;
    }

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            logError(e);
            System.err.println("Failed to load PostgreSQL Driver");
            throw new RuntimeException(e); // fail fast
        }
    }

    private static Properties props = new Properties();

    private ConnectionFactory() {
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
        } catch (Exception e) {
            logError(e);
            System.err.println("Failed to load database credentials from property file.");
            throw new RuntimeException(e); // fail fast for easier debugging
        }
    }

    public Connection getConnection() throws SQLException {

        Connection conn = DriverManager.getConnection(props.getProperty("db-url"),
                props.getProperty("db-username"),
                props.getProperty("db-password"));

        if (conn == null) {
            throw new RuntimeException("Could not establish a connection to the database");
        }

        return conn;

    }

}