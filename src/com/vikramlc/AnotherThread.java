package com.vikramlc;

public class AnotherThread extends Thread {
    @Override
    public void run() {
        System.out.println(ThreadColor.ANSI_BLUE +  "Hello from " + currentThread().getName());

        try {
            Thread.sleep(3000); // No statements after this are executed once an exception is thrown as this point of time.
            System.out.println("Another thread is awake after sleeping for 3 seconds.");
        } catch (InterruptedException e) {
            System.out.println(ThreadColor.ANSI_BLUE + "Another thread woke me up.");
        }

        System.out.println(ThreadColor.ANSI_BLUE + "Three seconds have passed and I am awake.");

    }

}
