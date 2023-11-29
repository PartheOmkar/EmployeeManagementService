package com.erpm.employeeManagementService.dtos;

public class SeniorityDto {

	private int seniorityid;
	private String seniority;

	public SeniorityDto() {
		super();
	}

	public SeniorityDto(int seniorityid, String seniority) {
		super();
		this.seniorityid = seniorityid;
		this.seniority = seniority;
	}

	public int getSeniorityid() {
		return seniorityid;
	}

	public void setSeniorityid(int seniorityid) {
		this.seniorityid = seniorityid;
	}

	public String getSeniority() {
		return seniority;
	}

	public void setSeniority(String seniority) {
		this.seniority = seniority;
	}

}
