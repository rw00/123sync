package com.rw.apps.simple.sync123;

/**
 * This solution is fine but uses legacy constructs and methods.
 */
public class Sync123WaitNotifyImpl extends AbstractSync123 {
    private final Object firstLock = new Object();
    private final Object secondLock = new Object();
    private volatile boolean firstRan;
    private volatile boolean secondRan;


    public Sync123WaitNotifyImpl() {
    }


    @Override
    public void first() throws Exception {
        synchronized (firstLock) {
            Sync123Helper.doRunFirst();
            firstRan = true;
            firstLock.notifyAll();
        }
    }

    @Override
    public void second() throws Exception {
        synchronized (firstLock) {
            while (!firstRan) {
                firstLock.wait();
            }
        }
        synchronized (secondLock) {
            Sync123Helper.doRunSecond();
            secondRan = true;
            secondLock.notify();
        }
    }

    @Override
    public void third() throws Exception {
        synchronized (secondLock) {
            while (!secondRan) {
                secondLock.wait();
            }
            Sync123Helper.doRunThird();
        }
    }

    @Override
    public void reset() {
        firstRan = false;
        secondRan = false;
    }
}
