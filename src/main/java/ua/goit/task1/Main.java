package main.java.ua.goit.task1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(() -> {
            int countSeconds = 0;
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countSeconds++;
                System.out.println("Time from the start of the program " + countSeconds + " sec");
            }
        });

        Thread second = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("5 seconds passed");
            }
        });

        first.start();
        second.start();
    }
}
