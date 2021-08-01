package cn.edu.zzu.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *  复习基础
 */
public class Test01 {
    public static void main(String[] args) {
        method3();
    }



    /**
     * ListIterator
     */
    private static void method3() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("aaa");
        strings.add("bbb");
        strings.add("ccc");
        strings.add("ddd");
        ListIterator<String> stringListIterator = strings.listIterator(strings.size());
        while (stringListIterator.hasPrevious()) {
            String previous = stringListIterator.previous();
            System.out.println(previous);
            if (previous.equals("ccc")) {
                stringListIterator.remove();
            }
        }
        System.out.println("=========");
        printList(strings);
        System.out.println("=========");
        forPrintList(strings);
    }

    /**
     * iterator
     */
    private static void method2() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("aaa");
        strings.add("bbb");
        strings.add("ccc");
        strings.add("ddd");
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }


    /**
     * 9*9
     */
    private static void method1() {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + i * j + "  ");
            }
            System.out.println();
        }
    }


    /**
     * print foreach
     */
    private static void forPrintList(List list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }

    /**
     * Iterator print
     *
     * @param list
     */
    private static void printList(List list) {
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
