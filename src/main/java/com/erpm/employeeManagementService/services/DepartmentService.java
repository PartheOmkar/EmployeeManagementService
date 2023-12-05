package com.erpm.employeeManagementService.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpm.employeeManagementService.dtos.DepartmentDto;
import com.erpm.employeeManagementService.entitys.Departments;
import com.erpm.employeeManagementService.exceptions.DepartmentNotFoundException;
import com.erpm.employeeManagementService.repositorys.DepartmentsRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentsRepository departmentsRepository;
	
	public Departments getDepartmentById(int departmentId) throws DepartmentNotFoundException {
		Departments department;
		try {
			department = departmentsRepository.findById(departmentId).get();
		}catch(NoSuchElementException ex) {
			throw new DepartmentNotFoundException("department id = "+departmentId+" not found");
		}
		return department;
	}

	public List<Departments> getAllDepartments() {
		List<Departments> departments = departmentsRepository.findAll();
		return departments;
	}

	public Departments addDepartment(DepartmentDto newdepartment) {
		Departments department = new Departments();
		department.setName(newdepartment.getDepartmentName());;
		department = departmentsRepository.save(department);
		return department;
	}

	public void deleteDepartmentById(int departmentId) {
		departmentsRepository.deleteById(departmentId);
	}

	public Departments updateDepartment(DepartmentDto department) throws DepartmentNotFoundException {
		Departments oldDepartment = getDepartmentById(department.getDepartmentId());
		oldDepartment.setName(department.getDepartmentName());
		oldDepartment = departmentsRepository.save(oldDepartment);
		return oldDepartment;
	}

}
