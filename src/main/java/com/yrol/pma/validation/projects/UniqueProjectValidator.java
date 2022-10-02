package com.yrol.pma.validation.projects;

import com.yrol.pma.dao.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueProjectValidator implements ConstraintValidator<UniqueProject, String> {

    @Autowired
    ProjectRepository projectRepo;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return projectRepo.projectByName(name).size() == 0;
    }
}
