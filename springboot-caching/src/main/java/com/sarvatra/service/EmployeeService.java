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
    public ResponseEntity<EmployeeDto> getEmployeeById(Long id) {
        log.info("Fetching Employee Details with id :- {}", id);
        final Employee employeeDetails = employeeRepository.findById(id).orElse(null);

        EmployeeDto employee = EmployeeDto.builder()
                .id(id)
                .name(employeeDetails.getName())
                .email(employeeDetails.getEmail())
                .salary(employeeDetails.getSalary())
                .build();
        return new ResponseEntity<>(employee, HttpStatusCode.valueOf(200));
    }

    @CachePut(cacheNames = "employeeCache", key = "#result.body.getId()")
    public ResponseEntity<EmployeeDto> addEmployee(EmployeeDto employee) {
        log.info("Adding Employee with email :- {}", employee.getEmail());
        Employee employee1 = Employee.builder()
                .name(employee.getName())
                .email(employee.getEmail())
                .salary(employee.getSalary())
                .build();

        final Employee addedEmployee = employeeRepository.save(employee1);
        EmployeeDto newEmployee = EmployeeDto.builder()
                .id(addedEmployee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .salary(employee.getSalary())
                .build();

        return new ResponseEntity<>(newEmployee, HttpStatusCode.valueOf(200));
    }

    @CachePut(cacheNames = "employeeCache", key = "#id")
    public ResponseEntity<EmployeeDto> updateEmployee(Long id, EmployeeDto employeeDto) {
        log.info("Updating Employee Details with ud :- {}", id);
        Employee employee1 = Employee.builder()
                .name(employeeDto.getName())
                .email(employeeDto.getEmail())
                .salary(employeeDto.getSalary())
                .build();

        employeeRepository.save(employee1);
        return new ResponseEntity<>(employeeDto, HttpStatusCode.valueOf(200));
    }

    @CacheEvict(cacheNames = "employeeCache", key = "#id")
    public ResponseEntity<Boolean> deleteEmployee(Long id) {
        log.info("Deleted Employee Details with id :- {}", id);
        employeeRepository.deleteById(id);
        return new ResponseEntity<>(true, HttpStatusCode.valueOf(200));
    }

}
