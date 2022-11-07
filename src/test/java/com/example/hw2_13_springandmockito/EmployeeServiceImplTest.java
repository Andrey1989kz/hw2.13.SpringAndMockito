package com.example.hw2_13_springandmockito;

import com.example.hw2_13_springandmockito.exception.InvalidInputException;
import com.example.hw2_13_springandmockito.model.Employee;
import com.example.hw2_13_springandmockito.service.EmployeeService;
import com.example.hw2_13_springandmockito.service.EmployeeServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class EmployeeServiceImplTest {
        private final EmployeeService out = new EmployeeServiceImpl();

        @AfterEach
        public void AfterEach() {
            out.getEmployees().forEach(employee -> out.remove(employee.getFirstName(), employee.getLastName()));
        }

        @ParameterizedTest
        @MethodSource("params")
        public void addNegativeTest1(String firstName, String lastName, int salary, int department) {
            Employee expected = new Employee(firstName, lastName, salary, department);

            assertThat(out.getEmployees().isEmpty());
            out.add(firstName, lastName, salary, department);
            assertThat(out.getEmployees())
                    .hasSize(1)
                    .containsExactly(expected);
            assertThat(out.find(expected.getFirstName(), expected.getLastName()))
                    .isNotNull()
                    .isEqualTo(expected);

            assertThatExceptionOfType(RuntimeException.class)
                    .isThrownBy(() -> out.add(firstName, lastName, salary, department));
        }

        @ParameterizedTest
        @MethodSource("params")
        public void removeNegativeTest(String firstName, String lastName, int salary, int department) {
            assertThat(out.getEmployees().isEmpty());
            assertThatExceptionOfType(RuntimeException.class)
                    .isThrownBy(() -> out.remove("test", "test"));

            Employee expected = new Employee(firstName, lastName, salary, department);
            out.add(firstName, lastName, salary, department);
            assertThat(out.getEmployees())
                    .hasSize(1)
                    .containsExactly(expected);

            assertThatExceptionOfType(RuntimeException.class)
                    .isThrownBy(() -> out.remove("test", "test"));
        }

        @ParameterizedTest
        @MethodSource("params")
        public void removePositiveTest(String firstName, String lastName, int salary, int department) {
            assertThat(out.getEmployees().isEmpty());
            Employee expected = new Employee(firstName, lastName, salary, department);
            out.add(firstName, lastName, salary, department);

            assertThat(out.getEmployees())
                    .hasSize(1)
                    .containsExactly(expected);

            assertThat(out.remove(firstName, lastName)).isEqualTo(expected);
            assertThat(out.getEmployees().isEmpty());
        }

        @ParameterizedTest
        @MethodSource("params")
        public void findNegativeTest(String firstName, String lastName, int salary, int department) {
            assertThat(out.getEmployees().isEmpty());
            assertThatExceptionOfType(RuntimeException.class)
                    .isThrownBy(() -> out.find("test", "test"));

            Employee expected = new Employee(firstName, lastName, salary, department);
            out.add(firstName, lastName, salary, department);
            assertThat(out.getEmployees())
                    .hasSize(1)
                    .containsExactly(expected);

            assertThatExceptionOfType(RuntimeException.class)
                    .isThrownBy(() -> out.find("test", "test"));
        }

        @ParameterizedTest
        @MethodSource("params")
        public void findPositiveTest(String firstName, String lastName, int salary, int department) {
            assertThat(out.getEmployees().isEmpty());
            Employee expected = new Employee(firstName, lastName, salary, department);
            out.add(firstName, lastName, salary, department);

            assertThat(out.getEmployees())
                    .hasSize(1)
                    .containsExactly(expected);

            assertThat(out.find(firstName, lastName)).isEqualTo(expected);
        }

    @Test
    public void addNegativeTest3() {
        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> out.add("!Nikolay", "Gogol", 60_000, 1));

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> out.add("Lev", "Tolstoy&", 55_000, 2));

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> out.add("Aleksandr", null, 30_000, 2));
    }

        public static Stream<Arguments> params() {
            return Stream.of(
                    Arguments.of("Nikolay", "Gogol",60_000, 1),
                    Arguments.of("Lev", "Tolstoy", 55_000, 2),
                    Arguments.of("Aleksandr", "Pushkin", 2, 75_000)
            );
        }

}
