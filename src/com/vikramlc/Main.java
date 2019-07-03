package com.vikramlc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import static com.vikramlc.Main.EOF;

// To overcome the disadvantages of Synchronization and prevent Thread Interference, we use java.util.concurrentlocks.lock interfaces.
// Benefits of ReentrantLock in Java:
// 1) Ability to lock interruptibly.
// 2) Ability to timeout while waiting for lock.
// 3) Power to create fair lock.
// 4) API to get list of waiting thread for lock.
// 5) Flexibility to try for lock without blocking.

// Disadvantages of ReentrantLock in Java:
// 1) Programmer is responsible for acquiring and releasing lock.
// 2) Wrapping method body inside try-finally block, which makes code unreadable and hides business logic.
public class Main {

    public static final String EOF = "EOF";

    public static void main(String[] args) {
        List<String> buffer = new ArrayList<>();
        ReentrantLock bufferLock = new ReentrantLock();

        MyProducer myProducer = new MyProducer(buffer, ThreadColor.ANSI_GREEN, bufferLock);
        MyConsumer myConsumer1 = new MyConsumer(buffer, ThreadColor.ANSI_PURPLE, bufferLock);
        MyConsumer myConsumer2 = new MyConsumer(buffer, ThreadColor.ANSI_CYAN, bufferLock);

        new Thread(myProducer).start();
        new Thread(myConsumer1).start();
        new Thread(myConsumer2).start();
    }
}

class MyProducer implements Runnable{
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyProducer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        String[] nums = {"1", "2", "3", "4", "5", "6"};
        Random random = new Random();

        for(String num: nums) {
            System.out.println(color + "Adding " + num);

            bufferLock.lock();
            buffer.add(num);
            bufferLock.unlock();

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println("Producer was interrupted.");
            }
        }

        System.out.println(color + "Adding EOF and Exiting!!");

        bufferLock.lock();
        buffer.add("EOF");
        bufferLock.unlock();

    }
}

class MyConsumer implements Runnable {
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyConsumer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        while(true) {
            bufferLock.lock();
                if(buffer.isEmpty()) {
                    bufferLock.unlock();
                    continue;
                }
                if(buffer.get(0).equals(EOF)) {
                    System.out.println(color + "Exiting!!");
                    bufferLock.unlock();
                    break;
                } else {
                    System.out.println(color + "Removed " + buffer.remove(0));
                }
            bufferLock.unlock();
        }
    }
}