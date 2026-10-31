package com.sarvatra.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AsyncSchedulerExample {

    @Scheduled(cron = "*/1 * * * * *")
    @Async(value = "asyncJob")
    public void logTransaction() {
        log.info("Transaction is started {}", Thread.currentThread().getName());
    }

    @Scheduled(cron = "*/3 * * * * *")
    @Async(value = "asyncJob")
    public void logDatabaseOperation() {
        log.info("DB Operation is started {}", Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
