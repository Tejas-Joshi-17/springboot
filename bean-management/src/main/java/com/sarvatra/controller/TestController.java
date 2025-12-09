package com.sarvatra.controller;

import com.sarvatra.dto.Person;
import com.sarvatra.dto.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping(path = "/bean-management", consumes = MediaType.APPLICATION_JSON_VALUE)
public class TestController {

    @Autowired
    private Person person;

    @Autowired
    private Person person1;

    @Autowired
    private School school;

    @GetMapping(path = "/request-scope")
    public void requestScopeTesting() {
        person.living();
        person1.living();
    }

    @GetMapping(path = "/singleton-scope")
    public void prototypeScopeTesting() {
        school.teacher();
        School.managementDepartment();
    }

}
