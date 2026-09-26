package com.sarvatra.services.impl;

import com.sarvatra.services.ShipmentService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Override
    public String orderPackage(Long orderId) {
        try {
            log.info("Processing the order...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("Error occurred while processing the order", e);
        }
        return "Order has been processed successfully, orderId: " + orderId;
    }

    @Override
    @Transactional
    public String trackPackage(Long orderId) {
        try {
            log.info("Tracking the order...");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            log.error("Error occurred while tracking the order", e);
        }

        return "Order has been tracked successfully, orderId: " + orderId;
    }

}
