package com.vikramlc;

public class Main {

    static StaticBlockSingleton staticBlockSingleton1 = StaticBlockSingleton.getInstance();
    static StaticBlockSingleton staticBlockSingleton2 = StaticBlockSingleton.getInstance();

    public static void main(String[] args) {
        System.out.println("First: " + staticBlockSingleton1.toString());
        System.out.println("Second: " + staticBlockSingleton2.toString());

        /*
        The above code has one drawback. Suppose there are 5 static fields in a class
        and the application code needs to access only 2 or 3, for which instance creation
        is not required at all. So, if we use this static initialization, we will have one
        instance created though it is required or not.
         */
    }




}