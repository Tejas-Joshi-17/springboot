package com.sarvatra.repository;

import com.sarvatra.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("""
           SELECT DISTINCT c
           FROM Customer c
           LEFT JOIN FETCH c.orders
           """)
    List<Customer> findAllWithOrders();
}
