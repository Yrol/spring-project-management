package com.yrol.pma.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.yrol.pma.entities.Project;

/**
 * ProjectRepository Interface which allows to perform CRUP operations for Project entity using CrudRepository
 * CrudRepository accepts the entity and the unique ID's type which is Long (projectId)
 * */
public interface ProjectRepository extends CrudRepository<Project, Long> {
	
	/**
	 * Overriding the "findAll()" of CrudRepository to return a List instead of Iterable
	 * */
	@Override
	public List<Project> findAll();
}
