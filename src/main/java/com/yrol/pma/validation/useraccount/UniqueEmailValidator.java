package com.yrol.pma.validation.useraccount;

import com.yrol.pma.dao.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    SecurityRepository securityRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return securityRepository.getUserByEmail(email).size() == 0;
    }
}
