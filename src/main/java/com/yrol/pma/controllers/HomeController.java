package com.yrol.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yrol.pma.dao.ProjectRepository;
import com.yrol.pma.entities.Project;

@Controller
public class HomeController {
	
	//@Autowired used for handling DI automatically
	@Autowired
	ProjectRepository proRepo;
	
	/**
	 * @GetMapping is similar to using "RequestMethod.GET" within @RequestMapping. For post use @PostMapping
	 * */
	@GetMapping("/")
	public String displayHome(Model model) {
		
		List<Project> projects = proRepo.findAll();
		
		model.addAttribute("projects", projects);
		
		return "home";
	}
}