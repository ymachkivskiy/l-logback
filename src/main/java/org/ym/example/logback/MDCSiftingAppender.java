package org.ym.example.logback;


import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.slf4j.helpers.NOPLoggerFactory;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MDCSiftingAppender {

    private static class ThreadedWorker implements Runnable {
        private static final Logger log = LoggerFactory.getLogger(ThreadedWorker.class);

        private final String name;

        private ThreadedWorker(String name) {
            this.name = name;
        }


        public void run() {
            MDC.put("workerName", name);
            MDC.put("session", UUID.randomUUID().toString());


            for (int i = 0; i < 20; i++) {
                log.info("This is some logging message");

                try {
                    TimeUnit.MILLISECONDS.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error("Something bad happened", e);
                }
            }


            MDC.remove("workerName");
            MDC.remove("session");
        }
    }

    public static void main(String[] args) {

        LoggerContext c = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(c);

        ExecutorService executor = Executors.newFixedThreadPool(2);


        executor.execute(new ThreadedWorker("Bob"));
        executor.execute(new ThreadedWorker("Jil"));


        executor.shutdown();
    }

}
