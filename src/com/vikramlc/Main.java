package com.vikramlc;

public class Main {

    private static Object lock = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Worker(ThreadColor.ANSI_CYAN), "Priority 10");
        Thread t2 = new Thread(new Worker(ThreadColor.ANSI_PURPLE), "Priority 8");
        Thread t3 = new Thread(new Worker(ThreadColor.ANSI_GREEN), "Priority 6");
        Thread t4 = new Thread(new Worker(ThreadColor.ANSI_BLUE), "Priority 4");
        Thread t5 = new Thread(new Worker(ThreadColor.ANSI_RED), "Priority 2");

//        t1.setPriority(10);
//        t2.setPriority(8);
//        t3.setPriority(6);
//        t4.setPriority(4);
//        t5.setPriority(2);

        t3.start();
        t5.start();
        t2.start();
        t4.start();
        t1.start();
    }

    private static class Worker implements Runnable {
        private int runCount = 1;
        private String color;

        public Worker(String color) {
            this.color = color;
        }

        @Override
        public void run() {
            synchronized (lock) {
                for(int i=0; i<100; i++) {
                    System.out.format(color + "%s: %d\n", Thread.currentThread().getName(), runCount++);
                }
            }
        }
    }

}