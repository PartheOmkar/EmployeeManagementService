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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erpm.employeeManagementService.dtos.EmployeeDto;
import com.erpm.employeeManagementService.dtos.LeaveRequestDto;
import com.erpm.employeeManagementService.exceptions.DepartmentNotFoundException;
import com.erpm.employeeManagementService.exceptions.EmployeeNotFoundException;
import com.erpm.employeeManagementService.exceptions.SeniorityNotFoundException;
import com.erpm.employeeManagementService.services.EmployeesService;
import com.erpm.employeeManagementService.services.LeaveService;

@RestController
@RequestMapping("employees")
public class EmployeesController {

	@Autowired
	private EmployeesService employeesService;

	@Autowired
	private LeaveService leaveService;

	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getEmployees() {
		List<EmployeeDto> emps = employeesService.getAllEmployees();
		if (emps.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(emps);
	}

	@GetMapping("/{employeeId}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable int employeeId) throws EmployeeNotFoundException {
		EmployeeDto employee = employeesService.getEmployeeById(employeeId);
		return ResponseEntity.ok(employee);
	}

	@PostMapping
	public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto newEmployees)
			throws DepartmentNotFoundException, SeniorityNotFoundException {
		EmployeeDto employee = employeesService.addEmployee(newEmployees);
		if (employee == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(employee);
	}

	@DeleteMapping("/{employeeID}")
	public ResponseEntity<String> deleteEmployee(@RequestParam int employeeID) {
		employeesService.deleteEmployee(employeeID);
		return ResponseEntity.ok("");
	}

	@PatchMapping("/{employeeID}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Integer employeeID,
			@RequestBody EmployeeDto newEmployee)
			throws DepartmentNotFoundException, EmployeeNotFoundException, SeniorityNotFoundException {
		EmployeeDto employee = employeesService.updateEmployee(employeeID, newEmployee);
		if (employee == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(employee);
	}

	@GetMapping("/leaves/{employeeId}")
	public ResponseEntity<List<LeaveRequestDto>> getLeaveByEmployeeId(@PathVariable int employeeId) {
		List<LeaveRequestDto> leaves = leaveService.getLeaveByEmployeeId(employeeId);
		return ResponseEntity.ok(leaves);
	}

	@GetMapping("/approvar/{approvarId}")
	public ResponseEntity<List<LeaveRequestDto>> getLeaveByApprovarId(@PathVariable int approvarId) {
		List<LeaveRequestDto> leaves = leaveService.getLeaveByApprovarId(approvarId);
		return ResponseEntity.ok(leaves);
	}
}
