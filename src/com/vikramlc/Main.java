package com.vikramlc;

public class Main {

    static LazySingleton lazySingleton1 = LazySingleton.getInstance();
    static LazySingleton lazySingleton2 = LazySingleton.getInstance();

    public static void main(String[] args) {
        System.out.println("First: " + lazySingleton1.toString());
        System.out.println("Second: " + lazySingleton2.toString());
    }




}