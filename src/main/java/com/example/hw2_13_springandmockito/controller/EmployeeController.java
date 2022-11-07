package com.example.hw2_13_springandmockito.controller;

import com.example.hw2_13_springandmockito.model.Employee;
import com.example.hw2_13_springandmockito.service.DepartmentService;
import com.example.hw2_13_springandmockito.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }


    @GetMapping(path = "/add")
    public Object add(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "salary") int salary,
            @RequestParam(value = "department") int department) {
        Employee employee = null;
        try {
            employee = employeeService.add(firstName, lastName, salary, department);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employee;
    }

    @GetMapping(path = "/remove")
    public Object remove(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName) {
        Employee employee = null;
        try {
            employee = employeeService.remove(firstName, lastName);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employee;
    }

    @GetMapping(path = "/find")
    public Object findEmployee(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName) {
        Employee employee = null;
        try {
            employee = employeeService.find(firstName, lastName);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employee;
    }

    @GetMapping(path = "/departments/max-salary")
    public Object maxSalary(
            @RequestParam(value = "departmentId") int departmentId) {
        Employee employee = null;
        try {
            employee = departmentService.searchEmployeeMaximumSalaryByDepartment(departmentId);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employee;
    }

    @GetMapping(path = "/departments/min-salary")
    public Object minSalary(
            @RequestParam(value = "departmentId") int departmentId) {
        Employee employee = null;
        try {
            employee = departmentService.searchEmployeeMinimumSalaryDepartment(departmentId);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employee;
    }

    @GetMapping(path = "/departments/all", params = "dep-Id")
    public Object department(
            @RequestParam(value = "departmentId") int departmentId) {
        List<Employee> employees = null;
        try {
            employees = departmentService.printEmployeesFromDepartment(departmentId);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employees;
    }

    @GetMapping(path = "/print")
    public Object printAll() {
        List<Employee> employees = null;
        try {
            employees = departmentService.printEmployeesByDepartment();
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employees;
    }


}