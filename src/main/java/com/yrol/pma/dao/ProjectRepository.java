package com.yrol.pma.dao;

import org.springframework.data.repository.CrudRepository;

import com.yrol.pma.entities.Project;

/**
 * ProjectRepository Interface which allows to perform CRUP operations for Project entity using CrudRepository
 * CrudRepository accepts the entity and the unique ID's type which is Long (projectId)
 * */
public interface ProjectRepository extends CrudRepository<Project, Long> {
	
}
