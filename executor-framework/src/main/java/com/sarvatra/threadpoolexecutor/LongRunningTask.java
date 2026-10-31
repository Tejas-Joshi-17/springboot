package com.sarvatra.threadpoolexecutor;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class LongRunningTask implements Runnable{

    private final String taskName;

    @Override
    public void run() {
        log.info("{} task started ....", taskName);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            log.info("{} task interrupted", taskName);
        }
        log.info("{} task finished ....", taskName);
    }

}
