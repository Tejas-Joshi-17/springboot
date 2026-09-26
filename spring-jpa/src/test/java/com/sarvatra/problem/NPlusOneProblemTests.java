package com.sarvatra.problem;

import com.sarvatra.entities.Customer;
import com.sarvatra.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class NPlusOneProblemTests {

    private static final Logger log = LoggerFactory.getLogger(NPlusOneProblemTests.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void fetchCustomerList() {
        final List<Customer> customerList = customerRepository.findAll();
        log.info("{}", customerList);
    }

    @Test
    @Transactional
    void fetchCustomerData() {
        final List<Customer> customerList = customerRepository.findAll();

        for (Customer customer : customerList) {
            log.info("{} -> {}", customer.getName(), customer.getOrders());
        }
    }

    @Test
    void getAllData() {
        final List<Customer> allWithOrders = customerRepository.findAllWithOrders();
        for (Customer customer : allWithOrders) {
            log.info("{} -> {}", customer.getName(), customer.getOrders());
        }
    }
}
