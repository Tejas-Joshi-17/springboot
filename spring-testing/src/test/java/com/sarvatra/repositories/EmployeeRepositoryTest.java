package com.sarvatra.repositories;

import com.sarvatra.entities.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .name("Anuj")
                .email("anuj@gmail.com")
                .salary(100L)
                .build();
    }

    @Test
    void testFindByEmail_whenEmailIsPresent_thenReturnEmployee() {
        employeeRepository.save(employee);

        List<Employee> employeeList = employeeRepository.findByEmail(employee.getEmail());

        Assertions.assertThat(employeeList).isNotNull();
        Assertions.assertThat(employeeList).isNotEmpty();
        Assertions.assertThat(employeeList.get(0).getEmail()).isEqualTo(employee.getEmail());
    }

    @Test
    void testFindByEmail_whenEmailIsNotFound_thenReturnEmptyEmployeeList() {
        String email = "notPresent.123@gmail.com";

        List<Employee> employeeList = employeeRepository.findByEmail(email);

        Assertions.assertThat(employeeList).isNotNull();
        Assertions.assertThat(employeeList).isEmpty();
    }

}