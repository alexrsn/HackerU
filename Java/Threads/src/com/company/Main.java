package com.company;

public class Main {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("a " + i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("b " + i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //thread2.start();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("c " + i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        //Thread thread3 = new Thread(runnable);

        class MyRunnable implements Runnable{
            private String label;

            public MyRunnable(String label) {
                this.label = label;
            }

            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(label + " " + i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        Thread thread4 = new Thread(new MyRunnable("a"));
        //thread4.start();
        Thread thread5 = new Thread(new MyRunnable("b "));
        //thread5.start();

        MyThread myThread1 = new MyThread("a");
        myThread1.start();
        MyThread myThread2 = new MyThread("b");
        myThread2.start();

    }
}

class MyThread extends Thread{
    private String label;

    public MyThread(String label) {
        this.label = label;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(label + " " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Lola implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("c " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}