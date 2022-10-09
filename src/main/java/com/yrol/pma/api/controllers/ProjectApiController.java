package com.yrol.pma.api.controllers;

import com.yrol.pma.dao.ProjectRepository;
import com.yrol.pma.entities.Employee;
import com.yrol.pma.entities.Project;
import com.yrol.pma.enums.projects.Stages;
import com.yrol.pma.exceptions.generic.InvalidProjectNameException;
import com.yrol.pma.exceptions.generic.NoRecordFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {

    @Autowired
    ProjectRepository projRepo;

    /**
     * Fetch all projects
     * */
    @GetMapping
    public List<Project> getProjects() {
        return projRepo.findAll();
    }

    /**
     * Find a project by ID
     * findById is being inherited from CrudRepository
     * */
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(projRepo.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        return new ResponseEntity<>(projRepo.save(project), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Project> partialUpdateProject(@PathVariable("id") Long id, @RequestBody Project patchProject) {

        Optional<Project> currentProjectInit = projRepo.findById(id);

        //check if project exist
        if (!currentProjectInit.isPresent()) {
            throw new NoRecordFoundException();
        }

        Project currentProject = currentProjectInit.get();

        String patchProjName = patchProject.getName();
        String currentProjName = currentProject.getName();

        String patchProjectStage = patchProject.getStage().toString();

        //Validate unique project name
        if (!currentProjName.equals(patchProjName) && projRepo.findByName(patchProjName).size() > 0) {
            throw new InvalidProjectNameException();
        }

        currentProject.setName(patchProjName);

        try {
            currentProject.setStage(Stages.valueOf(patchProjectStage));
        } catch(IllegalArgumentException e) {
            //add to logs
        }

        currentProject.setDescription(patchProject.getDescription());

        return new ResponseEntity<>(projRepo.save(currentProject), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProject(@PathVariable("id") long id) throws Exception {
        try {
            projRepo.deleteById(id);
        } catch(EmptyResultDataAccessException e) {
            // can be written to the app log
        }
    }

    /**
     * Endpoint for paginated results
     * ex: localhost:8080/app-api/projects?page=0&size=10
     * */
    @GetMapping(params = {"page", "size"})
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Project> findPaginatedProjects(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageAndSize = PageRequest.of(page, size);
        return projRepo.findAll(pageAndSize);
    }
}
