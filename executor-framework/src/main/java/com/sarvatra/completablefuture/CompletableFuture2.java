// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


package com.sarvatra.completablefuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CompletableFuture2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("Calling Method Started");
        learnCompletableFuture();
        log.info("Calling Method Ended ...");
    }

    private static void learnCompletableFuture()  {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(
                () -> getFutureName(),
                new ThreadPoolExecutor(
                        4,
                        6,
                        2,
                        TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(10))
        );

        future.thenAccept(name ->
                log.info("Future Completed Name : {}", name)
        );
    }

    static String getFutureName() {
        try {
            log.info("Inside learnCompletableFuture function");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Tejas Joshi";
    }

}

// 21:33:29.401 [main] INFO  c.s.c.CompletableFuture2 - Calling Method Started
// 21:33:29.409 [pool-1-thread-1] INFO  c.s.c.CompletableFuture2 - Inside learnCompletableFuture function
// 21:33:29.411 [main] INFO  c.s.c.CompletableFuture2 - Calling Method Ended ...
// 21:33:34.410 [pool-1-thread-1] INFO  c.s.c.CompletableFuture2 - Future Completed Name : Tejas Joshi


// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------