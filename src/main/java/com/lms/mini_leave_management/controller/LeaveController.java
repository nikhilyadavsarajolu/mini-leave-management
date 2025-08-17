package com.lms.mini_leave_management.controller;

import com.lms.mini_leave_management.model.LeaveRequest;
import com.lms.mini_leave_management.service.LeaveService;

import jakarta.validation.Valid;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    // Apply for leave
    @PostMapping("/apply")
    public LeaveRequest applyLeave(@Valid @RequestBody LeaveRequest leaveRequest) {
        return leaveService.applyLeave(leaveRequest);
    }

    // Approve leave
    @PostMapping("/approve")
    public LeaveRequest approveLeave(@RequestParam Long leaveId) {
        return leaveService.approveLeave(leaveId);
    }

    // Reject leave
    @PostMapping("/reject")
    public LeaveRequest rejectLeave(@RequestParam Long leaveId) {
        return leaveService.rejectLeave(leaveId);
    }

    // Get all leaves
    @GetMapping("/all")
    public List<LeaveRequest> getAllLeaves(@RequestParam(required = false) Long employeeId) {
    if (employeeId != null) {
        return leaveService.getLeavesByEmployee(employeeId);
    }
         return leaveService.getAllLeaves();
}


}
