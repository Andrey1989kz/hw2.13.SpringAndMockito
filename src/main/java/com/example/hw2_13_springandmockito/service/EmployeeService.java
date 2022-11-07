package com.example.hw2_13_springandmockito.service;

import com.example.hw2_13_springandmockito.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee add(String firstName, String lastName, int salary, int department);

    Employee remove(String firstName, String lastName);

    Employee find(String firstName, String lastName);

    List<Employee> getEmployees();

}