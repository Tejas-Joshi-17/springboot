package com.sarvatra;

import com.sarvatra.clients.EmployeeClient;
import com.sarvatra.dto.EmployeeDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductionReadyFeatureApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(ProductionReadyFeatureApplicationTests.class);

    @Autowired
    private EmployeeClient employeeClient;

    @Test
    @Order(1)
    void createNewEmployeeTest() {
        EmployeeDTO employeeDTO = new EmployeeDTO(1L, "Anuj", "anuj@gmail.com", 26, "USER", 5000.0);
        EmployeeDTO savedEmployeeDTO = employeeClient.createNewEmployee(employeeDTO);
        log.info("{}", savedEmployeeDTO);
    }

    @Test
    @Order(2)
    void getEmployeeByIdTest() {
        EmployeeDTO employeeDTO = employeeClient.getEmployeeById(156L);
        log.info("{}", employeeDTO);
    }

    @Test
    @Order(3)
    void getAllEmployeesTest() {
        List<EmployeeDTO> employeeDTOList = employeeClient.getAllEmployees();
        log.info("Bellow are list of all employees :- \n{}", employeeDTOList);
    }

}
