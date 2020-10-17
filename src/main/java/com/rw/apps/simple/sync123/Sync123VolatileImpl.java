package com.rw.apps.simple.sync123;

/**
 * The worst solution of them all. Don't implement it!
 */
public class Sync123VolatileImpl extends AbstractSync123 {
    private volatile boolean firstRan;
    private volatile boolean secondRan;


    public Sync123VolatileImpl() {
    }


    @Override
    public void first() throws Exception {
        Sync123Helper.doRunFirst();
        firstRan = true;
    }

    @Override
    public void second() throws Exception {
        while (!firstRan) {
            // just wait
        }
        Sync123Helper.doRunSecond();
        secondRan = true;
    }

    @Override
    public void third() throws Exception {
        while (!secondRan) {
            // CPU-intensive activity: spinning...
        }
        Sync123Helper.doRunThird();
    }

    @Override
    public void reset() {
        firstRan = false;
        secondRan = false;
    }
}
