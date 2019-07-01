package com.vikramlc;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Message message = new Message();

        new Thread(new Writer(message)).start();
        new Thread(new Reader(message)).start();
    }
}

class Message {
    private String message;
    private boolean empty=true;

    public synchronized String read() {
        while(empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        empty=true;
        notifyAll();
        return message;
    }

    public synchronized void write(String message) {
        while(!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        empty=false;
        notifyAll();
        this.message=message;
    }
}

class Writer implements Runnable {
    private Message message;

    public Writer(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        String messages[] = {
                "Test1",
                "Test2",
                "Test3",
                "Test4",
                "Test5"
        };

        Random random = new Random();

        for(int i=0; i<messages.length; i++) {
            message.write(messages[i]);
        }

        try {
            Thread.sleep(random.nextInt(2000));
        } catch(InterruptedException e) {

        }
        message.write("Finished Reading");
    }
}

class Reader implements Runnable {
    private Message message;

    public Reader(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        Random random = new Random();

        for(String lastMessage=message.read(); lastMessage!="Finished Reading"; lastMessage=message.read()) {
            System.out.println(lastMessage);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch(InterruptedException e) {

            }
        }
    }
}