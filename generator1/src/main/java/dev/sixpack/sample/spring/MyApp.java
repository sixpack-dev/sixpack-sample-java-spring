package dev.sixpack.sample.spring;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication(scanBasePackages = {
        "dev.sixpack.sample.spring"
})
public class MyApp {
    // This latch is used to wait for the JVM to shut down
    // In case you use Spring Boot Web starter or other starters this can be omitted
    private static final CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        System.out.println("Starting MyApp");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down MyApp");
            latch.countDown(); // Release the latch when the JVM shuts down
        }));
        new SpringApplicationBuilder(MyApp.class).run(args);
        try {
            latch.await();
            System.out.println("MyApp has shut down");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}