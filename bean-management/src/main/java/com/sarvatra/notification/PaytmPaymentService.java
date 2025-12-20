package com.sarvatra.notification;

import com.sarvatra.dto.Paytm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaytmPaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaytmPaymentService.class);

    @Bean
    public Paytm getPaytmBean() {
        log.info("Create Custom Paytm Bean");
        return new Paytm();
    }

}