package com.yrol.pma.controllers;

import java.util.List;

import com.yrol.pma.dto.EmployeeProject;
import com.yrol.pma.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yrol.pma.entities.Employee;

import javax.validation.Valid;

@Controller
@RequestMapping("/employees")
public class  EmployeeController {
	
	//@Autowired used for handling DI automatically
	@Autowired
	EmployeeService empService;
	
	@RequestMapping("")
	public String displayEmployees(Model model) {
		
		List<Employee> employees = empService.getAll();
		List<EmployeeProject> employeesProjectCount = empService.employeeProjects();
		
		//Passing Employees object (fetching all existing)
		model.addAttribute("employeesProjectCount", employeesProjectCount);
		
		return "employees/list-employees";
	}
	
	@RequestMapping("/new")
	public String displayEmployeeForm(Model model) {
		
		Employee employee = new Employee();
		
		//Passing an Employee object to create a new employee
		model.addAttribute("employee", employee);
		
		return "employees/new-employee";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String createEmployee(Model model, @Valid Employee employee, BindingResult result) {

		if(result.hasErrors()) {
			model.addAttribute("employee", employee);
			return "employees/new-employee";
		}
	
		// Saving data using EmployeeRepository
		empService.save(employee);
		
		//redirect
		return "redirect:/employees";
	}
}
