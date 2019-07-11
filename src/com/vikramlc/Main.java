package com.vikramlc;

public class Main {

    static BillPughSingleton billPughSingleton1 = BillPughSingleton.getInstance();
    static BillPughSingleton billPughSingleton2 = BillPughSingleton.getInstance();

    public static void main(String[] args) {
        System.out.println("First: " + billPughSingleton1.toString());
        System.out.println("Second: " + billPughSingleton2.toString());

        /*
         As you can see, until we need an instance, the LazyHolder class will not be
         initialized until required and you can still use other static members of
         BillPughSingleton class. This is the solution, i will recommend to use.
         I have used it in my all projects.
         */


    }




}