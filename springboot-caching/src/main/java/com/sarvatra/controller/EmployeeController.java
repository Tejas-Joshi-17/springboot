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
    private ResponseEntity<String> getEmployeeById(@PathVariable Long id) {
        employeeService.getEmployeeById(id);
        return new ResponseEntity<>("Fetched Employee Details", HttpStatusCode.valueOf(200));
    }

    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody EmployeeDto employee) {
        final ResponseEntity<EmployeeDto> newAddedEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>("New Employee Added", HttpStatusCode.valueOf(200));
    }

    @PutMapping(path = "/{id}")
    private ResponseEntity<String> updateEmployeeById(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        employeeService.updateEmployee(id, employeeDto);
        return new ResponseEntity<>("Employee Details Updated", HttpStatusCode.valueOf(200));
    }

    @DeleteMapping(path = "/{id}")
    private ResponseEntity<String> deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee Deleted", HttpStatusCode.valueOf(200));
    }

}
