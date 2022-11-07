package com.example.hw2_13_springandmockito;

import com.example.hw2_13_springandmockito.model.Employee;
import com.example.hw2_13_springandmockito.service.DepartmentServiceImpl;
import com.example.hw2_13_springandmockito.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

public class DepartmentServiceTest {


    @ExtendWith(MockitoExtension.class)
    public class DepartmentServiceImplTest {

        @Mock
        private EmployeeServiceImpl employeeService;

        @InjectMocks
        private DepartmentServiceImpl departmentService;

        @BeforeEach
        public void beforeEach() {
            List<Employee> employeeList = List.of(
                    new Employee("Lev", "Tolstoy", 80_000, 5));
            new Employee("Nikolay", "Gogol", 10_000, 5);
            new Employee("Aleksandr", "Pushkin", 20_000, 1);
            new Employee("Mihail", "Lermontov", 100_000, 1);
            new Employee("Fedor", "Dostoevsky", 120_000, 2);
            new Employee("Aleksandr", "Griboedov", 30_000, 2);
            new Employee("Nikolay", "Nekrasov", 65000, 3);
            new Employee("Anton", "Chehov", 155000, 3);
            new Employee("Fedor", "Tutchev", 255000, 4);
            new Employee("Afanasy", "Fet", 355000, 4);

            when(employeeService.getEmployees()).thenReturn(employeeList);
        }

        @ParameterizedTest
        @MethodSource("employeeWithMaxSalaryParams")
        public void maxSalaryEmployeePositiveTest(int departmentId, Employee expected) {


            assertThat(departmentService.searchEmployeeMaximumSalaryByDepartment(departmentId)).isEqualTo(expected);
        }

        @Test
        public void maxSalaryEmployeeNegativeTest() {
            assertThatExceptionOfType(RuntimeException.class)
                    .isThrownBy(() -> departmentService.searchEmployeeMinimumSalaryDepartment(10));
        }

        @ParameterizedTest
        @MethodSource("employeeWithMinSalaryParams")
        public void minSalaryEmployeePositiveTest(int departmentId, Employee expected) {
            assertThat(departmentService.searchEmployeeMinimumSalaryDepartment(departmentId)).isEqualTo(expected);
        }

        @Test
        public void minSalaryEmployeeNegativeTest() {
            assertThatExceptionOfType(RuntimeException.class)
                    .isThrownBy(() -> departmentService.searchEmployeeMinimumSalaryDepartment(3));
        }

        @ParameterizedTest
        @MethodSource("employeesFromDepartmentParams")
        public void employeesForDepartmentPositiveTest(int departmentId, List<Employee> expected) {
            assertThat(departmentService.printEmployeesFromDepartment(departmentId)).isEqualTo(expected);
        }


        public Stream<Arguments> employeeWithMaxSalaryParams() {
            return Stream.of(
                    Arguments.of(1, new Employee("Lev", "Tolstoy", 80_000, 5)),
                    Arguments.of(2, new Employee("Afanasy", "Fet", 355000, 4))
            );
        }

        public Stream<Arguments> employeeWithMinSalaryParams() {
            return Stream.of(
                    Arguments.of(1, new Employee("Aleksandr", "Griboedov", 30_000, 2)),
                    Arguments.of(2, new Employee("Anton", "Chehov", 155000, 3))
            );
        }

        public Stream<Arguments> employeesFromDepartmentParams() {
            return Stream.of(
                    Arguments.of(1, List.of(
                            new Employee("Aleksandr", "Pushkin", 20_000, 1),
                            new Employee("Mihail", "Lermontov", 100_000, 1),
                            Arguments.of(2, List.of(
                                    new Employee("Afanasy", "Fet", 355000, 4),
                                    new Employee("Fedor", "Tutchev", 255000, 4),
                                    Arguments.of(3, Collections.emptyList()))))));


        }
    }
}

