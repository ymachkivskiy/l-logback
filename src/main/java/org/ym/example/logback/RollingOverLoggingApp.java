package org.ym.example.logback;

import ch.qos.logback.classic.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class RollingOverLoggingApp {
    public static void main(String[] args) throws InterruptedException {

        Logger xyzFooLog = (Logger) getLogger("x.y.z.Foo");


        for (int i = 0; i < 1000; i++) {
            xyzFooLog.info("Some logging message");
            Thread.sleep(50);
        }
    }
}
