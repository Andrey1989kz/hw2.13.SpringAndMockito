package com.example.hw2_13_springandmockito.service;

import com.example.hw2_13_springandmockito.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee searchEmployeeMinimumSalaryDepartment(int department) {
        return employeeService.getEmployee().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Employee searchEmployeeMaximumSalaryByDepartment(int department) {
        return employeeService.getEmployee().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Employee> printEmployeesFromDepartment(int department) {
        return employeeService.getEmployee().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> printEmployeesByDepartment() {
        return employeeService.getEmployee().stream()
                .sorted(Comparator.comparingInt(Employee::getDepartment)).collect(Collectors.toUnmodifiableList());
    }


}