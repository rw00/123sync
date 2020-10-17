package com.rw.apps.simple.sync123;

import java.util.concurrent.CountDownLatch;


public class Sync123CountdownLatchImpl extends AbstractSync123 {
    private CountDownLatch secondLatch;
    private CountDownLatch thirdLatch;


    public Sync123CountdownLatchImpl() {
    }


    @Override
    public void first() throws Exception {
        Sync123Helper.doRunFirst();
        secondLatch.countDown();
    }

    @Override
    public void second() throws Exception {
        secondLatch.await();
        Sync123Helper.doRunSecond();
        thirdLatch.countDown();
    }

    @Override
    public void third() throws Exception {
        thirdLatch.await();
        Sync123Helper.doRunThird();
    }

    @Override
    public void reset() {
        secondLatch = new CountDownLatch(1);
        thirdLatch = new CountDownLatch(1);
    }
}
