package com.sarvatra.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SchedulerExample {

    @Scheduled(cron = "*/1 * * * * *")
    public void logTransaction() {
        log.info("Transaction is started {}", Thread.currentThread().getName());
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void logDatabaseOperation() {
        log.info("DB Operation is started {}", Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
