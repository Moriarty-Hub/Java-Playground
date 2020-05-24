package week09;


// 通过Synchronized关键字来实现线程安全性
public class ThreadSafeCounter2 implements Counter {

    private long count = 0;

    @Override
    public synchronized void addCount() {
        count++;
    }

    @Override
    public synchronized long getCount() {
        return count;
    }
}
