package com.sarvatra.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@Primary
@Qualifier("sms")
public class SmsNotificationService implements NotificationService {
    private static final Logger log = LoggerFactory.getLogger(SmsNotificationService.class);

    @Override
    public void sendNotification() {
        log.info("Sending Sms Notification");
    }

    @Override
    public String heartBeat() {
        return "Sms Notification Service Working Properly";
    }
}
