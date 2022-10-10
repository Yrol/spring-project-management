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

    public Employee update(Employee patchEmp) {
        Employee currentEmp = empRepo.findById(patchEmp.getEmployeeId()).get();
        currentEmp.setFirstName(patchEmp.getFirstName());
        currentEmp.setLastName(patchEmp.getLastName());
        currentEmp.setEmail(patchEmp.getEmail());
        return empRepo.save(currentEmp);
    }

    public List<Employee> getAll() {
        return empRepo.findAll();
    }

    public List<EmployeeProject> employeeProjects() {
        return empRepo.employeeProjects();
    }

    public Employee findById(Long id) {
        return empRepo.findById(id).get();
    }

    public boolean existsById(Long id) {
        return empRepo.existsById(id);
    }

    public boolean isUniqueEmailOnUpdate(Employee patchEmployee) {

        Employee currentEmp = empRepo.findById(patchEmployee.getEmployeeId()).get();

        String patchEmail = patchEmployee.getEmail();
        String currentEmail = currentEmp.getEmail();

        if (!patchEmail.equals(currentEmail) && empRepo.findByEmail(patchEmail).size() > 0) {
            return false;
        }
        return true;
    }

    public boolean isUniqueEmailOnCreate(Employee employee) {
        List<Employee> employees = empRepo.findByEmail(employee.getEmail());
        return employees.size() > 0;
    }
}
