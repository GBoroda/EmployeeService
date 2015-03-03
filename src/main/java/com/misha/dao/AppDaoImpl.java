package com.misha.dao;

import com.misha.model.Department;
import com.misha.model.Employee;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDaoImpl implements AppDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return sessionFactory.getCurrentSession().createQuery("FROM Employee").list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> getAllDepartments() {
        return sessionFactory.getCurrentSession().createQuery("FROM Department").list();
    }

    @Override
    @Transactional(readOnly = false)
    public Employee getEmployeeById(int id) {
        return (Employee) sessionFactory.getCurrentSession().get(Employee.class, id);
    }

    @Override
    @Transactional(readOnly = false)
    public Department getDepartmentById(int id) {
        return (Department) sessionFactory.getCurrentSession().get(Department.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getEmployeesInDepartmentId(int id) {
        return sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM EMPLOYEE WHERE EMPLOYEE.department_id =" + id).list();
    }


    @Override
    @Transactional(readOnly = false)
    public void calculateAndSetAvgSalaryInDepartments() {
        List<Department> departments = getAllDepartments();
        for (Department dep : departments) {
            Integer ids = dep.getId();
            Number x = calculatedAvgSalary(ids);
            if (x != null) {
                updateDepartmentAvgSalary(ids, x.intValue());
            }
            else {
                updateDepartmentAvgSalary(ids, 0);
                dep.setAvgSalary(0);
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Number calculatedAvgSalary(int id) {
        return ((Number) sessionFactory.getCurrentSession().createSQLQuery("SELECT AVG(salary) FROM EMPLOYEE WHERE department_id =" + id + "").uniqueResult());
    }

    @Override
    @Transactional(readOnly = false)
    public void updateDepartmentAvgSalary(int id, int avgSalary) {
        Department department = getDepartmentById(id);
        department.setAvgSalary(avgSalary);
        sessionFactory.getCurrentSession().update(department);
    }

    @Override
    @Transactional(readOnly = false)
    public void addEmployee(int departmentId, String firstName, String lastName, int salary) {
        Employee employee = new Employee(departmentId, firstName, lastName, salary);
        sessionFactory.getCurrentSession().saveOrUpdate(employee);
    }

    @Override
    @Transactional(readOnly = false)
    public void addEmployee(Employee employee) {
        sessionFactory.getCurrentSession().saveOrUpdate(employee);
    }

    @Override
    @Transactional(readOnly = false)
    public void ediitDepartment(int id, String name) {
        Department department = getDepartmentById(id);
        department.setName(name);
        sessionFactory.getCurrentSession().update(department);
    }

    @Override
    @Transactional(readOnly = false)
    public void addDepartment(String name) {
        Department department = new Department(name);
        sessionFactory.getCurrentSession().save(department);
    }

    @Override
    @Transactional(readOnly = false)
    public void addDepartment(Department department) {
        sessionFactory.getCurrentSession().save(department);
    }

    @Override
    @Transactional(readOnly = false)
    public void removeEmployee(int id) {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM Employee c WHERE c.id =" + id).executeUpdate();
    }


    // не знал что делать с сотрудниками которые работали в удаленном отделе. Поэтому уволил и их.
    @Override
    @Transactional(readOnly = false)
    public void removeDepartment(int id) {
        sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM EMPLOYEE WHERE department_id =" + id).executeUpdate();
        sessionFactory.getCurrentSession().createQuery("DELETE FROM Department c WHERE c.id = " + id).executeUpdate();
    }


}
