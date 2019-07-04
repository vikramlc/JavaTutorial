package com.vikramlc;

public class Main {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
    }

    public static class Thread1 extends Thread {
        @Override
        public void run() {

            synchronized (lock1) {
                System.out.println("Thread 1: Obtained lock1");

                try {
                    Thread.sleep(100);
                } catch(InterruptedException e) {

                }
                System.out.println("Thread 1: Trying to obtain lock2");
                synchronized (lock2) {
                    System.out.println("Thread 1: Obtained lock1 and lock2");
                }
                System.out.println("Thread 1: Released lock2");
            }
            System.out.println("Thread 1: Released lock1 and lock2");
        }
    }

    public static class Thread2 extends Thread {
        @Override
        public void run() {

            synchronized (lock1) {
                System.out.println("Thread 2: Obtained lock1");

                try {
                    Thread.sleep(100);
                } catch(InterruptedException e) {

                }
                System.out.println("Thread 2: Trying to obtain lock1");
                synchronized (lock2) {
                    System.out.println("Thread 2: Obtained lock1 and lock2");
                }
                System.out.println("Thread 2: Released lock2");
            }
            System.out.println("Thread 2: Released lock1 and lock2");
        }
    }

}