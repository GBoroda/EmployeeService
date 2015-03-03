package com.misha.controllerTest;


import com.misha.controller.MainController;
import com.misha.service.AppService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:app-servlet.xml"})
public class MainControllerTest {

    @Mock
    private AppService service;

    @InjectMocks MainController controller = new MainController();

    private MockMvc mockMvc;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(controller).build();
    }

    @Test
    public void testMainPage() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    public void testEditPage() throws Exception {
        mockMvc.perform(get("/edit/employee/")).andExpect(status().isOk());
    }

    @Test
    public void testAddDepartmentModelAndView() throws Exception {
        mockMvc.perform(post("/department/add/")).andExpect(status().is3xxRedirection());
    }

    @Test
    public void testAddEmployeeModelAndView() throws Exception {
        mockMvc.perform(post("/employee/add/")).andExpect(status().is3xxRedirection());
    }

    @Test
    public void testRemoveEmployeeModelAndView() throws Exception {
        mockMvc.perform(delete("/remove/employee/{id}/", 1)).andExpect(status().is3xxRedirection());
    }

    @Test
    public void testRemoveDepartmentModelAndView() throws Exception {
        mockMvc.perform(delete("/remove/department/{id}/", 1)).andExpect(status().is3xxRedirection());
    }

    @Test
    public void testEmployees() throws Exception {
        mockMvc.perform(get("/employees/")).andExpect(status().isOk());
    }

    @Test
    public void testDepartments() throws Exception {
        mockMvc.perform(get("/departments/")).andExpect(status().isOk());
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        mockMvc.perform(get("/employee/{id}/", 1)).andExpect(status().isOk());
    }

    @Test
    public void testGetDepartmentByID() throws Exception {
        mockMvc.perform(get("/department/{id}/", 1)).andExpect(status().isOk());
    }

    @Test
    public void testGetEmployeesInDepartment() throws Exception {
        mockMvc.perform(get("/employeesInDepartment/{id}/", 1)).andExpect(status().isOk());
    }

    @Test
    public void testAddDepartment() throws Exception {
        mockMvc.perform(post("/add/department/{name}/", "name")).andExpect(status().isOk());
    }

    @Test
    public void testAddEmployee() throws Exception {
        mockMvc.perform(post("/add/employee/{departmentId}/{firstName}/{lastName}/{salary}/", 1, "test", "tester", 200)).andExpect(status().isOk());
    }

    @Test
    public void testRemoveEmployee() throws Exception {
        mockMvc.perform(delete("/remove/employee/{id}/", 1)).andExpect(status().is3xxRedirection());
    }

    @Test
    public void testRemoveDepartment() throws Exception {
        mockMvc.perform(delete("/remove/department/{id}/", 1)).andExpect(status().is3xxRedirection());
    }

    @Test
    public void testEditDepartment() throws Exception {
        mockMvc.perform(put("/edit/department/{id}/{name}/", 1, "test")).andExpect(status().isOk());
    }

}
