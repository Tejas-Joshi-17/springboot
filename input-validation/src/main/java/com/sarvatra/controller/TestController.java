package com.sarvatra.controller;

import com.sarvatra.advice.ApiResponse;
import com.sarvatra.exception.ResourceNotFoundException;
import com.sarvatra.model.Student;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class TestController {

    @PostMapping(path = "/add-student")
    public ResponseEntity<ApiResponse<String>> addStudent(@RequestBody @Valid Student student) {
        return new ResponseEntity<>(new ApiResponse<>("ok"), HttpStatus.OK);
    }

    @PostMapping(path = "/update-student")
    public void updateStudent(@RequestBody @Valid Student student) {
        throw new ResourceNotFoundException("student (resource) not present");
    }

    @PostMapping(path = "/delete-student")
    public void deleteStudent(@RequestBody @Valid Student student) {
        throw new IllegalArgumentException("Getting Illegal Argument Exception");
    }

}