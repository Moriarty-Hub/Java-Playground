package week09;

public class NotThreadSafeCounter1 implements Counter {

    private long count = 0;

    public void addCount() {
        count++;
    }

    public long getCount() {
        return count;
    }
}
