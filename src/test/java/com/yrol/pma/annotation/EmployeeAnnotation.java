package com.yrol.pma.annotation;

import com.yrol.pma.entities.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class EmployeeAnnotation {

    @Autowired
    Validator validator;

    @Test
    public void employee_mandatory_fields_must_not_be_empty() {
        Employee employee =  new Employee();
        assertEquals("must not be empty", getValidatorMessage(employee, "firstName"));
        assertEquals("must not be empty", getValidatorMessage(employee, "lastName"));
        assertEquals("must not be empty", getValidatorMessage(employee, "email"));
    }

    @Test
    public void employee_email_must_be_valid() {
        Employee employee =  new Employee();
        employee.setEmail("Hello");
        assertEquals("must be a well-formed email address", getValidatorMessage(employee, "email"));

        employee.setEmail("james.webb@nasa.com");
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void employee_first_name_must_be_2_and_50_characters() {
        Employee employee =  new Employee();

        //less than 2 chars
        employee.setFirstName("T");
        assertEquals("The Firstname must be between 2 and 50 characters.", getValidatorMessage(employee, "firstName"));

        //more than 50 chars
        employee.setFirstName("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's");
        assertEquals("The Firstname must be between 2 and 50 characters.", getValidatorMessage(employee, "firstName"));

        employee.setFirstName("Damian");
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void employee_last_name_must_be_2_and_50_characters() {
        Employee employee =  new Employee();

        //less than 2 chars
        employee.setLastName("T");
        assertEquals("The Last name must be between 2 and 50 characters.", getValidatorMessage(employee, "lastName"));

        //more than 50 chars
        employee.setLastName("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's");
        assertEquals("The Last name must be between 2 and 50 characters.", getValidatorMessage(employee, "lastName"));

        employee.setLastName("Roberts");
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        assertFalse(violations.isEmpty());
    }

    public String getValidatorMessage(Employee employee, String field) {
        return validator.validateProperty( employee, field )
                .iterator()
                .next()
                .getMessage();
    }
}
