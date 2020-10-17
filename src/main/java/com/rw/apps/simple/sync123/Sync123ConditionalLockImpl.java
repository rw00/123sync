package com.rw.apps.simple.sync123;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Sync123ConditionalLockImpl extends AbstractSync123 {
    private final Lock lock = new ReentrantLock();
    private final Condition firstRan = lock.newCondition();
    private final Condition secondRan = lock.newCondition();


    public Sync123ConditionalLockImpl() {
    }


    @Override
    public void first() throws Exception {
        try {
            lock.lock();
            Sync123Helper.doRunFirst();
            firstRan.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void second() throws Exception {
        try {
            lock.lock();
            firstRan.await();
            Sync123Helper.doRunSecond();
            secondRan.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void third() throws Exception {
        try {
            lock.lock();
            secondRan.await();
            Sync123Helper.doRunThird();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void reset() {
    }
}
