package com.erpm.employeeManagementService.externalServices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.erpm.employeeManagementService.dtos.LeaveRequestDto;

@FeignClient("LEAVEMANAGEMENT")
public interface ExternalLeaveService {

	@GetMapping("/leave/employee/{employeeId}")
	public ResponseEntity<List<LeaveRequestDto>> getLeaveRequestByEmployeeId(@PathVariable int employeeId);
	
	@GetMapping("/leave/approver/{approvarId}")
	public ResponseEntity<List<LeaveRequestDto>> getLeaveRequestByApprovalId(@PathVariable int approvarId);
}
