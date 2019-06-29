package com.vikramlc;

public class Main {
    public static void main(String[] args) {
        Countdown countdown = new Countdown();

        CountdownThread t1 = new CountdownThread(countdown);
        t1.setName("Thread 1");
        CountdownThread t2 = new CountdownThread(countdown);
        t2.setName("Thread 2");

        t1.start();
        // Can use a join to make sure t1 is executed completely before t2
//        try {
//            t1.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        t2.start();
    }
}

class Countdown {

    //private int i;

    public void doCountdown() {
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

        for(int i=10; i>0; i--) {
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
