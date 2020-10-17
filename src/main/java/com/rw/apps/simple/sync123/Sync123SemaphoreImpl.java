package com.rw.apps.simple.sync123;

import java.util.concurrent.Semaphore;


public class Sync123SemaphoreImpl extends AbstractSync123 {
    private Semaphore firstMutex;
    private Semaphore secondSemaphore;


    public Sync123SemaphoreImpl() {
    }


    @Override
    public void first() throws Exception {
        Sync123Helper.doRunFirst();
        firstMutex.release();
    }

    @Override
    public void second() throws Exception {
        firstMutex.acquire();
        Sync123Helper.doRunSecond();
        secondSemaphore.release();
    }

    @Override
    public void third() throws Exception {
        secondSemaphore.acquire(2);
        Sync123Helper.doRunThird();
    }

    @Override
    public void reset() {
        firstMutex = new Semaphore(0);
        secondSemaphore = new Semaphore(1);
    }
}
