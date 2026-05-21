package com.sarvatra.service;

import com.sarvatra.dto.EmployeeDto;
import com.sarvatra.entities.Employee;
import com.sarvatra.respositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Cacheable(cacheNames = "employeeCache", key = "#id")
    public EmployeeDto getEmployeeById(Long id) {
        log.info("Fetching Employee Details with id :- {}", id);
        final Employee employeeDetails = employeeRepository.findById(id).orElse(null);

        return EmployeeDto.builder()
                .id(employeeDetails.getId())
                .name(employeeDetails.getName())
                .email(employeeDetails.getEmail())
                .salary(employeeDetails.getSalary())
                .build();
    }

    // result = EmployeeDto
    @CachePut(cacheNames = "employeeCache", key = "#result.getId()")
    public EmployeeDto addEmployee(EmployeeDto employee) {
        log.info("Adding Employee with email :- {}", employee.getEmail());

        Employee employee1 = Employee.builder()
                .name(employee.getName())
                .email(employee.getEmail())
                .salary(employee.getSalary())
                .build();

        final Employee addedEmployee = employeeRepository.save(employee1);

        return EmployeeDto.builder()
                .id(addedEmployee.getId())
                .name(addedEmployee.getName())
                .email(addedEmployee.getEmail())
                .salary(addedEmployee.getSalary())
                .build();
    }

    @CachePut(cacheNames = "employeeCache", key = "#id")
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        log.info("Updating Employee Details with ud :- {}", id);
        Employee employee1 = employeeRepository.findById(id).orElse(null);

        employee1.setEmail(employeeDto.getEmail());
        employee1.setName(employeeDto.getName());
        employee1.setSalary(employeeDto.getSalary());

        employeeRepository.save(employee1);

        return employeeDto;
    }

    @CacheEvict(cacheNames = "employeeCache", key = "#id")
    public ResponseEntity<Boolean> deleteEmployee(Long id) {
        log.info("Deleted Employee Details with id :- {}", id);
        employeeRepository.deleteById(id);
        return new ResponseEntity<>(true, HttpStatusCode.valueOf(200));
    }

}
