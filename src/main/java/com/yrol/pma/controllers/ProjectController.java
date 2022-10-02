package com.yrol.pma.controllers;

import java.util.List;

import com.yrol.pma.services.EmployeeService;
import com.yrol.pma.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yrol.pma.entities.Employee;
import com.yrol.pma.entities.Project;

import javax.validation.Valid;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	// @Autowired used for handling DI automatically
	@Autowired
	ProjectService projService;


	@Autowired
	EmployeeService empService;

	@RequestMapping("")
	public String displayProjects(Model model) {

		List<Project> projects = projService.getAll();

		model.addAttribute("projects", projects);

		return "projects/list-projects";
	}

	@RequestMapping("/new")
	public String displayProjectForm(Model model) {

		// creating a project object
		Project project = new Project();

		List<Employee> employees = empService.getAll();

		model.addAttribute("project", project);
		model.addAttribute("allEmployees", employees);

		return "projects/new-project";
	}

	/**
	 * @RequestParam employees are coming from the request payload
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String createProject(Model model, @Valid Project project, BindingResult result) {

		if(result.hasErrors()) {
			model.addAttribute("project", project);
			return "projects/new-project";
		}

		// Saving data using ProjectRepository
		projService.save(project);

		/**
		 * The following only need to be used in OneToMany relationship between Projects
		 * and Employees (Projects can have multiple employees and an employee can be
		 * assigned to one project). In ManyToMany relationship Spring is smart enough to store save data to the intermediary relationship table.
		 * Must use "@RequestParam List<Long> employees" in the constructor as the form passes "employees" param.
		 */
//		Iterable<Employee> chosenEmployees = empRepo.findAllById(employees);
//
//		// Using setter and Hibernate will set the project automatically
//		for (Employee employee : chosenEmployees) {
//			employee.setTheProjects(project);
//			empRepo.save(employee);
//		}

		// redirect
		return "redirect:/projects";
	}

}
