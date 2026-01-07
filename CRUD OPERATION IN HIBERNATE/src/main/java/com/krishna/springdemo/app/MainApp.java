package com.krishna.springdemo.app;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.krishna.springdemo.model.Department;
import com.krishna.springdemo.model.Employee;

import com.krishna.springdemo.util.HibernateUtil;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("\n========================================");
            System.out.println("    EMPLOYEE & DEPARTMENT CRUD SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Add Department");
            System.out.println("2. View Department");
            System.out.println("3. Update Department");
            System.out.println("4. Delete Department");
            System.out.println("5. Add Employee");
            System.out.println("6. View Employee");
            System.out.println("7. Update Employee");
            System.out.println("8. Delete Employee");
            System.out.println("9. Exit");
            System.out.println("========================================");
            System.out.print("Enter your choice: ");
            
            
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
                choice = 0;
                continue;
            }
            
            switch (choice) {
                case 1:
                    addDepartment(sc);
                    break;
                case 2:
                    viewDepartment(sc);
                    break;
                case 3:
                    updateDepartment(sc);
                    break;
                case 4:
                    deleteDepartment(sc);
                    break;
                case 5:
                    addEmployee(sc);
                    break;
                case 6:
                    viewEmployee(sc);
                    break;
                case 7:
                    updateEmployee(sc);
                    break;
                case 8:
                    deleteEmployee(sc);
                    break;
                case 9:
                    System.out.println("\nExiting... Thank you!");
                    HibernateUtil.shutdown();
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 9);
        
        sc.close();
    }
    
    // ==================== DEPARTMENT OPERATIONS ====================
    
    private static void addDepartment(Scanner sc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        try {
            Department dept = new Department();
            System.out.print("\nEnter Department ID: ");
            dept.setDeptId(sc.nextInt());
            sc.nextLine();
            System.out.print("Enter Department Name: ");
            dept.setDeptName(sc.nextLine());
            System.out.print("Enter Location: ");
            dept.setLocation(sc.nextLine());
            
            session.save(dept);
            tx.commit();
            System.out.println("✓ Department added successfully!");
        } catch (Exception e) {
            tx.rollback();
            System.out.println("✗ Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }
    
    private static void viewDepartment(Scanner sc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            System.out.print("\nEnter Department ID: ");
            int id = sc.nextInt();
            
            Department dept = session.get(Department.class, id);
            if (dept != null) {
                System.out.println("\n--- Department Details ---");
                System.out.println("ID       : " + dept.getDeptId());
                System.out.println("Name     : " + dept.getDeptName());
                System.out.println("Location : " + dept.getLocation());
            } else {
                System.out.println("✗ Department not found!");
            }
        } finally {
            session.close();
        }
    }
    
    private static void updateDepartment(Scanner sc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        try {
            System.out.print("\nEnter Department ID to update: ");
            int id = sc.nextInt();
            
            Department dept = session.get(Department.class, id);
            if (dept != null) {
                sc.nextLine();
                System.out.print("Enter new Department Name: ");
                dept.setDeptName(sc.nextLine());
                System.out.print("Enter new Location: ");
                dept.setLocation(sc.nextLine());
                
                tx.commit();
                System.out.println("✓ Department updated successfully!");
            } else {
                System.out.println("✗ Department not found!");
                tx.rollback();
            }
        } catch (Exception e) {
            tx.rollback();
            System.out.println("✗ Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }
    
    private static void deleteDepartment(Scanner sc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        try {
            System.out.print("\nEnter Department ID to delete: ");
            int id = sc.nextInt();
            
            Department dept = session.get(Department.class, id);
            if (dept != null) {
                session.delete(dept);
                tx.commit();
                System.out.println("✓ Department deleted successfully!");
            } else {
                System.out.println("✗ Department not found!");
                tx.rollback();
            }
        } catch (Exception e) {
            tx.rollback();
            System.out.println("✗ Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }
    
    // ==================== EMPLOYEE OPERATIONS ====================
    
    private static void addEmployee(Scanner sc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        try {
            Employee emp = new Employee();
            System.out.print("\nEnter Employee ID: ");
            emp.setEmpId(sc.nextInt());
            sc.nextLine();
            System.out.print("Enter Employee Name: ");
            emp.setEmpName(sc.nextLine());
            System.out.print("Enter Salary: ");
            emp.setSalary(sc.nextDouble());
            System.out.print("Enter Department ID: ");
            int deptId = sc.nextInt();
            
            // Fetch and validate the Department
            Department dept = session.get(Department.class, deptId);
            if (dept == null) {
                System.out.println("✗ Error: Department ID " + deptId + " does not exist!");
                tx.rollback();
                return;
            }
            
            // Set the Department object (not just the ID)
            emp.setDepartment(dept);
            
            session.save(emp);
            tx.commit();
            System.out.println("✓ Employee added successfully!");
        } catch (Exception e) {
            tx.rollback();
            System.out.println("✗ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    private static void viewEmployee(Scanner sc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            System.out.print("\nEnter Employee ID: ");
            int id = sc.nextInt();
            
            Employee emp = session.get(Employee.class, id);
            if (emp != null) {
                System.out.println("\n--- Employee Details ---");
                System.out.println("ID            : " + emp.getEmpId());
                System.out.println("Name          : " + emp.getEmpName());
                System.out.println("Salary        : " + emp.getSalary());
                
                // Display Department details
                if (emp.getDepartment() != null) {
                    System.out.println("Department ID : " + emp.getDepartment().getDeptId());
                    System.out.println("Department    : " + emp.getDepartment().getDeptName());
                } else {
                    System.out.println("Department    : Not assigned");
                }
            } else {
                System.out.println("✗ Employee not found!");
            }
        } finally {
            session.close();
        }
    }
    
    private static void updateEmployee(Scanner sc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        try {
            System.out.print("\nEnter Employee ID to update: ");
            int id = sc.nextInt();
            
            Employee emp = session.get(Employee.class, id);
            if (emp != null) {
                sc.nextLine();
                System.out.print("Enter new Employee Name: ");
                emp.setEmpName(sc.nextLine());
                System.out.print("Enter new Salary: ");
                emp.setSalary(sc.nextDouble());
                System.out.print("Enter new Department ID: ");
                int deptId = sc.nextInt();
                
                // Fetch and validate the new Department
                Department dept = session.get(Department.class, deptId);
                if (dept == null) {
                    System.out.println("✗ Error: Department ID " + deptId + " does not exist!");
                    tx.rollback();
                    return;
                }
                
                emp.setDepartment(dept);
                
                tx.commit();
                System.out.println("✓ Employee updated successfully!");
            } else {
                System.out.println("✗ Employee not found!");
                tx.rollback();
            }
        } catch (Exception e) {
            tx.rollback();
            System.out.println("✗ Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }
    
    private static void deleteEmployee(Scanner sc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        try {
            System.out.print("\nEnter Employee ID to delete: ");
            int id = sc.nextInt();
            
            Employee emp = session.get(Employee.class, id);
            if (emp != null) {
                session.delete(emp);
                tx.commit();
                System.out.println("✓ Employee deleted successfully!");
            } else {
                System.out.println("✗ Employee not found!");
                tx.rollback();
            }
        } catch (Exception e) {
            tx.rollback();
            System.out.println("✗ Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }
}