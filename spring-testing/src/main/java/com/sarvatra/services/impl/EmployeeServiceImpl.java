package com.sarvatra.services.impl;

import com.sarvatra.dto.EmployeeDto;
import com.sarvatra.entities.Employee;
import com.sarvatra.exceptions.ResourceNotFoundException;
import com.sarvatra.repositories.EmployeeRepository;
import com.sarvatra.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        log.info("Fetching employee with id: {}", id);
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Employee not found with id: {}", id);
                    return new ResourceNotFoundException("Employee not found with id: " + id);
                });
        log.info("Successfully fetched employee with id: {}", id);
        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .salary(employee.getSalary())
                .build();
    }

    @Override
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
        log.info("Creating new employee with email: {}", employeeDto.getEmail());
        List<Employee> existingEmployees = employeeRepository.findByEmail(employeeDto.getEmail());

        if (!existingEmployees.isEmpty()) {
            log.error("Employee already exists with email: {}", employeeDto.getEmail());
            throw new RuntimeException("Employee already exists with email: " + employeeDto.getEmail());
        }

        Employee newEmployee =  Employee.builder()
                .id(employeeDto.getId())
                .name(employeeDto.getName())
                .email(employeeDto.getEmail())
                .salary(employeeDto.getSalary())
                .build();

        Employee savedEmployee = employeeRepository.save(newEmployee);
        log.info("Successfully created new employee with id: {}", savedEmployee.getId());

        return EmployeeDto.builder()
                .id(savedEmployee.getId())
                .name(savedEmployee.getName())
                .email(savedEmployee.getEmail())
                .salary(savedEmployee.getSalary())
                .build();
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        log.info("Updating employee with id: {}", id);
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Employee not found with id: {}", id);
                    return new ResourceNotFoundException("Employee not found with id: " + id);
                });

        if (!employee.getEmail().equals(employeeDto.getEmail())) {
            log.error("Attempted to update email for employee with id: {}", id);
            throw new RuntimeException("The email of the employee cannot be updated");
        }

        employee = Employee.builder()
                        .id(employeeDto.getId())
                        .name(employeeDto.getName())
                        .email(employeeDto.getEmail())
                        .salary(employeeDto.getSalary())
                        .build();

        employee.setId(id);

        employeeRepository.save(employee);
        log.info("Successfully updated employee with id: {}", id);

        return employeeDto;
    }

    @Override
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with id: {}", id);
        boolean exists = employeeRepository.existsById(id);
        if (!exists) {
            log.error("Employee not found with id: {}", id);
            throw new ResourceNotFoundException("Employee not found with id: " + id);
        }

        employeeRepository.deleteById(id);
        log.info("Successfully deleted employee with id: {}", id);
    }
}
