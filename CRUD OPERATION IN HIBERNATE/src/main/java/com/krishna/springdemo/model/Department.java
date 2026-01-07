package com.krishna.springdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {
    
    @Id
    @Column(name = "dept_id")
    private int deptId;
    
    @Column(name = "dept_name")
    private String deptName;
    
    @Column(name = "location")
    private String location;
    
    public Department() {
    }
    
    public int getDeptId() {
        return deptId;
    }
    
    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }
    
    public String getDeptName() {
        return deptName;
    }
    
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    @Override
    public String toString() {
        return "Department [deptId=" + deptId + ", deptName=" + deptName + 
               ", location=" + location + "]";
    }
}