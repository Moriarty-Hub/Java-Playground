package week03.apple;

public class ApplePrintStyle2 implements AppleStringTemplate {
    public String toString(Apple apple) {
        return "Hello, I am a " + apple.getColor() + " apple.";
    }
}
