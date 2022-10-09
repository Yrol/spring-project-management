package com.yrol.pma.api.controllers;

import com.yrol.pma.dao.EmployeeRepository;
import com.yrol.pma.entities.Employee;
import com.yrol.pma.exceptions.generic.InvalidEmailException;
import com.yrol.pma.exceptions.generic.NoRecordFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {

    @Autowired
    EmployeeRepository empRepo;

    /**
     * Fetch all employees
     * */
    @GetMapping
    public List<Employee> getEmployees() {
        return empRepo.findAll();
    }

    /**
     * Find an employee by ID
     * findById is being inherited from CrudRepository
     * */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(empRepo.findById(id).get(), HttpStatus.OK);
    }

    /**
     * Creating an employee
     * */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {

        if (empRepo.findByEmail(employee.getEmail()).size() > 0) {
            throw new InvalidEmailException();
        }

        return new ResponseEntity<>(empRepo.save(employee), HttpStatus.CREATED);
    }

    /**
     * Partial update employee records
     * */
    @PatchMapping(path="/{id}", consumes = "application/json")
    public ResponseEntity<Employee> partialUpdate(@PathVariable("id") long id, @RequestBody Employee patchEmployee) {
        Optional<Employee> currentEmployeeInit = empRepo.findById(id);

        if (!currentEmployeeInit.isPresent()) {
            throw new NoRecordFoundException();
        }

        Employee currentEmployee = currentEmployeeInit.get();

        String patchEmail = patchEmployee.getEmail();
        String currentEmail = currentEmployee.getEmail();

        // When the current email has changed and making sure its unique
        if (!patchEmail.equals(currentEmail) && empRepo.findByEmail(patchEmail).size() > 0) {
            throw new InvalidEmailException();
        }

        currentEmployee.setFirstName(patchEmployee.getFirstName());
        currentEmployee.setLastName(patchEmployee.getLastName());
        currentEmployee.setEmail(patchEmployee.getEmail());

        return new ResponseEntity<>(empRepo.save(currentEmployee), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") long id) throws Exception {
        try {
            empRepo.deleteById(id);
        } catch(EmptyResultDataAccessException e) {
            // can be written to the app log
        }
    }

    @GetMapping(params = {"page", "size"})
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Employee> findPaginatedEmployees(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageAndSize = PageRequest.of(page, size);
        return empRepo.findAll(pageAndSize);
    }
}
