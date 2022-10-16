package com.yrol.pma.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yrol.pma.entities.Project;
import com.yrol.pma.enums.projects.Stages;
import com.yrol.pma.services.ProjectService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @DirtiesContext will clear the context, hence no existing data will be carried over to the next test.
 * */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class ProjectRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProjectService projService;

    @Test
    public void it_allows_to_fetch_all_projects () throws Exception {

        ResultActions beforeInsertResponse = mockMvc.perform(MockMvcRequestBuilders.get("/app-api/projects"));

        beforeInsertResponse.andExpect(MockMvcResultMatchers.status().isOk());
        beforeInsertResponse.andExpect(MockMvcResultMatchers.jsonPath("$.length()", CoreMatchers.is(0)));

        Project newProject = new Project("The BMW Project", Stages.COMPLETED, "This is the BMW project");
        projService.save(newProject);

        ResultActions afterInsertResponse = mockMvc.perform(MockMvcRequestBuilders.get("/app-api/projects"));

        afterInsertResponse.andExpect(MockMvcResultMatchers.status().isOk());
        afterInsertResponse.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(1)));
    }

    @Test
    public void it_allows_to_get_project_by_id () throws Exception {

        ResultActions beforeInsertResponse = mockMvc.perform(MockMvcRequestBuilders.get("/app-api/projects/{id}", 1)
                .accept(MediaType.APPLICATION_JSON));
        beforeInsertResponse.andExpect(MockMvcResultMatchers.status().isNotFound());

        Project newProject = new Project("The Honda Project", Stages.COMPLETED, "This is the Honda project");
        projService.save(newProject);

        ResultActions afterInsertResponse = mockMvc.perform(MockMvcRequestBuilders.get("/app-api/projects/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
        afterInsertResponse.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.projectId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("The Honda Project"));
    }

    @Test
    public void it_allows_to_create_new_projects () throws Exception {
        ResultActions ibmProject = createNewProject(new Project("IBM", Stages.COMPLETED, "The IBM Project"));
        ibmProject.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("IBM"));
    }

    @Test
    public void it_allows_to_update_existing_projects () throws Exception {
        ResultActions projectCreate = createNewProject(new Project("IBM", Stages.INPROGRESS, "The IBM Project"));
        projectCreate.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.projectId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("IBM"));

        //editing the project with ID 1
        mockMvc.perform(MockMvcRequestBuilders.patch("/app-api/projects/{id}", 1)
                        .content(asJsonString(new Project("IBM_DELL", Stages.COMPLETED, "The IBM Project after edit")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.projectId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("IBM_DELL"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("The IBM Project after edit"));
    }

    @Test
    public void it_allows_to_delete_existing_projects () throws Exception {
        ResultActions projectCreate = createNewProject(new Project("Honda", Stages.INPROGRESS, "The Honda Project"));
        projectCreate.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.projectId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Honda"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/app-api/projects/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/app-api/projects/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void it_will_validate_mandatory_field_project_name() throws Exception {
        createNewProject(new Project("", Stages.INPROGRESS, "The Honda Project"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void it_will_not_allow_to_create_duplicate_projects() throws Exception {

        //project 1
        createNewProject(new Project("Tesla", Stages.INPROGRESS, "The Tesla Project 1"))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.get("/app-api/projects"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(1)));

        // project 2
        createNewProject(new Project("Tesla", Stages.INPROGRESS, "The Tesla Project 1"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void it_will_not_allow_to_duplicate_projects_when_editing() throws Exception {

        //project 1
        createNewProject(new Project("Kenwood", Stages.INPROGRESS, "The Kenwood Project"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.projectId").value(1));

        //project 2
        createNewProject(new Project("Sony", Stages.INPROGRESS, "The Sony Project"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.projectId").value(2));

        //Editing project 1 using an existing project name
        mockMvc.perform(MockMvcRequestBuilders.patch("/app-api/projects/{id}", 1)
                        .content(asJsonString(new Project("Sony", Stages.COMPLETED, "The Kenwood Project")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResultActions createNewProject (Project project) throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/app-api/projects")
                        .content(asJsonString(project))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        return result;
    }
}
