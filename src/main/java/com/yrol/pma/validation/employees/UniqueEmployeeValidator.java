package com.yrol.pma.validation.employees;

import com.yrol.pma.dao.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmployeeValidator implements ConstraintValidator<UniqueEmployee, String> {

    @Autowired
    EmployeeRepository empRepo;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return empRepo.findByEmail(email).size() == 0;
    }

}
