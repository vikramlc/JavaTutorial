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

    // when a method is synchronized only one thread can execute that at a time so when the thread is executing the
    // method all other threads that want to call the method or any other synchronized method in that class will suspend
    // until the thread running the method exits it then another thread can run a
    // synchronized method then another etc to be clear if a class has three
    // synchronize methods then only one of these methods can ever run at a time and
    // only on one thread now since only one thread can execute a synchronized method
    // at a time
    // public synchronized void doCountdown() {
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

        //Synchronized: Every object has an intrinsic lock. Each thread has to obtain that lock and then it can run the operation.
        // So in synchronization we synchronize a block or a function and that means the thread has to obtain a lock and then it can
        // proceed to execute the function or block. Please note each time only thread can execute the function or block.
        // NOTE: Use synchronize always on a object instance or global variable and not on a local variable as it will be stored in Thread Stack.
        // Thread will keep a copy of the variable in the Thread Stack and the values are stored in the Heap so we see the values
        // updated by both the Threads.
        synchronized (this) {
            for(i=10; i>0; i--) {
                System.out.println(color + Thread.currentThread().getName() + ": i= " + i);
            }
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
