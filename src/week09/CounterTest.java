package week09;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CounterTest {

    private final Thread[] threads = new Thread[1000];

    @Test
    public void notThreadSafeCounter1Test() {
        NotThreadSafeCounter1 notThreadSafeCounter1 = new NotThreadSafeCounter1();
        executeAddCountOperationOnGivenCounter(notThreadSafeCounter1);
        assertNotEquals(10000000, notThreadSafeCounter1.getCount());
    }

    @Test
    public void notThreadSafeCounter2Test() {
        NotThreadSafeCounter2 notThreadSafeCounter2 = new NotThreadSafeCounter2();
        executeAddCountOperationOnGivenCounter(notThreadSafeCounter2);
        assertNotEquals(10000000, notThreadSafeCounter2.getCount());
    }

    @Test
    public void threadSafeCounter1Test() {
        ThreadSafeCounter1 threadSafeCounter1 = new ThreadSafeCounter1();
        executeAddCountOperationOnGivenCounter(threadSafeCounter1);
        assertEquals(10000000, threadSafeCounter1.getCount());
    }

    @Test
    public void threadSafeCounter2Test() {
        ThreadSafeCounter2 threadSafeCounter2 = new ThreadSafeCounter2();
        executeAddCountOperationOnGivenCounter(threadSafeCounter2);
        assertEquals(10000000, threadSafeCounter2.getCount());
    }

    private void executeAddCountOperationOnGivenCounter(Counter counter) {
        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    counter.addCount();
                }
            });
        }

        Arrays.stream(threads).forEach(Thread::start);

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
