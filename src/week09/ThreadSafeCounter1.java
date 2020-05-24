package week09;

import java.util.concurrent.atomic.AtomicLong;

// 通过AtomicLong来实现线程安全性
public class ThreadSafeCounter1 implements Counter {

    private final AtomicLong count = new AtomicLong(0);

    public void addCount() {
        count.incrementAndGet();
    }

    public long getCount() {
        return count.get();
    }
}
