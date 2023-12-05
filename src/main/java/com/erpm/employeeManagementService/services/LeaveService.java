package com.erpm.employeeManagementService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpm.employeeManagementService.dtos.LeaveRequestDto;
import com.erpm.employeeManagementService.externalServices.ExternalLeaveService;

@Service
public class LeaveService {

	@Autowired
	private ExternalLeaveService externalLeaveService;

	public List<LeaveRequestDto> getLeaveByEmployeeId(int employeeId) {
		List<LeaveRequestDto> leaves = externalLeaveService.getLeaveRequestByEmployeeId(employeeId).getBody();
		return leaves;
	}

	public List<LeaveRequestDto> getLeaveByApprovarId(int approvarId) {
		List<LeaveRequestDto> leaves = externalLeaveService.getLeaveRequestByApprovalId(approvarId).getBody();
		return leaves;
	}
}
