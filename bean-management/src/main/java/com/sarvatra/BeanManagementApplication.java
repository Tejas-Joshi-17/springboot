package com.sarvatra;

import com.sarvatra.dto.PaymentService;
import com.sarvatra.dto.Paytm;
import com.sarvatra.notification.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class BeanManagementApplication implements CommandLineRunner{

    private final PaymentService paymentService1;
    private final Paytm paytm;

    @Autowired
    BeanManagementApplication(PaymentService paymentService, Paytm paytm) {
        this.paymentService1 = paymentService;
        this.paytm = paytm;
    }

    public static void main(String[] args) {
        SpringApplication.run(BeanManagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        paymentService1.running();
        paytm.pay();
    }



//	private final NotificationService notificationService;

//	@Autowired
//	public BeanManagementApplication(@Qualifier("email")  NotificationService notificationService) {
//		this.notificationService = notificationService;
//	}

	@Autowired
	private Map<String, NotificationService> gatewayService;


//	@Override
//	public void run(String... args) throws Exception {
//		paymentService1.running();
//		paymentService2.running();
//		notificationService.sendNotification();
//		for (Map.Entry<String, NotificationService> notificationServiceEntry : gatewayService.entrySet()) {
//			LOGGER.info("{} -> {}", notificationServiceEntry.getKey(), (notificationServiceEntry.getValue().heartBeat()));
//		}
//	}

}