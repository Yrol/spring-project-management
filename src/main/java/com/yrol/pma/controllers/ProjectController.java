package com.yrol.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yrol.pma.dao.ProjectRepository;
import com.yrol.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	//@Autowired used for handling DI automatically
	@Autowired
	ProjectRepository proRepo;
	
	@RequestMapping("")
	public String displayProjects(Model model) {
		
		List<Project> projects = proRepo.findAll();
		
		model.addAttribute("projects", projects);
		
		return "/projects/list-projects";
	}

	@RequestMapping("/new")
	public String displayProjectForm(Model model) {

		//creating a project object
		Project project = new Project();

		model.addAttribute("project", project);

		return "/projects/new-project";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String createProject(Project project) {
		// Saving data using ProjectRepository
		proRepo.save(project);
		
		//redirect
		return "redirect:/projects/new";
	}

}
