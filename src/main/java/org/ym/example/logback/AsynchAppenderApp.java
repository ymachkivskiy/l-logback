package org.ym.example.logback;


import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Contended;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class AsynchAppenderApp {

    private static class ThreadedWorker implements Runnable {
        private static final Logger log = LoggerFactory.getLogger(ThreadedWorker.class);

        @Contended
        private final AtomicBoolean stoper;
        private final String name;

        private ThreadedWorker(String name, AtomicBoolean stopper) {
            this.name = name;
            this.stoper = stopper;
        }


        public void run() {

            while (!stoper.get()) {
                log.info("This is some logging message of {}", name);
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {


        ExecutorService executor = Executors.newCachedThreadPool();

        AtomicBoolean stopper = new AtomicBoolean(false);

        executor.execute(new ThreadedWorker("Bob", stopper));
        executor.execute(new ThreadedWorker("Jil", stopper));
        executor.execute(new ThreadedWorker("Mary", stopper));
        executor.execute(new ThreadedWorker("Jack", stopper));
        executor.execute(new ThreadedWorker("John", stopper));
        executor.execute(new ThreadedWorker("Bob", stopper));
        executor.execute(new ThreadedWorker("Jil", stopper));
        executor.execute(new ThreadedWorker("Mary", stopper));
        executor.execute(new ThreadedWorker("Jack", stopper));
        executor.execute(new ThreadedWorker("John", stopper));

        TimeUnit.SECONDS.sleep(3);

        executor.shutdown();

        stopper.set(true);

    }

}
