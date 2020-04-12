package week03.apple;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void printAllAppleInGivenStyle(List<Apple> apples, AppleStringTemplate appleStringTemplate) {
        for(Apple apple : apples) {
            System.out.println(appleStringTemplate.toString(apple));
        }
    }

    public static void main(String[] args) {
        List<Apple> apples = new LinkedList<>();
        apples.add(new Apple(Color.RED, 120));
        apples.add(new Apple(Color.GREEN, 135));
        apples.add(new Apple(Color.GREEN, 110));
        apples.add(new Apple(Color.RED, 125));
        apples.add(new Apple(Color.GREEN, 140));

        System.out.println("Style 1");
        printAllAppleInGivenStyle(apples, new ApplePrintStyle1());
        System.out.println("-----------------------------------");

        System.out.println("Style 2");
        printAllAppleInGivenStyle(apples, new ApplePrintStyle2());
        System.out.println("-----------------------------------");

    }
}
