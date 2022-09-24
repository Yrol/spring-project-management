package com.yrol.pma.services;

import com.yrol.pma.dao.ProjectRepository;
import com.yrol.pma.dto.ProjectStageCount;
import com.yrol.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * An intermediary service that'll abstract all the project actions.
 * Consumed by the Controllers.
 * */


@Service
public class ProjectService {

    @Autowired
    ProjectRepository projRepo;

    public List<Project> getAll(){
        return projRepo.findAll();
    }

    public Project save(Project project) {
        return projRepo.save(project);
    }

    public List<ProjectStageCount> stageCount() {
        return projRepo.projectStageCount();
    }
}
