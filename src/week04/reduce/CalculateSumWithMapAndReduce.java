package week04.reduce;

import java.util.Arrays;
import java.util.List;

public class CalculateSumWithMapAndReduce {

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1,5,7,9,3,18,21,26,39,45);
        Integer sum = integerList.stream().map((i) -> 1).reduce(0, Integer::sum);
        System.out.println(sum);
    }
}
