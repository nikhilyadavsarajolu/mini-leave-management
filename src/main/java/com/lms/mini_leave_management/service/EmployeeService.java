package com.lms.mini_leave_management.service;

import com.lms.mini_leave_management.model.Employee;
import com.lms.mini_leave_management.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Add new employee
    public Employee addEmployee(Employee employee) {
        // Default leave balance = 20
        employee.setLeaveBalance(20);
        return employeeRepository.save(employee);
    }

    // Fetch leave balance
    public Map<String, Object> getLeaveBalance(Long employeeId) {
        Optional<Employee> empOpt = employeeRepository.findById(employeeId);
        if (!empOpt.isPresent()) {
            throw new RuntimeException("Employee not found");
        }
        Employee emp = empOpt.get();
        Map<String, Object> response = new HashMap<>();
        response.put("employeeName", emp.getName());
        response.put("leaveBalance", emp.getLeaveBalance());
        return response;
    }
}
