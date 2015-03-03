package com.misha.daoTest;

import com.misha.dao.AppDao;
import com.misha.model.Department;
import com.misha.model.Employee;
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
public class AppDaoImplTest  {

    @Autowired
    private AppDao dao;

    @Test
    @Transactional
    public void testGetAllEmployees() {
        List<Employee> list = dao.getAllEmployees();
        Assert.assertNotNull(list);
    }

    @Test
    @Transactional
    public void testAddEmployee2() {
        List<Employee> beforeTest = dao.getAllEmployees();
        Employee employee = new Employee(1, "Test", "Testovich", 999);
        dao.addEmployee(employee);
        Assert.assertNotEquals(beforeTest.size(), dao.getAllEmployees().size());
    }

    @Test
    @Transactional
    public void testGetAllDepartments() {
        List<Department> list = dao.getAllDepartments();
        Assert.assertNotNull(list);
    }

    @Test
    @Transactional
    public void testGetEmployeeById() {
        Employee employee = dao.getEmployeeById(1);
        Assert.assertEquals("Mihail", employee.getFirstName());
        Assert.assertEquals("Spiridonov", employee.getLastName());
        Assert.assertEquals(1, employee.getDepartmentId());
        Assert.assertEquals(900, employee.getSalary());
        Assert.assertEquals(1, employee.getId());
    }

    @Test
    @Transactional
    public void testGetDepartmentById() {
        Department department = dao.getDepartmentById(1);
        Assert.assertEquals("Programmers", department.getName());
    }

    @Test
    @Transactional
    public void testGetEmployeesInDepartmentId() {
        List<Employee> list = dao.getEmployeesInDepartmentId(1);
        Assert.assertNotNull(list);
    }

    @Test
    @Transactional
    public void testCalculateAvgSalaryInDepartment() {
        List<Department> departments = dao.getAllDepartments();
        Department department = departments.get(0);
        Number AvgSalaryQuery = dao.calculatedAvgSalary(department.getId());
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
    public void testUpdateDepartmentAvgSalary() {
        Department department = dao.getDepartmentById(1);
        Assert.assertNotEquals(777, department.getAvgSalary());
        dao.updateDepartmentAvgSalary(1, 777);
        Assert.assertEquals(777, department.getAvgSalary());
    }

    @Test
    @Transactional
    public void testAddEmployee() {
        List<Employee> beforeTest = dao.getAllEmployees();
        dao.addEmployee(1, "Test", "Testovich", 999);
        Assert.assertNotEquals(beforeTest.size(), dao.getAllEmployees().size());
    }

    @Test
    @Transactional
    public void testEditDepartment() {
        Assert.assertEquals("Programmers", dao.getDepartmentById(1).getName());
        dao.ediitDepartment(1, "NotProgrammersAnyMore");
        Assert.assertEquals("NotProgrammersAnyMore", dao.getDepartmentById(1).getName());
    }

    @Test
    @Transactional
    public void testAddDepartment() {
        List<Department> beforeAdding = dao.getAllDepartments();
        dao.addDepartment("NewDepartment");
        Assert.assertNotEquals(beforeAdding.size(), dao.getAllDepartments().size());
    }

    @Test
    @Transactional
    public void testAddDepartment2() {
        List<Department> beforeAdding = dao.getAllDepartments();
        Department department = new Department("NewDepartment");
        dao.addDepartment(department);
        Assert.assertNotEquals(beforeAdding.size(), dao.getAllDepartments().size());
    }

    @Test
    @Transactional
    public void testRemoveEmployee() {
        List<Employee> beforeRemoving = dao.getAllEmployees();
        dao.removeEmployee(1);
        Assert.assertNotEquals(beforeRemoving.size(), dao.getAllEmployees().size());
    }

    @Test
    @Transactional
    public void testRemoveDepartment() {
        List<Department> beforeRemoving = dao.getAllDepartments();
        dao.removeDepartment(1);
        Assert.assertNotEquals(beforeRemoving.size(), dao.getAllDepartments().size());
    }

}
