package com.rw.apps.simple.sync123;

public class FirstSecondThirdSyncRunner {
    public FirstSecondThirdSyncRunner() {
    }


    public static void main(String[] args) throws Exception {
        var sync123 = new Sync123CountdownLatchImpl();

        SimpleLogger.log("Running with " + sync123.getClass().getSimpleName());

        var thread1 = new Thread(() -> Sync123Helper.runSync(sync123::first, "first"));
        var thread2 = new Thread(() -> Sync123Helper.runSync(sync123::second, "second"));
        var thread3 = new Thread(() -> Sync123Helper.runSync(sync123::third, "third"));
        thread2.start();
        thread3.start();
        thread1.start();
        thread2.join();
        thread3.join();
        thread1.join();
    }
}
