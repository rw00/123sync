package com.rw.apps.simple.sync123;

public class SimpleLogger {
    private SimpleLogger() {
    }


    public static void log(String msg, Exception ex) {
        System.err.println(msg);
        ex.printStackTrace();
    }

    public static void log(String msg) {
        System.out.println(msg);
    }
}
