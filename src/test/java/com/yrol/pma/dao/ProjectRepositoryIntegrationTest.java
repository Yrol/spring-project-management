package com.yrol.pma.dao;

import com.yrol.pma.api.controllers.EmployeeApiController;
import com.yrol.pma.entities.Employee;
import com.yrol.pma.entities.Project;
import com.yrol.pma.enums.projects.Stages;
import com.yrol.pma.services.EmployeeService;
import com.yrol.pma.services.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Integration Tests
 *
 * @SpringBootTest -  will load the entire context of the application.
 * @ContextConfiguration - Specifying the starting point of the application which helps to load beans & ect (from SpringContext)
 * @DataJpaTest - Used for writing test cases with temporary DBs
 * @SqlGroup - Used for SQL integrations with tests as well as for specify the classes that should run before and after executing the test cases
 */
//@ContextConfiguration(classes = ProjectManagementApplication.class) - required if @SpringBootTest is not defined
//@DataJpaTest - required with @ContextConfiguration

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql", "classpath:data.sql"}),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = {"classpath:drop.sql"})})
class ProjectRepositoryIntegrationTest {

    @Autowired
    ProjectService projService;

    @Autowired
    EmployeeService empService;

    @Autowired
    MockMvc mockMvc;


    @Test
    public void if_new_project_saved_then_success() {
        Project newProject = new Project("New Test Project", Stages.COMPLETED, "This is a new Test Project");
        projService.save(newProject);
        assertEquals(4, projService.getAll().size());
    }

    @Test
    public void if_new_employee_saved_then_success() {
        Employee newEmployee = new Employee("Damian", "Silva", "damian.silva@gmx.com");
        empService.save(newEmployee);
        assertEquals(7, empService.getAll().size());
    }
}
