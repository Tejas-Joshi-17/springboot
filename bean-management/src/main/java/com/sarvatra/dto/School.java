package com.sarvatra.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class School {

    private static final Logger log = LoggerFactory.getLogger(School.class);
    private static int STUDENT_COUNT = 0;

    public static void managementDepartment() {
        log.info("Current Student Count is :- {}", STUDENT_COUNT);
        log.info("Increasing Student Count by 1 by Management Department :- {}", ++STUDENT_COUNT);
    }

    public void teacher() {
        log.info("Current Student Count is :- {}", STUDENT_COUNT);
        log.info("Increasing Student Count by 1 by Teacher :- {}", ++STUDENT_COUNT);

        int teacherCount = 20;
        log.info("Current Teacher Count is :- {}", teacherCount);
    }

}
