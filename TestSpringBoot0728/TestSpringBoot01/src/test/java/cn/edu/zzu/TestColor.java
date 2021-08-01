package cn.edu.zzu;

public class TestColor {
    public static void main(String[] args) {
        for (Color  color : Color.values()) {
            System.out.println(color.getColor() + ":" + color.getIndex());
        }
    }
}
