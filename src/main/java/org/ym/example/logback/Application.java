package org.ym.example.logback;

import ch.qos.logback.classic.Logger;

import static org.slf4j.LoggerFactory.getLogger;


public class Application {

    public static void main(String[] args) {

        defaultAdditivityExample();

    }


    private static void defaultAdditivityExample() {

        Logger xyzFooLog = (Logger) getLogger("x.y.z.Foo");


        xyzFooLog.info("Some logging message");
    }
}
