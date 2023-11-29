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
import org.springframework.web.bind.annotation.RestController;

import com.erpm.employeeManagementService.dtos.DepartmentDto;
import com.erpm.employeeManagementService.entitys.Departments;
import com.erpm.employeeManagementService.exceptions.DepartmentNotFoundException;
import com.erpm.employeeManagementService.services.DepartmentService;

@RestController
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;

	@GetMapping("/{departmentId}")
	public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable int departmentId)
			throws DepartmentNotFoundException {
		DepartmentDto department = new DepartmentDto();
		Departments dt = departmentService.getDepartmentById(departmentId);
		department.setDepartmentId(dt.getDepartment_id());
		department.setDepartmentName(dt.getName());
		return ResponseEntity.ok(department);
	}

	@GetMapping
	public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
		List<DepartmentDto> departments = departmentService.getAllDepartments().stream()
				.map(x -> new DepartmentDto(x.getDepartment_id(), x.getName())).toList();
		return ResponseEntity.ok(departments);
	}

	@PostMapping
	public ResponseEntity<DepartmentDto> addDepartment(@RequestBody DepartmentDto department) {
		Departments depa = departmentService.addDepartment(department);
		DepartmentDto deptdto = new DepartmentDto(depa.getDepartment_id(), depa.getName());
		return ResponseEntity.ok(deptdto);
	}

	@DeleteMapping("/{departmentId}")
	public ResponseEntity<String> deleteDepartmentById(@PathVariable int departmentId) {
		departmentService.deleteDepartmentById(departmentId);
		return ResponseEntity.ok("");
	}

	@PatchMapping
	public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto department)
			throws DepartmentNotFoundException {
		Departments dept = departmentService.updateDepartment(department);
		DepartmentDto newdept = new DepartmentDto(dept.getDepartment_id(), dept.getName());
		return ResponseEntity.ok(newdept);
	}
}
