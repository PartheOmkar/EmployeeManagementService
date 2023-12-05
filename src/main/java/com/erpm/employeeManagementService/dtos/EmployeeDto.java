package com.erpm.employeeManagementService.dtos;

import com.erpm.employeeManagementService.entitys.Employees;

public class EmployeeDto {
	private int employee_id;
	private String first_name;
	private String last_name;
	private String email;
	private String phone;
	private int seniority_id;
	private int department_id;

	public EmployeeDto() {
		super();
	}

	public EmployeeDto(Employees employees) {
		this.employee_id = employees.getEmployee_id();
		this.first_name = employees.getFirst_name();
		this.last_name = employees.getLast_name();
		this.email = employees.getEmail();
		this.phone = employees.getPhone();
		this.seniority_id = employees.getSeniority().getSeniority_id();
		this.department_id = employees.getDepartment().getDepartment_id();
	}

	public EmployeeDto(String first_name, String last_name, String email, String phone) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.phone = phone;
	}

	public EmployeeDto(String first_name, String last_name, String email, String phone, int department_id) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.phone = phone;
		this.department_id = department_id;
	}

	public EmployeeDto(String first_name, String last_name, String email, String phone, int seniority_id,
			int department_id) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.phone = phone;
		this.seniority_id = seniority_id;
		this.department_id = department_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public int getSeniority_id() {
		return seniority_id;
	}

	public void setSeniority_id(int seniority_id) {
		this.seniority_id = seniority_id;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

}
