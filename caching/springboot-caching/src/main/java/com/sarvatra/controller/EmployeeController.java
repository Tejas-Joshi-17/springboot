package com.sarvatra.controller;

import com.sarvatra.dto.EmployeeDto;
import com.sarvatra.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(path = "/{id}")
    private ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        final EmployeeDto employeeDetails = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeDetails, HttpStatusCode.valueOf(200));
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employee) {
        final EmployeeDto newAddedEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(newAddedEmployee, HttpStatusCode.valueOf(200));
    }

    @PutMapping(path = "/{id}")
    private ResponseEntity<EmployeeDto> updateEmployeeById(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        final EmployeeDto updateEmployee = employeeService.updateEmployee(id, employeeDto);
        return new ResponseEntity<>(updateEmployee, HttpStatusCode.valueOf(200));
    }

    @DeleteMapping(path = "/{id}")
    private ResponseEntity<String> deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee Deleted", HttpStatusCode.valueOf(200));
    }

}
