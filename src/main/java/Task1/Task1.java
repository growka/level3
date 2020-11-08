package Task1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task1 {
//    Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.
    private static volatile char letter = 'A';
    private static Object monitor = new Object();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.execute(()->{
            for (int i = 0; i < 5 ; i++) {
                synchronized (monitor) {
                    while (letter!='A') {
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("A");
                    letter = 'B';
                    monitor.notifyAll();
                }
            }
        });

        executorService.execute(()->{
            for (int i = 0; i < 5 ; i++) {
                synchronized (monitor) {
                    while (letter!='B') {
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("B");
                    letter = 'C';
                    monitor.notifyAll();
                }
            }
        });

        executorService.execute(()->{
            for (int i = 0; i < 5 ; i++) {
                synchronized (monitor) {
                    while (letter!='C') {
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("C");
                    letter = 'A';
                    monitor.notifyAll();
                }
            }

        });

    executorService.shutdown();
    }

}

