package com.misha.service;

import com.misha.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AppServiceImplTest {

    @Mock
    private AppService service;

    @InjectMocks
    private AppService appService = new AppServiceImpl();

    @Test
    public void test() {
        List<Employee> list = appService.getAllEmployees();
        Employee employee = list.get(0);
        String name = employee.getFirstName();
        assertEquals("check name", "Mihail", name);
    }




}
