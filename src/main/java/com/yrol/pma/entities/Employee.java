package com.yrol.pma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yrol.pma.validation.employees.UniqueEmployee;
import lombok.*;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Marking the Employee as an Entity. Hence, Sprint Boot will create a table in
 * The DB for Employee with the required fields
 * Using Lombok functions - @Data, @AllArgsConstructor and @NoArgsConstructor to remove boilerplate setters and getters and constructors.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	/**
	 * @GeneratedValue is an annotation available within Spring to map Java objects
	 *                 automatically to DB tables.
	 * GenerationType.SEQUENCE -  will rely on database for the next available ID
	 * @SequenceGenerator - using the sequence generator created in Postgres - employee_seq
	 * @Id annotation is used for unique ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "employee_seq")
	@SequenceGenerator(name = "employee_seq", allocationSize = 1)
	private long employeeId;

	@NotEmpty
	@Size(min = 2, max = 50, message = "{Size.Employee.firstName}")
	private String firstName;

	@NotEmpty
	@Size(min = 2, max = 50, message = "{Size.Employee.lastName}")
	private String lastName;

	@NotEmpty
	@Email
//	@UniqueEmployee
	@Column(unique = true)
	private String email;
	
	/**
	 * Correlates to Method 2 in Project.java
	 * @ManyToOne relationship where many Employees(child) can be under one Project(Parent)
	 * Using Cascade Types:
	 * > DETACH - detach Employees when a Project gets deleted
	 * > MERGE - merge Employees when a Projects get combined
	 * > PERSISTS - When Projects get saved Employees will also be saved / persisted
	 * > REFRESH - if Projects get saved/updated, refresh Employees
	 * Using FetchType LAZY instead of EAGER where it'll load only Projects(parents) without the Employees(child), and can make a separate call to load Employees when needed.
	 * */
//	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
//			fetch = FetchType.LAZY)
//	@JoinColumn(name="project_id")
//	private Project theProject;
	
	/**
	 * Correlates to Method 3 in Project.java 
	 * @ManyToMany relationship where many Employees can be assigned with many projects
	 * @JoinTable Will create an intermediate join table called "project_employee"
	 * joinColumns will be used for defining relationship columns of both Project and Employee tables.
	 * Using Cascading rules
	 * @JsonIgnore - JSON API related prevent returning the Many-to-Many relationship data since running into an infinite loop
	 * */
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
	fetch = FetchType.LAZY)
	@JoinTable(name="project_employee", joinColumns=@JoinColumn(name="employee_id"), inverseJoinColumns = @JoinColumn(name="project_id"))
	@JsonIgnore
	public List<Project> theProjects;

//	public List<Project> getTheProjects() {
//		return theProjects;
//	}
//
//	public void setTheProjects(List<Project> theProjects) {
//		this.theProjects = theProjects;
//	}

	
	
//	private Project theProject;
//	
//	public Project getTheProject() {
//		return theProject;
//	}
//
//	public void setTheProject(Project theProject) {
//		this.theProject = theProject;
//	}

//	public Employee() {
//
//	}
//
	public Employee(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
//
//
//	public long getEmployeeId() {
//		return employeeId;
//	}
//
//	public void setEmployeeId(long employeeId) {
//		this.employeeId = employeeId;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
}
