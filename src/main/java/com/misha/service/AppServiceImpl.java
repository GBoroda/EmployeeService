package com.misha.service;

import com.misha.dao.AppDao;
import com.misha.model.Department;
import com.misha.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppServiceImpl implements AppService {

    @Autowired
    private AppDao dao;

    @Override
    public List<Employee> getAllEmployees() {
        return this.dao.getAllEmployees();
    }

    @Override
    public List<Department> getAllDepartments() {
        return this.dao.getAllDepartments();
    }

    @Override
    public Employee getEmployeeById(int id) {
        return this.dao.getEmployeeById(id);
    }

    @Override
    public Department getDepartmentById(int id) {
        return this.dao.getDepartmentById(id);
    }

    @Override
    public List<Employee> getEmployeesInDepartmentId(int id) {
        return this.dao.getEmployeesInDepartmentId(id);
    }

    @Override
    public void calculateAvgSalaryInDepartment() {
        this.dao.calculateAvgSalaryInDepartment();
    }

    @Override
    public void updateDepartmentAvgSalary(int id, int avgSalary) {
        this.dao.updateDepartmentAvgSalary(id, avgSalary);
    }

    @Override
    public void addEmployee(int departmentId, String firstName, String lastName, int salary) {
        this.dao.addEmployee(departmentId, firstName, lastName, salary);
    }

    @Override
    public void addEmployee(Employee employee) {
        this.dao.addEmployee(employee);
    }

    @Override
    public void addDepartment(String name) {
        this.dao.addDepartment(name);
    }

    @Override
    public void addDepartment(Department department) {
        this.dao.addDepartment(department);
    }

    @Override
    public void removeEmployee(int id) {
        this.dao.removeEmployee(id);
    }

    @Override
    public void removeDepartment(int id) {
        this.dao.removeDepartment(id);
    }

    @Override
    public void ediitDepartment(int id, String name) {
        this.dao.ediitDepartment(id, name);
    }
}
