-- Create the test database
CREATE DATABASE IF NOT EXISTS testdb;

-- Use the test database
USE testdb;

-- Create dept table
CREATE TABLE IF NOT EXISTS dept (
    dept_id INT PRIMARY KEY,
    dept_name VARCHAR(50)
);

-- Create employee table
CREATE TABLE IF NOT EXISTS employee (
    emp_id INT PRIMARY KEY,
    emp_name VARCHAR(50),
    salary DOUBLE,
    dept_id INT,
    FOREIGN KEY (dept_id) REFERENCES dept(dept_id)
);

-- Insert sample data
INSERT INTO dept (dept_id, dept_name) VALUES 
(1, 'Engineering'),
(2, 'Marketing'),
(3, 'Human Resources');

INSERT INTO employee (emp_id, emp_name, salary, dept_id) VALUES
(101, 'John Doe', 75000.0, 1),
(102, 'Jane Smith', 65000.0, 2),
(103, 'Robert Johnson', 70000.0, 1),
(104, 'Emily Davis', 60000.0, 3);