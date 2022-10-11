package com.yrol.pma.services;

import com.yrol.pma.dao.ProjectRepository;
import com.yrol.pma.dto.ProjectStageCount;
import com.yrol.pma.entities.Employee;
import com.yrol.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public boolean existsById(Long id) {
        return projRepo.existsById(id);
    }

    public Project findById(Long id) {
        return projRepo.findById(id).get();
    }

    public List<Project> getAll(){
        return projRepo.findAll();
    }

    public Page<Project> getAllByPage(Pageable pageAndSize) {
        return projRepo.findAll(pageAndSize);
    }

    public Project save(Project project) {
        return projRepo.save(project);
    }

    public Project update(Project patchProj) {
        Project currentProj = projRepo.findById(patchProj.getProjectId()).get();
        currentProj.setName(patchProj.getName());
        currentProj.setStage(patchProj.getStage());
        currentProj.setDescription(patchProj.getDescription());
        return projRepo.save(currentProj);
    }

    public void deleteById(Long id) {
        projRepo.deleteById(id);
    }

    public List<ProjectStageCount> stageCount() {
        return projRepo.projectStageCount();
    }

    public boolean isUniqueProjectOnCreate(Project project) {
        List<Project> projects = projRepo.findByName(project.getName());
        return projects.size() == 0;
    }

    public boolean isUniqueNameOnUpdate(Project patchProject) {

        Project currentProj = projRepo.findById(patchProject.getProjectId()).get();

        String patchName = patchProject.getName();
        String currentName = currentProj.getName();

        if (!patchName.equals(currentName) && projRepo.findByName(patchName).size() > 0) {
            return false;
        }
        return true;
    }
}
