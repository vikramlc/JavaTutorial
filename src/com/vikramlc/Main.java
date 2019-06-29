package com.vikramlc;

public class Main {

    public static void main(String[] args) {
        System.out.println(ThreadColor.ANSI_PURPLE + "Hello from the main thread!");

        Thread anotherThread = new AnotherThread();
        anotherThread.setName("====Another Thread====");
        anotherThread.start();

        new Thread() {
            @Override
            public void run() {
                System.out.println(ThreadColor.ANSI_GREEN + "Hello from the anonymous");
            }
        }.start();

//        //Usual way without anonymouse class
//        Thread myRunnableThread = new Thread(new MyRunnable());
//        myRunnableThread.start();

        Thread myRunnableThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(ThreadColor.ANSI_RED + "Hello from the anonymous runnable class");

                try {
                    anotherThread.join(2000);
                    System.out.println(ThreadColor.ANSI_RED + "Another thread executed, now I am awake.");
                } catch(InterruptedException e) {
                    System.out.println(ThreadColor.ANSI_RED + "Another thread was interrupted and not executed completely");
                }
            }
        });

        myRunnableThread.start();

        //anotherThread.interrupt(); // This is called when anotherThread is sleeping so an interrupted exception is thrown.

        System.out.println(ThreadColor.ANSI_PURPLE + "Hello again from the main thread.");
        //anotherThread.start(); // Gives illegal state exception
    }
}
