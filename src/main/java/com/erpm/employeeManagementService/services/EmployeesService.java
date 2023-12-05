package com.erpm.employeeManagementService.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpm.employeeManagementService.dtos.EmployeeDto;
import com.erpm.employeeManagementService.entitys.Departments;
import com.erpm.employeeManagementService.entitys.Employees;
import com.erpm.employeeManagementService.entitys.Seniority;
import com.erpm.employeeManagementService.exceptions.DepartmentNotFoundException;
import com.erpm.employeeManagementService.exceptions.EmployeeNotFoundException;
import com.erpm.employeeManagementService.exceptions.SeniorityNotFoundException;
import com.erpm.employeeManagementService.repositorys.EmployeesRepositoy;

@Service
public class EmployeesService {

	@Autowired
	private EmployeesRepositoy employeesRepositoy;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private SeniorityService seniorityService;
	
	public List<EmployeeDto> getAllEmployees() {
		List<EmployeeDto> emps = employeesRepositoy.findAll().stream().map(employee->
			new EmployeeDto(employee)
		).toList();
		return emps;
	}

	public EmployeeDto addEmployee(EmployeeDto newEmployees) throws DepartmentNotFoundException, SeniorityNotFoundException {
		
		Employees employees = new Employees();
		employees.setFirst_name(newEmployees.getFirst_name());
		employees.setLast_name(newEmployees.getLast_name());
		employees.setEmail(newEmployees.getEmail());
		employees.setPhone(newEmployees.getPhone());
		employees.setDepartment(departmentService.getDepartmentById(newEmployees.getDepartment_id()));
		employees.setSeniority(seniorityService.getSeniorityById(newEmployees.getSeniority_id()));
		employees = employeesRepositoy.save(employees);
		
		return new EmployeeDto(employees);
	}

	public void deleteEmployee(int id) {
		try {
			employeesRepositoy.deleteById(id);
			//employeesRepositoy.sa
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("number should be not null");
		}
	}

	public EmployeeDto updateEmployee(int employeeId, EmployeeDto newEmployee)
			throws DepartmentNotFoundException, EmployeeNotFoundException, SeniorityNotFoundException {
		Optional<Employees> checkEmpExist = employeesRepositoy.findById(employeeId);
		Employees employee;
		try {
			Employees exEmployee = checkEmpExist.get();
			if (newEmployee.getFirst_name() != null || !newEmployee.getFirst_name().isBlank()) {
				exEmployee.setFirst_name(newEmployee.getFirst_name());
			}
			if (newEmployee.getLast_name() != null || !newEmployee.getLast_name().isBlank()) {
				exEmployee.setLast_name(newEmployee.getLast_name());
			}
			if (newEmployee.getPhone() != null || !newEmployee.getPhone().isBlank()) {
				exEmployee.setPhone(newEmployee.getPhone());
			}
			if (newEmployee.getEmail() != null || !newEmployee.getEmail().isBlank()) {
				exEmployee.setEmail(newEmployee.getEmail());
			}
			if (newEmployee.getDepartment_id() != 0) {
				Departments dept = departmentService.getDepartmentById(newEmployee.getDepartment_id());
				if (dept != null) {
					exEmployee.setDepartment(dept);
				}
			}
			if (newEmployee.getSeniority_id() != 0) {
				Seniority sen = seniorityService.getSeniorityById(newEmployee.getSeniority_id());
				if (sen != null) {
					exEmployee.setSeniority(sen);
				}
			}
			employee = employeesRepositoy.save(exEmployee);
		} catch (DepartmentNotFoundException ex) {
			throw ex;
		} catch (NoSuchElementException ex) {
			throw new EmployeeNotFoundException("employee id +" + employeeId + " not found");
		}

		return new EmployeeDto(employee);
	}

	public EmployeeDto  getEmployeeById(int employeeId) throws EmployeeNotFoundException {
		Employees employee;
		try {
			employee = employeesRepositoy.findById(employeeId).get();
		} catch (NoSuchElementException ex) {
			throw new EmployeeNotFoundException("employee id = " + employeeId + " not found");
		}
		return new EmployeeDto(employee);
	}
}
