package week10;

import java.util.Arrays;

class Node {

    private String[] data;
    private int length;

    public Node(String[] data) {
        setData(data);
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
        this.length = data.length;
    }

    public int getLength() {
        return length;
    }

}

class RepairedNode {

    private String[] data;
    private int length;

    public RepairedNode(String[] data) {
        setData(data);
    }

    public String[] getData() {
        String[] returnData = new String[length];
        System.arraycopy(data, 0, returnData, 0, length);
        return returnData;
    }

    public void setData(String[] data) {
        length = data.length;
        this.data = new String[length];
        System.arraycopy(data, 0, this.data, 0, length);
    }

    public int getLength() {
        return length;
    }
}

public class ObjectEscape {

    public static void main(String[] args) {

        // 逸出是指将对象的私有变量错误地发布出去，导致调用者可以随意的对其进行修改，这种行为会破坏线程安全性
        String[] data1 = {"ONE", "TWO", "THREE"};
        Node node = new Node(data1);

        // 比如在下面这一例子中，可以通过修改外部的data数组导致node里面的data也一起跟着改变
        System.out.println(Arrays.toString(node.getData()));
        data1[0] = "FOUR";
        System.out.println(Arrays.toString(node.getData()));

        // 在RepairedNode这一方法中对set以及get方法进行了修改，使其中的data数组不会产生逸出
        // 由于任何方法都无法获得对基本类型的引用，因此确保了基本类型的局部变量始终被封闭在线程内
        String[] data2 = {"RED", "YELLOW", "BLUE"};
        RepairedNode repairedNode = new RepairedNode(data2);

        System.out.println(Arrays.toString(repairedNode.getData()));
        data2[2] = "GREEN";
        System.out.println(Arrays.toString(repairedNode.getData()));
    }

}


