package com.yrol.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yrol.pma.dao.EmployeeRepository;
import com.yrol.pma.entities.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	//@Autowired used for handling DI automatically
	@Autowired
	EmployeeRepository empRepo;
	
	@RequestMapping("/new")
	public String displayEmployeeForm(Model model) {
		
		Employee employee = new Employee();
		
		model.addAttribute("employee", employee);
		
		return "new-employee";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String createEmployee(Employee employee) {
	
		// Saving data using EmployeeRepository
		empRepo.save(employee);
		
		//redirect
		return "redirect:/employees/new";
	}
}
