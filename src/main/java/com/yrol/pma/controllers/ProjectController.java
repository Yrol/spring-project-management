package com.yrol.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yrol.pma.dao.EmployeeRepository;
import com.yrol.pma.dao.ProjectRepository;
import com.yrol.pma.entities.Employee;
import com.yrol.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	// @Autowired used for handling DI automatically
	@Autowired
	ProjectRepository proRepo;

	@Autowired
	EmployeeRepository empRepo;

	@RequestMapping("")
	public String displayProjects(Model model) {

		List<Project> projects = proRepo.findAll();

		model.addAttribute("projects", projects);

		return "/projects/list-projects";
	}

	@RequestMapping("/new")
	public String displayProjectForm(Model model) {

		// creating a project object
		Project project = new Project();

		List<Employee> employees = empRepo.findAll();

		model.addAttribute("project", project);
		model.addAttribute("allEmployees", employees);

		return "/projects/new-project";
	}

	/**
	 * @RequestParam employees are coming from the request payload
	 * */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String createProject(Project project, @RequestParam List<Long> employees) {
		// Saving data using ProjectRepository
		proRepo.save(project);

		Iterable<Employee> chosenEmployees = empRepo.findAllById(employees);

		// Using setter and Hibernate will set the project automatically
		for (Employee employee : chosenEmployees) {
			employee.setTheProject(project);
			empRepo.save(employee);
		}

		// redirect
		return "redirect:/projects/new";
	}

}
