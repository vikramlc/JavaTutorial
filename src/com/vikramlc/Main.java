package com.vikramlc;

public class Main {
    static EagerSingleton eagerSingleton1 = EagerSingleton.getInstance();
    static EagerSingleton eagerSingleton2 = EagerSingleton.getInstance();

    public static void main(String[] args) {

        // At any moment of time, only one instance is created.
        System.out.println("First: " + eagerSingleton1.toString());
        System.out.println("Second: " + eagerSingleton2.toString());
    }
}