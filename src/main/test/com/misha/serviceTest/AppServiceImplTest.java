package com.misha.serviceTest;

import com.misha.model.Department;
import com.misha.model.Employee;
import com.misha.service.AppService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:app-servlet.xml"})
public class AppServiceImplTest {

    @Autowired
    private AppService appService;

    @Test
    @Transactional
    public void testGetAllEmployee() {
        List<Employee> list = this.appService.getAllEmployees();
        Assert.assertNotNull(list);
    }

    @Test
    @Transactional
    public void testGetAllDepartments() {
        List<Department> list = this.appService.getAllDepartments();
        Assert.assertNotNull(list);
    }

    @Test
    @Transactional
    public void testGetEmployeeById() {
        Employee employee = this.appService.getEmployeeById(1);
        Assert.assertEquals("Mihail", employee.getFirstName());
        Assert.assertEquals("Spiridonov", employee.getLastName());
        Assert.assertEquals(1, employee.getDepartmentId());
        Assert.assertEquals(900, employee.getSalary());
        Assert.assertEquals(1, employee.getId());
    }

    @Test
    @Transactional
    public void testGetDepartmentById() {
        Department department = this.appService.getDepartmentById(1);
        Assert.assertEquals("Programmers", department.getName());
    }

    @Test
    @Transactional
    public void testGetEmployeesInDepartmentId() {
        List<Employee> list = this.appService.getEmployeesInDepartmentId(1);
        Assert.assertNotNull(list);
    }

    @Test
    @Transactional
    public void testCalculateAvgSalaryInDepartments() {
        List<Department> departments = appService.getAllDepartments();
        Department department = departments.get(0);
        Number AvgSalaryQuery = appService.calculatedAvgSalary(department.getId());
        if (AvgSalaryQuery != null) {
            department.setAvgSalary(AvgSalaryQuery.intValue());
        }
        else {
            department.setAvgSalary(0);
        }
        // проверяем на тестовых данных в отделе "Programmers"
        Assert.assertEquals(937, department.getAvgSalary());
    }

    @Test
    @Transactional
    public void testAddEmployee() {
        List<Employee> beforeTest = this.appService.getAllEmployees();
        this.appService.addEmployee(1, "Test", "Testovich", 999);
        Assert.assertNotEquals(beforeTest.size(), this.appService.getAllEmployees().size());
    }

    @Test
    @Transactional
    public void testAddEmployee2() {
        List<Employee> beforeTest = this.appService.getAllEmployees();
        Employee employee = new Employee(1, "Test", "Testovich", 999);
        this.appService.addEmployee(employee);
        Assert.assertNotEquals(beforeTest.size(), this.appService.getAllEmployees().size());
    }

    @Test
    @Transactional
    public void testAddDepartment() {
        List<Department> beforeAdding = this.appService.getAllDepartments();
        this.appService.addDepartment("NewDepartment");
        Assert.assertNotEquals(beforeAdding.size(), this.appService.getAllDepartments().size());
    }

    @Test
    @Transactional
    public void testAddDepartment2() {
        List<Department> beforeAdding = this.appService.getAllDepartments();
        Department department = new Department("NewDepartment");
        this.appService.addDepartment(department);
        Assert.assertNotEquals(beforeAdding.size(), this.appService.getAllDepartments().size());
    }

    @Test
    @Transactional
    public void testRemoveEmployee() {
        List<Employee> beforeRemoving = this.appService.getAllEmployees();
        this.appService.removeEmployee(1);
        Assert.assertNotEquals(beforeRemoving.size(), this.appService.getAllEmployees().size());
    }

    @Test
    @Transactional
    public void testRemoveDepartment() {
        List<Department> beforeRemoving = this.appService.getAllDepartments();
        this.appService.removeDepartment(1);
        Assert.assertNotEquals(beforeRemoving.size(), this.appService.getAllDepartments().size());
    }

    @Test
    @Transactional
    public void tetEdiitDepartment() {
        Assert.assertEquals("Programmers", this.appService.getDepartmentById(1).getName());
        this.appService.ediitDepartment(1, "NotProgrammersAnyMore");
        Assert.assertEquals("NotProgrammersAnyMore", this.appService.getDepartmentById(1).getName());
    }
}
