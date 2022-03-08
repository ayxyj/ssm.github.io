package cn.edu.zzu.p1;

public class DuoTaiDemo {
    public static void main(String[] args) {
        Zi.method();
        Fu.method();
        Fu f = new Zi();
        f.show();

        Zi zi = new Zi();
        System.out.println(zi.num);
    }
}
