package week04.map;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GetSquareOfAllListMembers {

    private final List<Integer> integerList;

    public GetSquareOfAllListMembers(List<Integer> integerList) {
        this.integerList = integerList;
    }

    public List<Integer> getResult() {
        return integerList.stream()
                .map((i) -> (i * i))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1,3,7,11,28,36,55,80);
        GetSquareOfAllListMembers getSquareOfAllListMembers = new GetSquareOfAllListMembers(integerList);
        getSquareOfAllListMembers.getResult().forEach(System.out::println);
        System.out.println("------------------------");
        getSquareOfAllListMembers.getResult().stream().filter((i) -> i % 111 == 0).findAny().ifPresent(System.out::println);
    }
}
