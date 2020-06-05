package issue;

@SuppressWarnings("unchecked")
public class SampleClass<T> {
    private final int[] intArray = new int[]{1, 2, 3};
    private final T[] genericArray = (T[]) new Object[]{4, 5, 6};
}
