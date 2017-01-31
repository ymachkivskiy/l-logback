package org.ym.example.logback;

import ch.qos.logback.core.PropertyDefinerBase;

import java.util.Random;

public class RandomIntPropetyDefiner extends PropertyDefinerBase {

    private Random r = new Random();

    private int bound;

    public void setBound(int bound) {
        this.bound = bound;
    }

    public String getPropertyValue() {
        return String.valueOf(r.nextInt(bound));
    }
}
