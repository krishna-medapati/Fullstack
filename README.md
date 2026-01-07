# Java JDBC Maven Project

This is a basic Maven project setup with Java and JDBC connectivity.

## Project Structure

```
project-root/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/
│   │   │       ├── MainApplication.java
│   │   │       ├── JDBCCrud.java
│   │   │       ├── dao/
│   │   │       │   └── UserDAO.java
│   │   │       └── database/
│   │   │           └── DatabaseConnection.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── schema.sql
│   │       └── setup.sql
│   └── test/
│       ├── java/
│       └── resources/
```

## Prerequisites

1. Java 17 or higher
2. Maven 3.6 or higher
3. MySQL Server (or modify the JDBC driver for your database)

## Setup Instructions

1. **Database Setup**:
   - Install and start your MySQL server
   - Create a database named `your_database` (or modify in `application.properties`)
   - Execute the `schema.sql` script to create tables
   - Alternatively, run the JDBCCrud application which will create tables automatically

2. **Configuration**:
   - Update `src/main/resources/application.properties` with your database credentials
   - For the JDBCCrud example, modify the database credentials directly in the JDBCCrud.java file

3. **Build the Project**:
   ```bash
   mvn clean install
   ```

4. **Run the Applications**:
   
   To run the original example:
   ```bash
   mvn exec:java -Dexec.mainClass="com.example.MainApplication"
   ```
   
   To run the JDBC CRUD example:
   ```bash
   mvn exec:java -Dexec.mainClass="com.example.JDBCCrud"
   ```

## Dependencies

- MySQL Connector/J 8.0.33 (JDBC driver)
- JUnit 4.13.2 (for testing)

## Key Components

- `DatabaseConnection.java`: Manages database connections using properties file
- `UserDAO.java`: Data Access Object for user-related database operations
- `MainApplication.java`: Sample application demonstrating JDBC usage
- `JDBCCrud.java`: Complete CRUD example with department and employee management
- `setup.sql`: Sample database schema and data for testing

## Customization

To use a different database:
1. Update the JDBC driver dependency in `pom.xml`
2. Modify the connection URL format in `application.properties`
3. Adjust the SQL syntax in DAO classes if needed