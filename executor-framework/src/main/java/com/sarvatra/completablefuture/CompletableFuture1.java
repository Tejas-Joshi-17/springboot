// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

package com.sarvatra.completablefuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CompletableFuture1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("Calling Method Started");
        learnCompletableFuture();
        log.info("Calling Method Ended ...");
    }

    private static void learnCompletableFuture()  {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(
                () -> getFutureName()
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

// 21:33:10.242 [main] INFO  c.s.c.CompletableFuture1 - Calling Method Started
// 21:33:10.249 [ForkJoinPool.commonPool-worker-1] INFO  c.s.c.CompletableFuture1 - Inside learnCompletableFuture function
// 21:33:10.250 [main] INFO  c.s.c.CompletableFuture1 - Calling Method Ended ...

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------