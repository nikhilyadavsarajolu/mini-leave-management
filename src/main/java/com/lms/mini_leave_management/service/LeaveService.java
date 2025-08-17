package com.lms.mini_leave_management.service;

import com.lms.mini_leave_management.model.Employee;
import com.lms.mini_leave_management.model.LeaveRequest;
import com.lms.mini_leave_management.repository.EmployeeRepository;
import com.lms.mini_leave_management.repository.LeaveRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    // Apply leave
    public LeaveRequest applyLeave(LeaveRequest leaveRequest) {
        Optional<Employee> empOpt = employeeRepository.findById(leaveRequest.getEmployeeId());
        if (!empOpt.isPresent()) {
            throw new RuntimeException("Employee not found");
        }

        Employee emp = empOpt.get();
        LocalDate start = leaveRequest.getStartDate();
        LocalDate end = leaveRequest.getEndDate();

        // Check dates
        if (start.isBefore(emp.getJoiningDate())) {
            throw new RuntimeException("Cannot apply leave before joining date");
        }
        if (end.isBefore(start)) {
            throw new RuntimeException("End date cannot be before start date");
        }

        // Check leave balance
        int days = (int) ChronoUnit.DAYS.between(start, end) + 1;
        if (days > emp.getLeaveBalance()) {
            throw new RuntimeException("Not enough leave balance");
        }

        // Check overlapping approved leaves
        List<LeaveRequest> approvedLeaves = leaveRequestRepository
                .findByEmployeeIdAndStatus(emp.getId(), "Approved");

        for (LeaveRequest lr : approvedLeaves) {
            if (!(end.isBefore(lr.getStartDate()) || start.isAfter(lr.getEndDate()))) {
                throw new RuntimeException("Overlapping leave request");
            }
        }

        leaveRequest.setStatus("Pending");
        leaveRequest.setAppliedOn(LocalDate.now());

        return leaveRequestRepository.save(leaveRequest);
    }

    // Approve leave
    public LeaveRequest approveLeave(Long leaveId) {
        LeaveRequest lr = leaveRequestRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        Employee emp = employeeRepository.findById(lr.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        int days = (int) ChronoUnit.DAYS.between(lr.getStartDate(), lr.getEndDate()) + 1;
        if (days > emp.getLeaveBalance()) {
            throw new RuntimeException("Not enough leave balance");
        }

        emp.setLeaveBalance(emp.getLeaveBalance() - days);
        employeeRepository.save(emp);

        lr.setStatus("Approved");
        return leaveRequestRepository.save(lr);
    }

    // Reject leave
    public LeaveRequest rejectLeave(Long leaveId) {
        LeaveRequest lr = leaveRequestRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));
        lr.setStatus("Rejected");
        return leaveRequestRepository.save(lr);
    }

    public List<LeaveRequest> getAllLeaves() {
    return leaveRequestRepository.findAll();
}

    public List<LeaveRequest> getLeavesByEmployee(Long employeeId) {
    return leaveRequestRepository.findByEmployeeId(employeeId);
}
}
