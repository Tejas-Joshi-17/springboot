package com.sarvatra.controller;

import com.sarvatra.dto.Paytm;
import com.sarvatra.dto.Person;
import com.sarvatra.dto.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping(path = "/bean-management")
public class TestController {

    @Autowired
    private Paytm paytm;

    @Autowired
    private Person person;

    @GetMapping(path = "/request-scope")
    public void requestScopeTesting() {
        person.living();
    }

}

//    @Autowired
//    private School school;

//    @GetMapping(path = "/singleton-scope")
//    public void prototypeScopeTesting() {
//        school.teacher();
//        School.managementDepartment();
//    }
