package com.sarvatra.services;

import com.sarvatra.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto getEmployeeById(Long id);
    EmployeeDto createNewEmployee(EmployeeDto employeeDto);
    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);
    void deleteEmployee(Long id);
}
