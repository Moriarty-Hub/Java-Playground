package issue;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SampleClassTest {

    private final SampleClass<Integer> sampleClass = new SampleClass<>();

    @Test
    public void test1Array() throws NoSuchFieldException, IllegalAccessException {
        Field intArray = SampleClass.class.getDeclaredField("intArray");
        intArray.setAccessible(true);
        assertArrayEquals(new int[]{1, 2, 3},(int[]) intArray.get(sampleClass));
    }

    @Test
    public void test2Array() throws NoSuchFieldException, IllegalAccessException {
        Field genericArray = SampleClass.class.getDeclaredField("genericArray");
        genericArray.setAccessible(true);
        assertArrayEquals(new int[]{4, 5, 6}, (int[]) genericArray.get(sampleClass));
    }

}
