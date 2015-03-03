package com.misha.dao;

import com.misha.model.Department;
import com.misha.model.Employee;

import java.util.List;

public interface AppDao {

    List<Employee> getAllEmployees();
    List<Department> getAllDepartments();

    Employee getEmployeeById(int id);
    Department getDepartmentById(int id);

    List<Employee>getEmployeesInDepartmentId(int id);

    void calculateAndSetAvgSalaryInDepartments();
    Number calculatedAvgSalary(int id);
    void updateDepartmentAvgSalary(int id, int avgSalary);

    void addEmployee(int departmentId, String firstName, String lastName, int salary);
    void addEmployee(Employee employee);
    void addDepartment(String name);
    void addDepartment(Department department);

    void removeEmployee(int id);
    void removeDepartment(int id);

    void ediitDepartment(int id, String name);




}
