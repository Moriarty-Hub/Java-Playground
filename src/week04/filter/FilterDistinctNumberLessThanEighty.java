package week04.filter;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class FilterDistinctNumberLessThanEighty {

    public static void main(String[] args) {
        Stream<Integer> integerStream = Stream.generate(new RandomNumberSupplier());
        integerStream.filter((i) -> (i < 80 && i > 0))
                .limit(100)
                .distinct()
                .forEach(System.out::println);
    }
}

class RandomNumberSupplier implements Supplier<Integer> {

    private final Random random;

    public RandomNumberSupplier() {
        random = new Random();
    }

    @Override
    public Integer get() {
        return random.nextInt();
    }
}
