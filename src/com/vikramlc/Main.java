package com.vikramlc;

import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();

        final PolitePerson ajay = new PolitePerson("deepa", lock);
        final PolitePerson deepa = new PolitePerson("ajay", lock);

        new Thread(new Runnable() {
            @Override
            public void run() {
                ajay.sayHello(deepa);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                deepa.sayHello(ajay);
            }
        }).start();
    }

    public static class PolitePerson {
        private String name;
        ReentrantLock lock;

        public PolitePerson(String name, ReentrantLock lock) {
            this.name = name;
            this.lock = lock;
        }

        public String getName() {
            return name;
        }

        public void sayHello(PolitePerson person) {

            lock.lock();
            System.out.format("%s: %s" + " has said Hello to me.%n", this.name, person.getName());
            person.sayHelloBack(this);
            lock.unlock();
        }

        public synchronized void sayHelloBack(PolitePerson person) {
            System.out.format("%s: %s" + " has said Hello back to me.%n", this.name, person.getName());
        }

    }

}