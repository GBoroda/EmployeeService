package com.misha.controllerT;

import com.misha.model.Employee;
import com.misha.service.AppService;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:resources/test-context.xml")
public class MainControllerTest {

    @Autowired
    private AppService service;


    @Test
    public void findById() throws Exception {
        Employee employee = service.getEmployeeById(1);
        Assert.assertEquals(true, "Misha".equals(employee.getFirstName()));
    }

}
