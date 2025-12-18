package com.example.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static final String PROPERTIES_FILE = "application.properties";
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    
    private static Connection connection = null;
    
    static {
        loadProperties();
    }
    
    private static void loadProperties() {
        try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            Properties prop = new Properties();
            if (input == null) {
                System.err.println("Unable to find " + PROPERTIES_FILE);
                // Set default values
                URL = "jdbc:mysql://localhost:3306/your_database";
                USERNAME = "your_username";
                PASSWORD = "your_password";
                return;
            }
            prop.load(input);
            URL = prop.getProperty("db.url");
            USERNAME = prop.getProperty("db.username");
            PASSWORD = prop.getProperty("db.password");
        } catch (IOException ex) {
            System.err.println("Error loading properties file: " + ex.getMessage());
            // Set default values
            URL = "jdbc:mysql://localhost:3306/your_database";
            USERNAME = "your_username";
            PASSWORD = "your_password";
        }
    }
    
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Load the JDBC driver (optional for newer versions of JDBC)
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new SQLException("MySQL JDBC Driver not found", e);
            }
        }
        return connection;
    }
    
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
            }
        }
    }
}