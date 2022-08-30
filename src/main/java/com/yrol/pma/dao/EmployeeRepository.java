package com.yrol.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.yrol.pma.dto.EmployeeProject;
import com.yrol.pma.entities.Employee;


/**
 * EmployeeRepository Interface which allows to perform CRUP operations for Employee entity using CrudRepository
 * CrudRepository accepts the entity and the unique ID's type which is Long (employeeId)
 * */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	/**
	 * Overriding the "findAll()" of CrudRepository to return a List instead of Iterable
	 * */
	@Override
	public List<Employee> findAll();
	
	@Query(nativeQuery = true, value = "SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_id) as projectCount"
			+ " FROM employee e LEFT JOIN project_employee pe ON pe.employee_id = e.employee_id"
			+ " GROUP BY e.first_name, e.last_name ORDER BY e.last_name DESC")
	public List<EmployeeProject> employeeProjects();
}
