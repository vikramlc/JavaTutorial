package com.vikramlc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
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
        ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<String>(6);

        // Created a thread of 3 threads which will carry out the tasks.
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        MyProducer myProducer = new MyProducer(buffer, ThreadColor.ANSI_GREEN);
        MyConsumer myConsumer1 = new MyConsumer(buffer, ThreadColor.ANSI_PURPLE);
        MyConsumer myConsumer2 = new MyConsumer(buffer, ThreadColor.ANSI_CYAN);

        executorService.execute(myProducer);
        executorService.execute(myConsumer1);
        executorService.execute(myConsumer2);

        // To get something back from the execution of the tasks by the threads, we use Callable and Runnable methods.
        Future<String> future = executorService.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                System.out.println(ThreadColor.ANSI_BLUE + "Being printed for the Callable class.");
                return "This is a callable result.";
            }
        });

        try {
            System.out.println("Future value: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // Always shutdown the service else the thread is still running in the background.
        executorService.shutdown();
    }
}

class MyProducer implements Runnable{
    private ArrayBlockingQueue<String> buffer;
    private String color;

    public MyProducer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        String[] nums = {"1", "2", "3", "4", "5", "6"};
        Random random = new Random();

        for(String num: nums) {
            try {
                System.out.println(color + "Adding " + num);
                buffer.put(num);

                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println("Producer was interrupted.");
            }
        }

        System.out.println(color + "Adding EOF and Exiting!!");

        try {
            buffer.put("EOF");
        } catch(InterruptedException e) {
            System.out.println("The thread is interrupted.");
        }

    }
}

class MyConsumer implements Runnable {
    private ArrayBlockingQueue<String> buffer;
    private String color;

    public MyConsumer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {

        while(true) {
            synchronized (buffer) {
                try {
                    if(buffer.isEmpty()) {
                        continue;
                    }

                    if(buffer.peek().equals(EOF)) {
                        System.out.println(color + "Exiting!!");
                        break;
                    } else {
                        System.out.println(color + "Removed " + buffer.take());
                    }
                } catch (InterruptedException e) {
                    System.out.println("Interrupted!");
                }
            }
        }
    }
}