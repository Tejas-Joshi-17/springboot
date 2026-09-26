package com.sarvatra.controller;

import com.sarvatra.dto.EmployeeDto;
import com.sarvatra.services.EmployeeMngService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeMngService employeeMngService;

    @GetMapping(path = "/{employeeId}")
    private EmployeeDto getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        return employeeMngService.getEmployeeById(id);
    }

}
