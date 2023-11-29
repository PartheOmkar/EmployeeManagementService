package com.erpm.employeeManagementService.entitys;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Seniority {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int seniority_id;
	private String seniority;
	@OneToMany(mappedBy = "seniority")
	private Set<Employees> employees;

	public int getSeniority_id() {
		return seniority_id;
	}

	public Set<Employees> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employees> employees) {
		this.employees = employees;
	}

	public void setSeniority_id(int seniority_id) {
		this.seniority_id = seniority_id;
	}

	public String getSeniority() {
		return seniority;
	}

	public void setSeniority(String seniority) {
		this.seniority = seniority;
	}

}
