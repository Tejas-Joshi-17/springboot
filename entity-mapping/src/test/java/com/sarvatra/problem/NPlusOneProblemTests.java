package com.sarvatra.problem;

import com.sarvatra.repositories.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class NPlusOneProblemTests {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void test() {
        departmentRepository.findAll();
    }

}
