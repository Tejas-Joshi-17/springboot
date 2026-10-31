// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

package com.sarvatra.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class Future2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("Calling Method Started");
        learnFuture();
        log.info("Calling Method Ended ...");

    }

    private static void learnFuture() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        final Future<String> myName = executorService.submit(() -> {
            Thread.sleep(70000);
            return "Tejas Joshi";
        });

        final String name = myName.get();
        log.info("Future Name : {}", name);
    }

}


// 18:36:11.388 [main] INFO  com.sarvatra.future.Future2 - Calling Method Started
// 18:37:21.396 [main] INFO  com.sarvatra.future.Future2 - Future Name : Tejas Joshi
// 18:37:21.396 [main] INFO  com.sarvatra.future.Future2 - Calling Method Ended ...   <--- Blocks Calling Method

// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------