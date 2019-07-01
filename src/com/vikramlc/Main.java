package com.vikramlc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.vikramlc.Main.EOF;

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
            buffer.add(num);

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println("Producer was interrupted.");
            }
        }

        System.out.println(color + "Adding EOF and Exiting!!");
        buffer.add("EOF");

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