package com.sarvatra.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@Primary
@Qualifier("email")
public class EmailNotificationService implements NotificationService {
    private static final Logger log = LoggerFactory.getLogger(EmailNotificationService.class);

    @Override
    public void sendNotification() {
        log.info("Sending Email Notification");
    }

    @Override
    public String heartBeat() {
        return "Email Notification Service Working Properly";
    }
}
