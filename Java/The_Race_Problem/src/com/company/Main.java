package com.company;

public class Main {

    public static void main(String[] args) {
        UseCounter useCounter = new UseCounter();
        Thread t1 = new Thread(useCounter);
        Thread t2 = new Thread(useCounter);
        Thread t3 = new Thread(useCounter);
        t1.start();
        t2.start();
        t3.start();

    }
}

class UseCounter implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            //synchronized(pointer) mean lock threads where it appears and then run code in {...} synchronized
            //pointer should be to object you going to change
            synchronized (this){
                Counter.count++;
                System.out.print(Counter.count + " ");
            }
        }
    }
}

class Counter{
    public static int count = 0;

}