package com.yrol.pma.services;

import com.yrol.pma.dao.EmployeeRepository;
import com.yrol.pma.dto.EmployeeProject;
import com.yrol.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * An intermediary service that'll abstract all the employee actions.
 * Consumed by the Controllers.
 * */

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository empRepo;

    public Employee save(Employee employee) {
        return empRepo.save(employee);
    }

    public List<Employee> getAll() {
        return empRepo.findAll();
    }

    public List<EmployeeProject> employeeProjects() {
        return empRepo.employeeProjects();
    }
}
