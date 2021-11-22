package main.java.ua.goit.task1;

public class Main {
    private static int countSeconds = 0;
    private static final Object MONITOR = new Object();

    public static void main(String[] args) {

        Thread first = new Thread(() -> {
            synchronized (MONITOR) {
                while (true) {
                    try {
                        MONITOR.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countSeconds++;
                    System.out.println("Time from the start of the program " + countSeconds + " sec");
                    if (countSeconds % 5 == 0) {
                        MONITOR.notifyAll();
                    }
                }
            }
        });

        Thread second = new Thread(() -> {
            synchronized (MONITOR) {
                while (true) {
                    try {
                        MONITOR.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("5 seconds passed");
                }
            }
        });

        first.start();
        second.start();
    }
}
