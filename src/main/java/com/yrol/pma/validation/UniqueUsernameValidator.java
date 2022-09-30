package com.yrol.pma.validation;

import com.yrol.pma.dao.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    SecurityRepository securityRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return securityRepository.getUsersByUsername(username).size() == 0;
    }
}
