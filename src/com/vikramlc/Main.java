package com.vikramlc;

public class Main {

    public static void main(String[] args) {
        final PolitePerson ajay = new PolitePerson("deepa");
        final PolitePerson deepa = new PolitePerson("ajay");

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

        public PolitePerson(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public synchronized void sayHello(PolitePerson person) {
            System.out.format("%s: %s" + " has said Hello to me.%n", this.name, person.getName());
            person.sayHelloBack(this);
        }

        public synchronized void sayHelloBack(PolitePerson person) {
            System.out.format("%s: %s" + " has said Hello back to me.%n", this.name, person.getName());
        }

    }

}