package com.vikramlc;

public class Main {
    public static void main(String[] args) {
        Countdown countdown = new Countdown();

        CountdownThread t1 = new CountdownThread(countdown);
        t1.setName("Thread 1");
        CountdownThread t2 = new CountdownThread(countdown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();
    }
}

class Countdown {

    private int i;

    //Synchronized: Every object has an intrinsic lock. Each thread has to obtain that lock and then it can run the operation.
    // So in synchronization we synchronize a block or a function and that means the thread has to obtain a lock and then it can
    // proceed to execute the function or block. Please note each time only thread can execute the function or block.
    public synchronized void doCountdown() {
        String color;

        switch(Thread.currentThread().getName()) {
            case "Thread 1":
                color = ThreadColor.ANSI_CYAN;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_PURPLE;
                break;
            default:
                color = ThreadColor.ANSI_GREEN;
        }

        for(i=10; i>0; i--) {
            System.out.println(color + Thread.currentThread().getName() + ": i= " + i);
        }
    }
}

class CountdownThread extends Thread {
    private Countdown countdownThread;

    public CountdownThread(Countdown countdown) {
        this.countdownThread = countdown;
    }

    @Override
    public void run() {
        countdownThread.doCountdown();
    }
}
