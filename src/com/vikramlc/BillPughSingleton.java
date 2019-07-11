package com.vikramlc;

public class BillPughSingleton {
    private BillPughSingleton() {

    }

    private static class BillPughSingleTonDemo {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return BillPughSingleTonDemo.INSTANCE;
    }
}
