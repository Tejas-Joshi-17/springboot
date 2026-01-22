package com.sarvatra.controller;

import com.sarvatra.model.Student;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/school")
public class SchoolController {

    @PostMapping(path = "/add-student")
    public ResponseEntity<String> addStudent(@RequestBody @Valid Student student) {
        return ResponseEntity.ok("student add successfully");
    }

}
