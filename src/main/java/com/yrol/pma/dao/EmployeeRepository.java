package com.yrol.pma.dao;

import org.springframework.data.repository.CrudRepository;

import com.yrol.pma.entities.Employee;


/**
 * EmployeeRepository Interface which allows to perform CRUP operations for Employee entity using CrudRepository
 * CrudRepository accepts the entity and the unique ID's type which is Long (employeeId)
 * */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
