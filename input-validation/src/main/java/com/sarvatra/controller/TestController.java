package com.sarvatra.controller;

import com.sarvatra.advice.ApiResponse;
import com.sarvatra.model.Student;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class TestController {

    @PostMapping(path = "/student")
    public ResponseEntity<ApiResponse<String>> addStudent(@RequestBody @Valid Student student) {
        return new ResponseEntity<>(new ApiResponse<>("ok"), HttpStatus.OK);
    }
}
