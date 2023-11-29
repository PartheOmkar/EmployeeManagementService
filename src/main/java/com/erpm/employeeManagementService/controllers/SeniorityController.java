package com.erpm.employeeManagementService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.erpm.employeeManagementService.dtos.SeniorityDto;
import com.erpm.employeeManagementService.entitys.Seniority;
import com.erpm.employeeManagementService.exceptions.SeniorityNotFoundException;
import com.erpm.employeeManagementService.services.SeniorityService;

@RestController
public class SeniorityController {

	@Autowired
	SeniorityService seniorityService;

	@GetMapping
	public ResponseEntity<List<SeniorityDto>> getAllSeniority() {
		List<SeniorityDto> seniorities = seniorityService.getAllSeniority().stream()
				.map(seniority -> new SeniorityDto(seniority.getSeniority_id(), seniority.getSeniority())).toList();
		return ResponseEntity.ok(seniorities);
	}

	@GetMapping("/{seniorityId}")
	public ResponseEntity<SeniorityDto> getSeniorityById(@PathVariable int seniorityId)
			throws SeniorityNotFoundException {

		Seniority s = seniorityService.getSeniorityById(seniorityId);
		SeniorityDto sinDto = new SeniorityDto(s.getSeniority_id(), s.getSeniority());
		return ResponseEntity.ok(sinDto);
	}

	@DeleteMapping("/{seniorityId}")
	public ResponseEntity<String> deleteSeniorityById(@PathVariable int seniorityId) {
		seniorityService.deleteSeniorityById(seniorityId);
		return ResponseEntity.ok("");
	}
	
	@PatchMapping
	public ResponseEntity<SeniorityDto> updateSeniorityById(@RequestBody SeniorityDto newSeniority) throws SeniorityNotFoundException{
		Seniority sen = seniorityService.updateSeniorityById(newSeniority);
		SeniorityDto senDto = new SeniorityDto(sen.getSeniority_id(),sen.getSeniority());
		return ResponseEntity.ok(senDto);
	}
}
