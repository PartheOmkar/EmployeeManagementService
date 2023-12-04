package com.erpm.employeeManagementService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpm.employeeManagementService.dtos.SeniorityDto;
import com.erpm.employeeManagementService.entitys.Seniority;
import com.erpm.employeeManagementService.exceptions.SeniorityNotFoundException;
import com.erpm.employeeManagementService.services.SeniorityService;

@RestController
@RequestMapping("seniority")
public class SeniorityController {

	@Autowired
	SeniorityService seniorityService;

	@GetMapping
	public ResponseEntity<List<Seniority>> getAllSeniority() {
		List<Seniority> seniorities = seniorityService.getAllSeniority();
		return ResponseEntity.ok(seniorities);
	}

	@GetMapping("/{seniorityId}")
	public ResponseEntity<Seniority> getSeniorityById(@PathVariable int seniorityId) throws SeniorityNotFoundException {

		Seniority seniority = seniorityService.getSeniorityById(seniorityId);
		return ResponseEntity.ok(seniority);
	}

	@PostMapping
	public ResponseEntity<Seniority> addSeniority(@RequestBody SeniorityDto sen) {
		Seniority seniority = seniorityService.addSeniority(sen);
		return ResponseEntity.ok(seniority);
	}

	@DeleteMapping("/{seniorityId}")
	public ResponseEntity<String> deleteSeniorityById(@PathVariable int seniorityId) {
		seniorityService.deleteSeniorityById(seniorityId);
		return ResponseEntity.ok("");
	}

	@PatchMapping
	public ResponseEntity<Seniority> updateSeniorityById(@RequestBody SeniorityDto newSeniority)
			throws SeniorityNotFoundException {
		Seniority sen = seniorityService.updateSeniorityById(newSeniority);
		return ResponseEntity.ok(sen);
	}
}
