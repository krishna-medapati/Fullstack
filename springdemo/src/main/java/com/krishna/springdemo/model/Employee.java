package com.krishna.springdemo.model;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    
    @Id
    @Column(name = "emp_id")
    private int empid;
    
    @Column(name = "emp_name")
    private String empname;
    
    @Column(name = "salary")
    private double salary;
    
    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    public Employee() {
    }

    public void setEmpId(int empid) {
        this.empid = empid;
    }
    
    public int getEmpId() {
        return empid;
    }
    
    public void setEmpName(String empname) {
        this.empname = empname;
    }
    
    public String getEmpName() {
        return empname;
    }
    
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    public double getSalary() {
        return salary;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
    }
    
    public Department getDepartment() {
        return department;
    }
    
    @Override
    public String toString() {
        return "Employee [empId=" + empid + ", empName=" + empname + 
               ", salary=" + salary + ", department=" + 
               (department != null ? department.getDeptName() : "null") + "]";
    }
}