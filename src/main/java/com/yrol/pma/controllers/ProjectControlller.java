package com.yrol.pma.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yrol.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectControlller {

	@RequestMapping("/new")
	public String displayProjectForm(Model model) {

		// creating an e
		Project project = new Project();

		model.addAttribute("project", project);

		return "new-project";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String createProject(Project project, Model model) {
		// Saving data using a CRUD repository class
		return "";
	}

}
