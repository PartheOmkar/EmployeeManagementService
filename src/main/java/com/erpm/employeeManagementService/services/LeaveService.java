package com.erpm.employeeManagementService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.erpm.employeeManagementService.dtos.LeaveRequestDto;

@Service
public class LeaveService {

	@Autowired
	private RestTemplate restTemplate;
	private static final String LEAVE_SERVICES = "http://LEAVEMANAGEMENT/leave";

	public List<LeaveRequestDto> getLeaveByEmployeeId(int employeeId) {
		ResponseEntity<List<LeaveRequestDto>> leaves = this.restTemplate.exchange(
				LEAVE_SERVICES + "/employee/" + employeeId, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
				});
		return leaves.getBody();
	}

	public List<LeaveRequestDto> getLeaveByApprovarId(int approvarId) {
		ResponseEntity<List<LeaveRequestDto>> leaves = this.restTemplate.exchange(LEAVE_SERVICES+"/approver/"+approvarId, HttpMethod.GET, null,
				new ParameterizedTypeReference<>() {
				});
		return leaves.getBody();
	}
}
