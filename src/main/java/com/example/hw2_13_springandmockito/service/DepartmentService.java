package com.example.hw2_13_springandmockito.service;

import com.example.hw2_13_springandmockito.model.Employee;

import java.util.List;

public interface DepartmentService {

    Employee searchEmployeeMinimumSalaryDepartment(int department);

    Employee searchEmployeeMaximumSalaryByDepartment(int department);

    List<Employee> printEmployeesFromDepartment(int department);

    List<Employee> printEmployeesByDepartment();

}