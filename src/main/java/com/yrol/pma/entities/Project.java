package com.yrol.pma.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * Marking the Project as an Entity. Hence, Sprint Boot will create a table in
 * the DB for Project with the required fields
 */
@Entity
public class Project {

	/**
	 * @GeneratedValue is an annotation available within Spring to map Java objects
	 *                 automatically to DB tables.
	 * @Id annotation is used for unique ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long projectId;

	private String name;
	
	private String stage;
	
	private String description;
	
	/**
	 *  @OneToMany relationship where project(parent) can be assigned to many employees(child)
	 *  Will create a new table to maintain project IDs and employee IDs
	 *  Method 1
	 * */
//	@OneToMany
//	private List<Employee> employees;
	
	/**
	 *  @OneToMany relationship where project can be assigned to many employees (an employee can be assigned to only one project)
	 *  Will create a new field in the Employee table (PROJECT_ID)
	 *  Method 2
	 * */
//	@OneToMany(mappedBy = "theProject")
//	private List<Employee> employees;
	
	
	/**
	 *  @ManyToMany relationship where project can be assigned to many employees and vice versa
	 *  @JoinTable Will create an intermediately join table called "project_employee"
	 *  joinColumns will be used for defining relationship columns of both Project and Employee tables.
	 *  Using CascadeTypes to make sure. 
	 *  Using FetchType LAZY instead of EAGER loading.
	 *  Method 3
	 * */
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.LAZY)
	@JoinTable(name="project_employee", joinColumns =@JoinColumn(name="project_id"), inverseJoinColumns = @JoinColumn(name="employee_id"))
	private List<Employee> employees;
	

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Project() {

	} 

	public Project(String name, String stage, String description) {
		super();
		this.name = name;
		this.stage = stage;
		this.description = description;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
