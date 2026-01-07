package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false";
        String user = "root";
        String password = "Vanitas@41";
        
        System.out.println("Attempting to connect to database...");
        System.out.println("URL: " + url);
        System.out.println("User: " + user);
        
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("SUCCESS: Connected to the database!");
            connection.close();
        } catch (SQLException e) {
            System.out.println("FAILED: Could not connect to the database");
            System.out.println("Error: " + e.getMessage());
            
            // Try with different credentials
            testAlternativeCredentials();
        }
    }
    
    private static void testAlternativeCredentials() {
        String[][] credentials = {
            {"root", "Vanitas@41"},
            {"root", "root"},
            {"root", ""},
            {"appuser", "apppassword"}
        };
        
        String url = "jdbc:mysql://localhost:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false";
        
        for (String[] cred : credentials) {
            String user = cred[0];
            String password = cred[1];
            
            System.out.println("\nTrying credentials - User: " + user + ", Password: " + password);
            
            try {
                Connection connection = DriverManager.getConnection(url, user, password);
                System.out.println("SUCCESS with User: " + user + ", Password: " + password);
                connection.close();
                return;
            } catch (SQLException e) {
                System.out.println("FAILED with User: " + user + ", Password: " + password);
            }
        }
        
        System.out.println("\nAll credential combinations failed. Please check your MySQL installation and credentials.");
    }
}