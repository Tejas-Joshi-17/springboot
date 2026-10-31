// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

package com.sarvatra;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.CompletableFuture;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class ExecutorFrameworkApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ExecutorFrameworkApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        log.info("Calling Method Started");
//        learnCompletableFuture();
//        log.info("Calling Method Ended ...");
    }

    private static void learnCompletableFuture()  {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(
                () -> getFutureName()
        );

        future.thenAccept(name ->
                log.info("Future Completed Name : {}", name)
        );
    }

    private static String getFutureName() {
        try {
            log.info("Inside learnCompletableFuture function");
            Thread.sleep(90000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Tejas Joshi";
    }
}

// 2026-10-05 21:53:34,012 INFO  [main] c.s.ExecutorFrameworkApplication: Calling Method Started
// 2026-10-05 21:53:34,015 INFO  [ForkJoinPool.commonPool-worker-1] c.s.ExecutorFrameworkApplication: Inside learnCompletableFuture function
// 2026-10-05 21:53:34,015 INFO  [main] c.s.ExecutorFrameworkApplication: Calling Method Ended ...
// 2026-10-05 21:55:04,016 INFO  [ForkJoinPool.commonPool-worker-1] c.s.ExecutorFrameworkApplication: Future Completed Name : Tejas Joshi    <--- After 90 secs


// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------