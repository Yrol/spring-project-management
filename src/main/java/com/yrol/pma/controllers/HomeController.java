package com.yrol.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yrol.pma.dto.ProjectStageCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yrol.pma.dao.EmployeeRepository;
import com.yrol.pma.dao.ProjectRepository;
import com.yrol.pma.dto.EmployeeProject;
import com.yrol.pma.entities.Employee;
import com.yrol.pma.entities.Project;

@Controller
public class HomeController {
	
	//@Autowired used for handling DI automatically
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	/**
	 * @GetMapping is similar to using "RequestMethod.GET" within @RequestMapping. For post use @PostMapping
	 * */
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {

		Map<String, Object> map = new HashMap<>();
		
		List<Project> projects = proRepo.findAll();
		List<EmployeeProject> employeesProjectCount = empRepo.employeeProjects();
		List<ProjectStageCount> projectStageCount = proRepo.projectStageCount();

		//converting the projectStageCount to JSON to be used by the home template
		ObjectMapper objectMapper = new ObjectMapper();

		//will look like - [["NOTSTARTED", 1], ["INPROGRESS", 2], ["COMPLETED", 5]]
		String jsonString = objectMapper.writeValueAsString(projectStageCount);
		
		//Adding the projects
		model.addAttribute("projects", projects);
		model.addAttribute("employeesProjectCount", employeesProjectCount);
		model.addAttribute("projectStageCount", jsonString);
		
		return "/main/home";
	}
}
