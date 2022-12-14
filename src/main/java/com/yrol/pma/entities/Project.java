package com.yrol.pma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yrol.pma.enums.projects.Stages;
import com.yrol.pma.validation.projects.UniqueProject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Marking the Project as an Entity. Hence, Sprint Boot will create a table in
 * The DB for Project with the required fields
 * Using Lombok functions - @Data, @AllArgsConstructor and @NoArgsConstructor to remove boilerplate setters, getters and constructors (handle them automatically).
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project {

	/**
	 * @GeneratedValue is an annotation available within Spring to map Java objects
	 *                 automatically to DB tables.
	 * GenerationType.SEQUENCE -  will rely on database for the next available ID
	 * @SequenceGenerator - using the sequence generator created in Postgres - project_seq
	 * @Id annotation is used for unique ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "project_seq")
	@SequenceGenerator(name = "project_seq", allocationSize = 1)
	private long projectId;

	@NotEmpty
	@Column(unique = true)
	@Size(min = 2, max = 50, message = "{Size.Project.name}")
//	@UniqueProject
	private String name;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Stages stage;

	@Size(max = 200, message = "{Size.Project.description}")
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
	 *  @JoinTable Will create an intermediate join table called "project_employee"
	 *  joinColumns will be used for defining relationship columns of both Project and Employee tables.
	 *	@JsonIgnore - JSON API related prevent returning the Many-to-Many relationship data since running into an infinite loop
	 *  Using CascadeTypes to make sure. 
	 *  Using FetchType LAZY instead of EAGER loading.
	 *  Method 3
	 * */
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.LAZY)
	@JoinTable(name="project_employee", joinColumns =@JoinColumn(name="project_id"), inverseJoinColumns = @JoinColumn(name="employee_id"))
	@JsonIgnore
	private List<Employee> employees;


	// Custom constructor without ID param
	public Project(String name, Stages stage, String description) {
		super();
		this.name = name;
		this.stage = stage;
		this.description = description;
	}
	
	/**
	 * Convenience method used for seeding & etc.
	 * Initiate the above "employees" List if not exist and add the employee
	 * */
	public void addEmployee(Employee emp) {
		if(employees==null) {
			employees = new ArrayList<>();
		}
		employees.add(emp);
	}

}
