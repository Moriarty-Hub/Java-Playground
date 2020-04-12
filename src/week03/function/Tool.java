package week03.function;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Tool {

    public static <T, R> List<R> executeFunctionToEachMemberOfList(List<T> list, Function<T, R> function) {
        List<R> resultList = new LinkedList<>();
        for (T t : list) {
            resultList.add(function.apply(t));
        }
        return resultList;
    }

}