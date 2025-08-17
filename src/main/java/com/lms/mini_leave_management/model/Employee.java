package com.lms.mini_leave_management.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String department;
    private LocalDate joiningDate;
    private int leaveBalance = 20; 

    
    public Employee() {}

    public Employee(String name, String email, String department, LocalDate joiningDate) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.joiningDate = joiningDate;
    }

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public LocalDate getJoiningDate() { return joiningDate; }
    public void setJoiningDate(LocalDate joiningDate) { this.joiningDate = joiningDate; }

    public int getLeaveBalance() { return leaveBalance; }
    public void setLeaveBalance(int leaveBalance) { this.leaveBalance = leaveBalance; }
}
