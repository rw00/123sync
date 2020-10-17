package com.rw.apps.simple.sync123;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


public class Sync123ImplTest {
    private static final int ITERATIONS = 50;
    private PrintStream stdOut;


    public Sync123ImplTest() {
    }


    @BeforeEach
    public void setUp() {
        stdOut = System.out;
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdOut);
    }

    @ParameterizedTest
    @MethodSource("implementationsOfFirstSecondThirdSync")
    public void test123Sync(FirstSecondThirdSync sync123) throws Exception {
        for (var i = 0; i < ITERATIONS; i++) {
            // Definitely not an ideal test. But perhaps the interface should return instead of print (void)?
            var bytesOutStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(bytesOutStream));

            var executorService = Executors.newFixedThreadPool(3);
            executorService.submit(() -> Sync123Helper.runSync(sync123::second, "second"));
            executorService.submit(() -> Sync123Helper.runSync(sync123::third, "third"));
            executorService.submit(() -> Sync123Helper.runSync(sync123::first, "first"));
            executorService.shutdown();
            executorService.awaitTermination(2, TimeUnit.SECONDS);

            Assertions.assertEquals("first\nsecond\nthird\n", bytesOutStream.toString().replaceAll("\r\n", "\n"));
            sync123.reset();
        }
    }

    private static Stream<Arguments> implementationsOfFirstSecondThirdSync() {
        //J-
        return Stream.of(
            Arguments.of(new Sync123ConditionalLockImpl()),
            Arguments.of(new Sync123CountdownLatchImpl()),
            Arguments.of(new Sync123SemaphoreImpl()),
            Arguments.of(new Sync123WaitNotifyImpl()),
            Arguments.of(new Sync123VolatileImpl())
        );
        //J+
    }
}
