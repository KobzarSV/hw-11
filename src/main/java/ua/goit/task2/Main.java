package main.java.ua.goit.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    volatile static int numbers = 15;
    volatile static int counter = 1;
    static final List<String> list = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws InterruptedException {

        Thread a = new Thread(Main::fizz);
        a.start();
        Thread b = new Thread(Main::buzz);
        b.start();
        Thread c = new Thread(Main::fizzbuzz);
        c.start();
        Thread d = new Thread(Main::number);
        d.start();

        Thread.sleep(1000);
        System.out.println(list);
    }

    public static void fizz() {
        synchronized (list) {
            while (counter <= numbers) {
                if (counter % 3 == 0 && counter % 5 != 0) {
                    list.add("fizz");
                    counter++;
                    list.notifyAll();
                } else {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void buzz() {
        synchronized (list) {
            while (counter <= numbers) {
                if (counter % 5 == 0 && counter % 3 != 0) {
                    list.add("buzz");
                    counter++;
                    list.notifyAll();
                } else {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void fizzbuzz() {
        synchronized (list) {
            while (counter <= numbers) {
                if (counter % 3 == 0 && counter % 5 == 0) {
                    list.add("fizzbuzz");
                    counter++;
                    list.notifyAll();
                } else {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void number() {
        synchronized (list) {
            while (counter <= numbers) {
                if (counter % 3 == 0 || counter % 5 == 0) {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    list.add(String.valueOf(counter));
                    counter++;
                    list.notifyAll();
                }
            }
        }
    }
}
