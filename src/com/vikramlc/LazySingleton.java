package com.vikramlc;

public class LazySingleton {
    private static LazySingleton instance = null;

    private LazySingleton() {

    }

    // Instantiated when invoked
    // Drawback: Can create two instances when 2 threads are used.
    public static LazySingleton getInstance() {
        if(instance == null) {
            synchronized (LazySingleton.class) {
                instance = new LazySingleton();
            }
        }

        return instance;
    }

}
