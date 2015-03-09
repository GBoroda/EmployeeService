package com.misha.controller;

import com.misha.model .Department;
import com.misha.model.Employee;
import com.misha.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class MainController  {

    @Autowired
    private AppService service;
    
    @RequestMapping(value = "/")
    public ModelAndView getMainPage() {
        return new ModelAndView("redirect:/main");
    }

    @RequestMapping(value = "/main")
    public ModelAndView getPage() {
        service.calculateAvgSalaryInDepartments();
        List<Employee> employeeList = service.getAllEmployees();
        List<Department> departmentList = service.getAllDepartments();
        ModelAndView modelAndView = new ModelAndView("Main");
        modelAndView.addObject("emps", employeeList);
        modelAndView.addObject("deps", departmentList);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/employee/", method = RequestMethod.GET)
    public ModelAndView editEmployeePage() {
        service.calculateAvgSalaryInDepartments();
        List<Employee> employeeList = service.getAllEmployees();
        List<Department> departmentList = service.getAllDepartments();
        ModelAndView modelAndView = new ModelAndView("Edit");
        modelAndView.addObject("emps", employeeList);
        modelAndView.addObject("deps", departmentList);
        return modelAndView;
    }

    @RequestMapping(value = "/department/add", method = RequestMethod.POST)
    public ModelAndView addDepartment(@ModelAttribute("com/misha/model/Department.java") Department department, Model model) {
        model.addAttribute("name", department.getName());
        service.addDepartment(department);
        return new ModelAndView("redirect:/main");
    }

    @RequestMapping(value = "/employee/add", method = RequestMethod.POST)
    public ModelAndView addEmployee(@ModelAttribute("com/misha/model/Employee.java") Employee employee , Model model) {
        model.addAttribute("departmentId", employee.getDepartmentId());
        model.addAttribute("firstName", employee.getFirstName());
        model.addAttribute("lastName", employee.getLastName());
        model.addAttribute("salary", employee.getSalary());
        service.addEmployee(employee);
        return new ModelAndView("redirect:/main");
    }

    @RequestMapping(value = "/remove/employee/{id}", method = RequestMethod.DELETE)
    public ModelAndView removeEmployeeRedirect(@PathVariable("id") int id) {
        service.removeEmployee(id);
        return new ModelAndView("redirect:/main");
    }

    @RequestMapping(value = "/remove/department/{id}", method = RequestMethod.DELETE)
    public ModelAndView removeDepartmentRedirect(@PathVariable("id") int id) {
        service.removeDepartment(id);
        return new ModelAndView("redirect:/main");
    }

    // RESTful web service stuff
    @RequestMapping(value = "/departments", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Department> getDepartment() {
        return service.getAllDepartments();
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Employee> getEmployees() {
        return service.getAllEmployees();
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Employee getEmployeeById(@PathVariable int id) {
        return service.getEmployeeById(id);
    }

    @RequestMapping(value = "/department/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Department getDepartmentById(@PathVariable int id) {
        return service.getDepartmentById(id);
    }

    @RequestMapping(value = "/employeesInDepartment/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Employee> getEmployeesByDepartment(@PathVariable int id) {
        return service.getEmployeesInDepartmentId(id);
    }

    @RequestMapping(value = "/add/department/{name}", method = RequestMethod.POST, headers = "Accept=application/json")
    public void addDepartment(@PathVariable String name) {
        service.addDepartment(name);
    }

    @RequestMapping(value = "/add/employee/{departmentId}/{firstName}/{lastName}/{salary}", method = RequestMethod.POST, headers = "Accept=application/json")
    public void addEmployee(@PathVariable int departmentId,@PathVariable String firstName,@PathVariable String lastName,@PathVariable int salary) {
        service.addEmployee(departmentId, firstName, lastName, salary);
        service.calculateAvgSalaryInDepartments();
    }

    @RequestMapping(value = "/remove/employee/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void removeEmployee(@PathVariable int id) {
        service.removeEmployee(id);
        service.calculateAvgSalaryInDepartments();
    }

    @RequestMapping(value = "/remove/department/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void removeDepartment(@PathVariable int id) {
        service.removeDepartment(id);
    }

    @RequestMapping(value = "/edit/department/{id}/{name}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public void editDepartment(@PathVariable int id, @PathVariable String name) {
        service.ediitDepartment(id, name);
    }

}
