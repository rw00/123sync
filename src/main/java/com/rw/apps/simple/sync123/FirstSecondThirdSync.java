package com.rw.apps.simple.sync123;

public interface FirstSecondThirdSync {

    /**
     * Prints "first" (without quotes)
     *
     * @throws Exception in case any error occurs
     */
    void first() throws Exception;

    /**
     * Prints "second" (without quotes)
     *
     * @throws Exception in case any error occurs
     */
    void second() throws Exception;

    /**
     * Prints "third" (without quotes)
     *
     * @throws Exception in case any error occurs
     */
    void third() throws Exception;

    /**
     * Resets any conditions so that this instance can be used again
     */
    void reset();
}
