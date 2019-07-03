package com.vikramlc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.vikramlc.Main.EOF;

// Advantage of synchronization:
// * Makes sure that the threads are executed in order i.e. no two threads are adding content to
// the buffer at the same time.

// Disadvantages of Synchronization:
// * Threads waiting to execute the synchronization code cannot be interrupted.
// * A synchronization block cannot start in one method and end in another, obviously.
// * We cannot test if the object's intrinsic lock is available. A thread wanting to execute the synchronization code
// will have to wait until the lock is available or block until it is free.
// * If multiple threads are waiting to get a lock, it's not FCFS. The lock will be acquired at random.

// To overcome these disadvantages and prevent Thread Interference, we use java.util.concurrentlocks.lock interfaces.
public class Main {

    public static final String EOF = "EOF";

    public static void main(String[] args) {
        List<String> buffer = new ArrayList<>();
        MyProducer myProducer = new MyProducer(buffer, ThreadColor.ANSI_GREEN);
        MyConsumer myConsumer1 = new MyConsumer(buffer, ThreadColor.ANSI_PURPLE);
        MyConsumer myConsumer2 = new MyConsumer(buffer, ThreadColor.ANSI_CYAN);
        new Thread(myProducer).start();
        new Thread(myConsumer1).start();
        new Thread(myConsumer2).start();
    }
}

class MyProducer implements Runnable{
    private List<String> buffer;
    private String color;

    public MyProducer(List<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        String[] nums = {"1", "2", "3", "4", "5", "6"};
        Random random = new Random();

        for(String num: nums) {
            System.out.println(color + "Adding " + num);
            synchronized(buffer) {
                buffer.add(num);
            }

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println("Producer was interrupted.");
            }
        }

        System.out.println(color + "Adding EOF and Exiting!!");
        synchronized (buffer) {
            buffer.add("EOF");
        }

    }
}

class MyConsumer implements Runnable {
    private List<String> buffer;
    private String color;

    public MyConsumer(List<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        while(true) {
            synchronized (buffer) {
                if(buffer.isEmpty()) {
                    continue;
                }
                if(buffer.get(0).equals(EOF)) {
                    System.out.println(color + "Exiting!!");
                    break;
                } else {
                    System.out.println(color + "Removed " + buffer.remove(0));
                }
            }
        }
    }
}