package com.rw.apps.simple.sync123;

public class Sync123Helper {
    private Sync123Helper() {
    }


    public static void doRunFirst() {
        SimpleLogger.log("first");
    }

    public static void doRunSecond() {
        SimpleLogger.log("second");
    }

    public static void doRunThird() {
        SimpleLogger.log("third");
    }

    public static void runSync(ExceptionalFunction function, String firstSecondOrThird) {
        try {
            function.run();
        } catch (Exception ex) {
            SimpleLogger.log("Exception in " + firstSecondOrThird, ex);
        }
    }
}
