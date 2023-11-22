package com.erpm.employeeManagementService.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpm.employeeManagementService.dtos.EmployeeDto;
import com.erpm.employeeManagementService.exceptions.DepartmentNotFoundException;
import com.erpm.employeeManagementService.exceptions.EmployeeNotFoundException;
import com.erpm.employeeManagementService.models.Departments;
import com.erpm.employeeManagementService.models.Employees;
import com.erpm.employeeManagementService.repositorys.DepartmentsRepository;
import com.erpm.employeeManagementService.repositorys.EmployeesRepositoy;

@Service
public class EmployeesService {

	@Autowired
	private EmployeesRepositoy employeesRepositoy;
	@Autowired
	private DepartmentsRepository departmentsRepository;

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
		Optional<Departments> departments = departmentsRepository.findById(newEmployees.getDepartment_id());
		try {
			Departments depart = departments.get();
			employees.setDepartment(depart);
		} catch (NoSuchElementException ex) {
			throw new DepartmentNotFoundException("department id" + newEmployees.getDepartment_id() + " not found");
		}
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

	public Employees updateEmployee(int employeeId, EmployeeDto newEmployee) throws DepartmentNotFoundException, EmployeeNotFoundException {
		Optional<Employees> checkEmpExist = employeesRepositoy.findById(employeeId);
		Employees employee;
		try {
			Employees exEmployee = checkEmpExist.get();
			if (newEmployee.getFirst_name() != null || newEmployee.getFirst_name() != "") {
				exEmployee.setFirst_name(newEmployee.getFirst_name());
			}
			if (newEmployee.getLast_name() != null || newEmployee.getLast_name() != "") {
				exEmployee.setLast_name(newEmployee.getLast_name());
			}
			if (newEmployee.getPhone() != null || newEmployee.getPhone() != "") {
				exEmployee.setPhone(newEmployee.getPhone());
			}
			if (newEmployee.getEmail() != null || newEmployee.getEmail() != "") {
				exEmployee.setEmail(newEmployee.getEmail());
			}
			if (newEmployee.getDepartment_id() != 0) {
				Departments dept = checkDepartmentExist(newEmployee.getDepartment_id());
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

	private Departments checkDepartmentExist(int id) throws DepartmentNotFoundException {
		Optional<Departments> departOptional = departmentsRepository.findById(id);
		Departments dept = new Departments();
		try {
			dept = departOptional.get();
		} catch (NoSuchElementException ex) {
			throw new DepartmentNotFoundException("department id " + id + " not found");
		}
		return dept;
	}

}
