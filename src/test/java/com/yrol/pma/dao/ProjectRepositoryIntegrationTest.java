package com.yrol.pma.dao;

import com.yrol.pma.entities.Project;
import com.yrol.pma.services.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Integration Tests
 *
 * @SpringBootTest
 * @ContextConfiguration - Specifying the starting point of the application which helps to load beans & ect (from SpringContext)
 * @DataJpaTest - Used for writing test cases with temporary DBs
 * @SqlGroup - Used for SQL integrations with tests as well as for specify the classes that should run before and after executing the test cases
 */
//@ContextConfiguration(classes = ProjectManagementApplication.class) - required if @SpringBootTest is not defined
//@DataJpaTest - required with @ContextConfiguration

@SpringBootTest
@ExtendWith(SpringExtension.class)
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql", "classpath:data.sql"}),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = {"classpath:drop.sql"})})
class ProjectRepositoryIntegrationTest {

    @Autowired
    ProjectService projService;

    @Test
    public void if_new_project_saved_then_success() {
        Project newProject = new Project("New Test Project", "COMPLETED", "This is a new Test Project");
        projService.save(newProject);
        assertEquals(4, projService.getAll().size());
    }
}
