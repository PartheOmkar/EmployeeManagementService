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

import com.erpm.employeeManagementService.dtos.DepartmentDto;
import com.erpm.employeeManagementService.entitys.Departments;
import com.erpm.employeeManagementService.exceptions.DepartmentNotFoundException;
import com.erpm.employeeManagementService.services.DepartmentService;

@RestController
@RequestMapping("api/department")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;

	@GetMapping("/{departmentId}")
	public ResponseEntity<Departments> getDepartmentById(@PathVariable int departmentId)
			throws DepartmentNotFoundException {
		Departments department = departmentService.getDepartmentById(departmentId);
		return ResponseEntity.ok(department);
	}

	@GetMapping
	public ResponseEntity<List<Departments>> getAllDepartments() {
		List<Departments> departments = departmentService.getAllDepartments();
		return ResponseEntity.ok(departments);
	}

	@PostMapping
	public ResponseEntity<Departments> addDepartment(@RequestBody DepartmentDto department) {
		Departments dept = departmentService.addDepartment(department);
		return ResponseEntity.ok(dept);
	}

	@DeleteMapping("/{departmentId}")
	public ResponseEntity<String> deleteDepartmentById(@PathVariable int departmentId) {
		departmentService.deleteDepartmentById(departmentId);
		return ResponseEntity.ok("");
	}

	@PatchMapping
	public ResponseEntity<Departments> updateDepartment(@RequestBody DepartmentDto department)
			throws DepartmentNotFoundException {
		Departments dept = departmentService.updateDepartment(department);
		return ResponseEntity.ok(dept);
	}
}
