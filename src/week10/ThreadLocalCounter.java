package week10;

public class ThreadLocalCounter {

    // ThreadLocal类的作用在于为每一个线程都创建一个独立的变量
    // threadLocalCount是被ThreadLocal所修饰的，那么对于每一个线程而言，尽管它们共享ThreadLocalCounter这个类，但它们存储的值都是相互独立的
    // count没有被ThreadLocal修饰，那么它的值会被所有线程共享
    private Long count;
    private final ThreadLocal<Long> threadLocalCount;

    public ThreadLocalCounter() {
        count = 0L;
        threadLocalCount = ThreadLocal.withInitial(() -> 0L);
    }

    public void addThreadLocalCount() {
        threadLocalCount.set(this.getThreadLocalCount() + 1);
    }

    public Long getThreadLocalCount() {
        return threadLocalCount.get();
    }

    public void addCount() {
        count++;
    }

    public Long getCount() {
        return count;
    }

}
