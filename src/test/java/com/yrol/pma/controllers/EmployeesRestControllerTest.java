package com.yrol.pma.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yrol.pma.entities.Employee;
import com.yrol.pma.services.EmployeeService;
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
public class EmployeesRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    EmployeeService empService;

    @Test
    public void it_allows_to_fetch_all_employees () throws Exception {

        ResultActions beforeInsertResponse = mockMvc.perform(MockMvcRequestBuilders.get("/app-api/employees"));

        beforeInsertResponse.andExpect(MockMvcResultMatchers.status().isOk());
        beforeInsertResponse.andExpect(MockMvcResultMatchers.jsonPath("$.length()", CoreMatchers.is(0)));

        Employee newEmployee = new Employee("James", "Caan", "j.caan@gmail.com");
        empService.save(newEmployee);

        ResultActions afterInsertResponse = mockMvc.perform(MockMvcRequestBuilders.get("/app-api/employees"));

        afterInsertResponse.andExpect(MockMvcResultMatchers.status().isOk());
        afterInsertResponse.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(1)));
    }

    @Test
    public void it_allows_to_get_employee_by_id () throws Exception {

        ResultActions beforeInsertResponse = mockMvc.perform(MockMvcRequestBuilders.get("/app-api/employees/{id}", 1)
                .accept(MediaType.APPLICATION_JSON));
        beforeInsertResponse.andExpect(MockMvcResultMatchers.status().isNotFound());

        Employee newEmployee = new Employee("John", "Cena", "j.cena@yahoo.com");
        empService.save(newEmployee);

        ResultActions afterInsertResponse = mockMvc.perform(MockMvcRequestBuilders.get("/app-api/employees/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
        afterInsertResponse.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Cena"));
    }

    @Test
    public void it_allows_to_create_new_employees () throws Exception {
        ResultActions ibmProject = createNewEmployee(new Employee("Anne", "Gosling", "a.gosling@gmx.com"));
        ibmProject.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Anne"));
    }


    @Test
    public void it_allows_to_update_existing_employees () throws Exception {
        ResultActions projectCreate = createNewEmployee(new Employee("Ryan", "Reynolds", "r.reynolds@msn.com"));
        projectCreate.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Reynolds"));

        //editing the project with ID 1
        mockMvc.perform(MockMvcRequestBuilders.patch("/app-api/employees/{id}", 1)
                        .content(asJsonString(new Employee("Ryan", "Silva", "r.silva@msn.com")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Silva"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("r.silva@msn.com"));
    }

    @Test
    public void it_allows_to_delete_existing_employees () throws Exception {
        ResultActions projectCreate = createNewEmployee(new Employee("Desmond", "Pereira", "d.pereira@aol.com"));
        projectCreate.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Desmond"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/app-api/employees/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders.get("/app-api/employees/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void it_will_validate_mandatory_field_employee_firstName() throws Exception {
        ResultActions projectCreate = createNewEmployee(new Employee("", "Robins", "s.robins@yahoo.com"));
        projectCreate.andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void it_will_validate_mandatory_field_employee_lastName() throws Exception {
        ResultActions projectCreate = createNewEmployee(new Employee("Simon", "", "s.robins@yahoo.com"));
        projectCreate.andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void it_will_validate_mandatory_field_employee_email() throws Exception {
        ResultActions projectCreate = createNewEmployee(new Employee("Simon", "Robins", "This is not an email"));
        projectCreate.andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void it_will_not_allow_to_create_multiple_employees_with_same_email() throws Exception {

        //Employee 1
        createNewEmployee(new Employee("Dave", "McDonald", "d.mcdonald@msn.com"))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.get("/app-api/employees"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(1)));

        //Employee 2
        createNewEmployee(new Employee("Aaron", "Finch", "d.mcdonald@msn.com"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void it_will_not_allow_to_duplicate_employee_when_editing_using_same_email() throws Exception {

        //Employee 1
        createNewEmployee(new Employee("Kelly", "Johnson", "k.johnson@skunk.com"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").value(1));

        //Employee 2
        createNewEmployee(new Employee("Ian", "Thomas", "i.thomas@gmail.com"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").value(2));

        mockMvc.perform(MockMvcRequestBuilders.patch("/app-api/employees/{id}", 1)
                        .content(asJsonString(new Employee("Kelly", "Johnson", "i.thomas@gmail.com")))
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

    public ResultActions createNewEmployee (Employee employee) throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/app-api/employees")
                .content(asJsonString(employee))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        return result;
    }

}
