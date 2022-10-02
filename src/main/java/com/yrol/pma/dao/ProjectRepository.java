package com.yrol.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.yrol.pma.dto.ProjectStageCount;
import com.yrol.pma.entities.Project;
import org.springframework.data.repository.query.Param;

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
	
	@Query(nativeQuery = true, value = "SELECT stage, COUNT(*) as projectStageCount from project GROUP BY stage")
	public List<ProjectStageCount> projectStageCount();

	@Query(nativeQuery = true, value = "SELECT * FROM project WHERE name = :projectName")
	public List<Project> projectByName(@Param("projectName") String projectName);
}
