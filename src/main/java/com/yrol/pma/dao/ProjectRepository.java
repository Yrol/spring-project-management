package com.yrol.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.yrol.pma.dto.ProjectStageCount;
import com.yrol.pma.entities.Project;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * ProjectRepository Interface which allows to perform CRUP operations for Project entity using CrudRepository (without pagination)
 * CrudRepository accepts the entity and the unique ID's type which is Long (projectId)
 * PagingAndSortingRepository an extension of CrudRepository which allows pagination.
 * */
//public interface ProjectRepository extends CrudRepository<Project, Long> {
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
	
	/**
	 * Overriding the "findAll()" of CrudRepository to return a List instead of type Iterable
	 * */
	@Override
	public List<Project> findAll();
	
	@Query(nativeQuery = true, value = "SELECT stage, COUNT(*) as projectStageCount from project GROUP BY stage")
	public List<ProjectStageCount> projectStageCount();

	/**
	 * Method 1: using queries manually.
	 * */
//	@Query(nativeQuery = true, value = "SELECT * FROM project WHERE name = :projectName")
//	public List<Project> projectByName(@Param("projectName") String projectName);

	/**
	 * Method 2: automatic queries using exact attribute name along with "findBy" - findBy<Attribute>
	 * */
	public List<Project> findByName(String projectName);

}
