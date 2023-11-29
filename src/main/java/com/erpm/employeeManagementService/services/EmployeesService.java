package com.erpm.employeeManagementService.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpm.employeeManagementService.dtos.EmployeeDto;
import com.erpm.employeeManagementService.entitys.Departments;
import com.erpm.employeeManagementService.entitys.Employees;
import com.erpm.employeeManagementService.exceptions.DepartmentNotFoundException;
import com.erpm.employeeManagementService.exceptions.EmployeeNotFoundException;
import com.erpm.employeeManagementService.repositorys.EmployeesRepositoy;

@Service
public class EmployeesService {

	@Autowired
	private EmployeesRepositoy employeesRepositoy;
	@Autowired
	private DepartmentService departmentService;

	public List<Employees> getAllEmployees() {
		List<Employees> emps = employeesRepositoy.findAll();
		return emps;
	}

	public Employees addEmployee(EmployeeDto newEmployees) throws DepartmentNotFoundException {
		Employees employees = new Employees();
		employees.setFirst_name(newEmployees.getFirst_name());
		employees.setLast_name(newEmployees.getLast_name());
		employees.setEmail(newEmployees.getEmail());
		employees.setPhone(newEmployees.getPhone());
		employees.setDepartment(departmentService.getDepartmentById(newEmployees.getDepartment_id()));
		employees = employeesRepositoy.save(employees);
		return employees;
	}

	public void deleteEmployee(int id) {
		try {
			employeesRepositoy.deleteById(id);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("number should be not null");
		}
	}

	public Employees updateEmployee(int employeeId, EmployeeDto newEmployee)
			throws DepartmentNotFoundException, EmployeeNotFoundException {
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
			employee = employeesRepositoy.save(exEmployee);
		} catch (DepartmentNotFoundException ex) {
			throw ex;
		} catch (NoSuchElementException ex) {
			throw new EmployeeNotFoundException("employee id +" + employeeId + " not found");
		}
		return employee;
	}

	public Employees getEmployeeById(int employeeId) throws EmployeeNotFoundException {
		Employees employee;
		try {
			employee = employeesRepositoy.findById(employeeId).get();
		} catch (NoSuchElementException ex) {
			throw new EmployeeNotFoundException("employee id = " + employeeId + " not found");
		}
		return employee;
	}

}
