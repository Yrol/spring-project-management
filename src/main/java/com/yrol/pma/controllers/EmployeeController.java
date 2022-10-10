package com.yrol.pma.controllers;

import java.util.List;

import com.yrol.pma.dto.EmployeeProject;
import com.yrol.pma.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

		List<EmployeeProject> employeesProjects = empService.employeeProjects();
		
		//Passing Employees object (fetching all existing)
		model.addAttribute("employeesProjects", employeesProjects);
		
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

		if (!empService.isUniqueEmailOnCreate(employee)) {
			result.rejectValue("email", "employee.email", "An account already exists for this email.");
		}

		if(result.hasErrors()) {
			model.addAttribute("employee", employee);
			return "employees/new-employee";
		}

		empService.save(employee);

		//redirect
		return "redirect:/employees";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateEmployee(Model model, @Valid Employee employee, BindingResult result) {

		//Validating the email when updating
		if (!empService.isUniqueEmailOnUpdate(employee)) {
			result.rejectValue("email", "employee.email", "An account already exists for this email.");
		}

		if(result.hasErrors()) {
			model.addAttribute("employee", employee);
			return "employees/new-employee";
		}

		empService.update(employee);

		//redirect
		return "redirect:/employees";
	}


	@GetMapping(value = "/update/{id}")
	public String displayUpdateEmployeeForm(Model model, @PathVariable Long id) {

		if (!empService.existsById(id)) {
			return "redirect:/employees";
		}

		Employee employee = empService.findById(id);

		model.addAttribute("employee", employee);

		return "employees/new-employee";
	}

	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable Long id) {

		try {
			empService.deleteById(id);
		} catch (EmptyResultDataAccessException e) {

		}

		return "redirect:/employees";
	}
}
