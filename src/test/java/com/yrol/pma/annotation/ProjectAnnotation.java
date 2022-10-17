package com.yrol.pma.annotation;

import com.yrol.pma.entities.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ProjectAnnotation {

    @Autowired
    Validator validator;

    @Test
    public void project_name_must_not_be_empty() {
        Project project =  new Project();
        assertEquals("must not be empty", getValidatorMessage(project, "name"));
    }

    @Test
    public void project_name_must_be_2_and_50_characters() {

        Project project =  new Project();

        // Less than 2
        project.setName("T");
        assertEquals("The Project name must be between 2 and 50 characters.", getValidatorMessage(project, "name"));

        // More than 50
        project.setName("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's");
        assertEquals("The Project name must be between 2 and 50 characters.", getValidatorMessage(project, "name"));

        // Valid project name
        project.setName("Toyota");
        Set<ConstraintViolation<Project>> violations = validator.validate(project);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void project_description_must_not_exceed_50_characters() {
        Project project =  new Project();
        project.setDescription("Message interpolation is the process of creating error messages for violated Bean Validation constraints. " +
                "In this chapter you will learn how such messages are defined and resolved and how you can plug in custom message interpolators in " +
                "case the default algorithm is not sufficient for your requirements.");
        assertEquals("The Project description cannot exceed 200 characters.", getValidatorMessage(project, "description"));
    }

    public String getValidatorMessage(Project project, String field) {
        return validator.validateProperty( project, field )
                .iterator()
                .next()
                .getMessage();
    }
}
