package com.lms.mini_leave_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.mini_leave_management.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
