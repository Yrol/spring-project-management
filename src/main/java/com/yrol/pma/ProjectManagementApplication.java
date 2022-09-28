package com.yrol.pma;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.yrol.pma.dao.EmployeeRepository;
import com.yrol.pma.dao.ProjectRepository;
import com.yrol.pma.entities.Employee;
import com.yrol.pma.entities.Project;

//@SpringBootApplication( exclude = {SecurityAutoConfiguration.class} ) // use this for disabling the Spring security completely
@SpringBootApplication
public class ProjectManagementApplication {

	@Autowired
	EmployeeRepository empRepo;

	@Autowired
	ProjectRepository projRepo;

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runner() {
//		return args -> {
//
//			Employee emp1 = new Employee("John", "Warton", "warton@gmail.com");
//			Employee emp2 = new Employee("Mike", "Lanister", "lanister@gmail.com");
//			Employee emp3 = new Employee("Steve", "Reeves", "reeves@gmail.com");
//
//			Employee emp4 = new Employee("Roland", "Carson", "carson@gmail.com");
//			Employee emp5 = new Employee("Honor", "Miles", "miles@gmail.com");
//			Employee emp6 = new Employee("Tony", "Ferguson", "ferguson@gmail.com");
//
//			Project proj1 = new Project("The MBQ Project", "NOTSTARTED", "This is the MBQ project");
//			Project proj2 = new Project("The BMW Project", "INPROGRESS", "This is the BMW project");
//			Project proj3 = new Project("The Toyota Project", "COMPLETED", "This is the Toyota project");
//
//			// Assigning employees to the project
//			proj1.addEmployee(emp1);
//			proj1.addEmployee(emp3);
//			proj1.addEmployee(emp6);
//
//			proj2.addEmployee(emp5);
//			proj2.addEmployee(emp6);
//
//			proj3.addEmployee(emp1);
//			proj3.addEmployee(emp2);
//			proj3.addEmployee(emp3);
//			proj3.addEmployee(emp4);
//
//			// Assigning projects to the employees (other side of the relationship)
//			emp1.setTheProjects(Arrays.asList(proj1, proj3));
//			emp2.setTheProjects(Arrays.asList(proj3));
//			emp3.setTheProjects(Arrays.asList(proj1, proj3));
//			emp4.setTheProjects(Arrays.asList(proj3));
//			emp5.setTheProjects(Arrays.asList(proj2));
//			emp6.setTheProjects(Arrays.asList(proj1, proj2));
//
//			// saving data
//			empRepo.save(emp1);
//			empRepo.save(emp2);
//			empRepo.save(emp3);
//			empRepo.save(emp4);
//			empRepo.save(emp5);
//			empRepo.save(emp6);
//
//			// saving projects
//			projRepo.save(proj1);
//			projRepo.save(proj2);
//			projRepo.save(proj3);
//
//		};
//	}
}
