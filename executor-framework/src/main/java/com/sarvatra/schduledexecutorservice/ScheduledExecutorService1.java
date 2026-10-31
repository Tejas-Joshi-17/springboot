// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

package com.sarvatra.schduledexecutorservice;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ScheduledExecutorService1 {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(3);

        log.info("Main Thread Started");

        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                log.info("Scheduled Service Run .....");
            }
        },
            4000,
            TimeUnit.MILLISECONDS
        );



        log.info("Main Thread Ended ...");
    }

}

// 13:22:25.185 [main] INFO  c.s.s.ScheduledExecutorService1 - Main Thread Started
// 13:22:25.189 [main] INFO  c.s.s.ScheduledExecutorService1 - Main Thread Ended ...
// 13:22:29.191 [pool-1-thread-1] INFO  c.s.s.ScheduledExecutorService1 - Scheduled Service Run .....

// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------