package week03.apple;

public class ApplePrintStyle1 implements AppleStringTemplate {

    public String toString(Apple apple) {
        return "Color: " + apple.getColor().toString() + ", Weight: " + apple.getWeight();
    }
}
