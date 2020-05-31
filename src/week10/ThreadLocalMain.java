package week10;

public class ThreadLocalMain {

    public static void main(String[] args) throws InterruptedException {

        // 在这个测试类中，创建了一个ThreadLocalCounter类的实例，同时也创建了20个线程，每个线程都对里面的counter和ThreadLocalCounter做100次累加
        // 可以通过在每一个线程执行结束之后打印出它们的值来验证ThreadLocal类起到的作用

        // 可以发现，每执行完一个线程，count的值都递增100， 而ThreadLocal的值始终是100
        ThreadLocalCounter threadLocalCounter = new ThreadLocalCounter();
        Thread[] threads1 = new Thread[20];
        for (int i = 0; i < 20; i++) {
            threads1[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    threadLocalCounter.addCount();
                    threadLocalCounter.addThreadLocalCount();
                }
                System.out.println(Thread.currentThread().getName() + " Count: " + threadLocalCounter.getCount());
                System.out.println(Thread.currentThread().getName() + " Thread Local Count: " + threadLocalCounter.getThreadLocalCount());
            });
            threads1[i].start();
            threads1[i].join();
        }
    }
}
