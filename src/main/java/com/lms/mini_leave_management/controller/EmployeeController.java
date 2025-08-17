package com.lms.mini_leave_management.controller;

import com.lms.mini_leave_management.model.Employee;
import com.lms.mini_leave_management.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Add new employee
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    // Get leave balance
    @GetMapping("/{id}/balance")
    public Map<String, Object> getLeaveBalance(@PathVariable Long id) {
        return employeeService.getLeaveBalance(id);
    }
}
