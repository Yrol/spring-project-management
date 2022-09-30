package com.yrol.pma.validation.employees;

import com.yrol.pma.validation.useraccount.UniqueEmailValidator;

import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Constraint(validatedBy = UniqueEmployeeValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmployee {
    String message() default "Employee with the same email already exists.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
