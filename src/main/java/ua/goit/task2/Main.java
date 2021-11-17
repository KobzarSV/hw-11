package main.java.ua.goit.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    volatile static int n = 15;
    volatile static int i = 1;
    static List<String> list = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws InterruptedException {

        Thread A = new Thread(Main::fizz);
        A.start();
        Thread B = new Thread(Main::buzz);
        B.start();
        Thread C = new Thread(Main::fizzbuzz);
        C.start();
        Thread D = new Thread(Main::number);
        D.start();

        Thread.sleep(1000);
        System.out.println(list);
    }

    public static void fizz() {
        while (i <= n) {
            if (i % 3 == 0 && i % 5 != 0) {
                list.add("fizz");
                i++;
            }
        }
    }

    public static void buzz() {
        while (i <= n) {
            if (i % 5 == 0 && i % 3 != 0) {
                list.add("buzz");
                i++;
            }
        }
    }

    public static void fizzbuzz() {
        while (i <= n) {
            if (i % 3 == 0 && i % 5 == 0) {
                list.add("fizzbuzz");
                i++;
            }
        }
    }

    public static void number() {
        while (i <= n) {
            if (i % 3 != 0) {
                if (i % 5 != 0) {
                    list.add(String.valueOf(i));
                    i++;
                }
            }
        }
    }
}
