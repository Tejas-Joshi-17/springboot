// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

package com.sarvatra.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class Future1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("Calling Method Started");
        learnFuture();
        log.info("Calling Method Ended ...");

    }

    private static void learnFuture() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        final Future<String> myName = executorService.submit(() -> {
            Thread.sleep(5000);
            return "Tejas Joshi";
        });

    }

}

// 18:34:32.922 [main] INFO  com.sarvatra.future.Future1 - Calling Method Started
// 18:34:32.927 [main] INFO  com.sarvatra.future.Future1 - Calling Method Ended ...

// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------