package com.yrol.pma.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Marking the Employee as an Entity. Hence, Sprint Boot will create a table in
 * the DB for Employee with the required fields
 */
@Entity
public class Employee {

	/**
	 * @GeneratedValue is an annotation available within Spring to map Java objects
	 *                 automatically to DB tables.
	 * @Id annotation is used for unique ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long employeeId;

	private String firstName;
	private String lastName;
	private String email;
	
	/**
	 * ManyToOne relationship where many Employees(child) can be under one Project(Parent)
	 * Using Cascade Types:
	 * > DETACH - detach Employees when a Project gets deleted
	 * > MERGE - merge Employees when a Projects get combined
	 * > PERSISTS - When Projects get saved Employees will also be saved / persisted
	 * > REFRESH - if Projects get saved/updated, refresh Employees
	 * Using FetchType LAZY instead of EAGER where it'll load only Projects(parents) without the Employees(child), and can make a separate call to load Employees when needed.
	 * */
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
			fetch = FetchType.LAZY)
	@JoinColumn(name="project_id")
	private Project theProject;
	
	public Project getTheProject() {
		return theProject;
	}

	public void setTheProject(Project theProject) {
		this.theProject = theProject;
	}

	public Employee() {
		
	}

	public Employee(long employeeId, String firstName, String lastName, String email) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	
	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
