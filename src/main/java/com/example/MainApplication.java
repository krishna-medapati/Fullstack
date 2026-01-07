package com.example;

import com.example.dao.UserDAO;
import com.example.database.DatabaseConnection;
import java.sql.SQLException;
import java.util.List;

public class MainApplication {
    
    public static void main(String[] args) {
        System.out.println("Java JDBC Application Started");
        
        UserDAO userDAO = new UserDAO();
        
        try {
            // Example of getting all users
            List<String> users = userDAO.getAllUsers();
            System.out.println("Current users in database:");
            for (String user : users) {
                System.out.println("- " + user);
            }
            
            // Example of inserting a new user
            boolean inserted = userDAO.insertUser("john_doe");
            if (inserted) {
                System.out.println("Successfully inserted new user");
            }
            
        } catch (SQLException e) {
            System.err.println("Database error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the database connection
            DatabaseConnection.closeConnection();
        }
        
        System.out.println("Java JDBC Application Finished");
    }
}