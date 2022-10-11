package com.yrol.pma.api.controllers;

import com.yrol.pma.entities.Employee;
import com.yrol.pma.exceptions.generic.InvalidEmailException;
import com.yrol.pma.exceptions.generic.NoRecordFoundException;
import com.yrol.pma.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {

    @Autowired
    EmployeeService empService;

    /**
     * Fetch all employees
     * */
    @GetMapping
    public List<Employee> getEmployees() {
        return empService.getAll();
    }

    /**
     * Find an employee by ID
     * findById is being inherited from CrudRepository
     * */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) throws NoRecordFoundException {

        if (!empService.existsById(id)) {
            throw new NoRecordFoundException();
        }

        return new ResponseEntity<>(empService.findById(id), HttpStatus.OK);
    }

    /**
     * Creating an employee
     * */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Employee> create(@RequestBody @Valid Employee employee) throws InvalidEmailException {

        if (!empService.isUniqueEmailOnCreate(employee)) {
            throw new InvalidEmailException();
        }

        return new ResponseEntity<>(empService.save(employee), HttpStatus.CREATED);
    }

    /**
     * Partial update employee records
     * */
    @PatchMapping(path="/{id}", consumes = "application/json")
    public ResponseEntity<Employee> partialUpdate(@PathVariable("id") long id, @RequestBody @Validated Employee patchEmployee) throws NoRecordFoundException, InvalidEmailException {

        if (!empService.existsById(id)) {
            throw new NoRecordFoundException();
        }

        patchEmployee.setEmployeeId(id);

        if (!empService.isUniqueEmailOnUpdate(patchEmployee)) {
            throw new InvalidEmailException();
        }

        return new ResponseEntity<>(empService.update(patchEmployee), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") long id) throws Exception {
        try {
            empService.deleteById(id);
        } catch(EmptyResultDataAccessException e) {
            // can be written to the app log
        }
    }

    @GetMapping(params = {"page", "size"})
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Employee> findPaginatedEmployees(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageAndSize = PageRequest.of(page, size);
        return empService.getAllByPage(pageAndSize);
    }
}
