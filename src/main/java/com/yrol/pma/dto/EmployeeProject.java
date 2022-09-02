package com.yrol.pma.dto;

public interface EmployeeProject {

	/**
	 * IMPORTANT: Need to have the property names begin with "get"
	 * This DTO is being used by EmployeeRepository - DAO and in some controllers
	 * */
	public String getFirstName();
	public String getLastName();
	public Integer getProjectCount();
}

