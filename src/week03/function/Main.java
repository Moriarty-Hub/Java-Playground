package week03.function;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> stringList = new LinkedList<>();
        stringList.add("Hello");
        stringList.add("this");
        stringList.add("is");
        stringList.add("a");
        stringList.add("demo");
        stringList.add("of");
        stringList.add("Lambda");
        stringList.add("expression");

        List<Integer> resultList = Tool.executeFunctionToEachMemberOfList(stringList, String::length);  // Equals to (String str) -> str.length()

        for(int result : resultList) {
            System.out.println(result);
        }
    }
}
