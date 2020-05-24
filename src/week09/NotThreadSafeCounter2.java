package week09;

// 在确保只有一个线程写入volatile变量值的情况下，volatile变量才具有线程安全性
public class NotThreadSafeCounter2 implements Counter {

    private volatile long count = 0;

    public void addCount() {
        count++;
    }

    public long getCount() {
        return count;
    }
}
