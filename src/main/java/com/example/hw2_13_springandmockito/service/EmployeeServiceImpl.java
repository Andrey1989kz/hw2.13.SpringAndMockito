package com.example.hw2_13_springandmockito.service;

import com.example.hw2_13_springandmockito.exception.EmployeeNotFoundException;
import com.example.hw2_13_springandmockito.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employees = new ArrayList<>();



    @Override
    public Employee add(String firstName, String lastName, int salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employees.contains(employee)) {
            try {
                throw new EmployeeNotFoundException();
            } catch (EmployeeNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = find(firstName, lastName);
        employees.remove(employee);
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        final Optional<Employee> employee = employees.stream()
                .filter(e -> e.getFirstName().equals(firstName) && e.getLastName().equals(lastName))
                .findAny();
        return employee.orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Employee> getEmployees() {
        return  new ArrayList<>(employees);
    }
}