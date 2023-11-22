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
import com.erpm.employeeManagementService.exceptions.DepartmentNotFoundException;
import com.erpm.employeeManagementService.exceptions.EmployeeNotFoundException;
import com.erpm.employeeManagementService.models.Employees;
import com.erpm.employeeManagementService.services.EmployeesService;

@RestController
@RequestMapping("api/employees")
public class EmployeesController {

	@Autowired
	private EmployeesService employeesService;

	@GetMapping
	public ResponseEntity<List<Employees>> getEmployees() {
		List<Employees> emps = employeesService.getAllEmployees();
		if (emps.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(emps);
	}

	@PostMapping
	public ResponseEntity<Employees> addEmployee(@RequestBody EmployeeDto newEmployees) throws DepartmentNotFoundException{
		Employees employee = employeesService.addEmployee(newEmployees);
		if(employee==null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(employee);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteEmployee(@RequestParam int id){
		employeesService.deleteEmployee(id);
		return ResponseEntity.ok("");
	}
	
	@PatchMapping("/{employeeID}")
	public ResponseEntity<Employees> updateEmployee(@PathVariable Integer employeeID,@RequestBody EmployeeDto newEmployee) throws DepartmentNotFoundException, EmployeeNotFoundException{
		Employees employee = employeesService.updateEmployee(employeeID,newEmployee);
		if(employee==null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(employee);
	}
}
