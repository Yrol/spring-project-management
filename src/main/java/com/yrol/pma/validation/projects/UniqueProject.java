package com.yrol.pma.validation.projects;

import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueProjectValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueProject {
    String message() default "Project with the same name already exists.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
