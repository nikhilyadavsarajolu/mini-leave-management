package com.lms.mini_leave_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.mini_leave_management.model.LeaveRequest;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployeeId(Long employeeId);
    List<LeaveRequest> findByEmployeeIdAndStatus(Long employeeId, String status);
}
