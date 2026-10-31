// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

package com.sarvatra.threadpoolexecutor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class ThreadPoolExecutor1 {

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                4,
                6,
                2,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10)
        );

        log.info("Main Thread Started");

        threadPoolExecutor.execute(new LongRunningTask("Tejas"));

        log.info("Main Thread Ended");

    }
}

// 12:02:07.683 [main] INFO  c.s.t.ThreadPoolExecutor1 - Main Thread Started
// 12:02:07.686 [main] INFO  c.s.t.ThreadPoolExecutor1 - Main Thread Ended
// 12:02:07.686 [pool-1-thread-1] INFO  c.s.t.LongRunningTask - Tejas task started ....
// 12:02:11.687 [pool-1-thread-1] INFO  c.s.t.LongRunningTask - Tejas task finished ....


// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------