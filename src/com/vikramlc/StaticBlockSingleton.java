package com.vikramlc;

public class StaticBlockSingleton {
    private static final StaticBlockSingleton INSTANCE;

    static {
        try {
            INSTANCE = new StaticBlockSingleton();
        } catch(Exception e) {
            throw new RuntimeException("Not expecting this!", e);
        }
    }

    private StaticBlockSingleton() {

    }

    public static StaticBlockSingleton getInstance() {
        return INSTANCE;
    }
}
