# Synchronize execution of 1 2 3
This is a basic app that implements synchronization of execution of threads in different ways.

The idea here is that 3 `Thread`s are  sharing one instance of type `FirstSecondThirdSync` which has 3 methods to print "first", "second" and "third"

Here are the different ways I implemented it (in order of personal preference):
1. `Sync123CountdownLatchImpl`: using 2 `java.util.concurrent.CountDownLatch`s
1. `Sync123SemaphoreImpl`: using 2 `java.util.concurrent.Semaphore`s
1. `Sync123ConditionalLockImpl`: using 1 `java.util.concurrent.locks.Lock` with 2 `java.util.concurrent.locks.Condition`s
1. `Sync123WaitNotifyImpl`: using 2 `Object` locks and 2 `volatile` `boolean`s based on `Object#wait()` and `Object#notify()` methods

The JUnit tests show that the implementation with `Lock` and `Condition` seems to be the slowest.
