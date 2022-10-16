package com.yrol.pma.api.controllers;

import com.yrol.pma.entities.Project;
import com.yrol.pma.exceptions.generic.InvalidProjectNameException;
import com.yrol.pma.exceptions.generic.NoRecordFoundException;
import com.yrol.pma.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {

    @Autowired
    ProjectService projService;

    /**
     * Fetch all projects
     * */
    @GetMapping
    public List<Project> getProjects() {
        return projService.getAll();
    }

    /**
     * Find a project by ID
     * findById is being inherited from CrudRepository
     * */
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") Long id) {

        if (!projService.existsById(id)) {
            throw new NoRecordFoundException();
        }

        return new ResponseEntity<>(projService.findById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Project> createProject(@RequestBody @Valid Project project) throws InvalidProjectNameException {

        if (!projService.isUniqueProjectOnCreate(project)) {
            throw new InvalidProjectNameException();
        }

        return new ResponseEntity<>(projService.save(project), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Project> partialUpdateProject(@PathVariable("id") Long id, @RequestBody @Valid Project patchProject) throws NoRecordFoundException, InvalidProjectNameException {

        if (!projService.existsById(id)) {
            throw new NoRecordFoundException();
        }

        patchProject.setProjectId(id);

        if (!projService.isUniqueNameOnUpdate(patchProject)) {
            throw new InvalidProjectNameException();
        }

        return new ResponseEntity<>(projService.update(patchProject), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProject(@PathVariable("id") long id) throws Exception {
        try {
            projService.deleteById(id);
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
        return projService.getAllByPage(pageAndSize);
    }
}
