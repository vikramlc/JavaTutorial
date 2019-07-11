package com.vikramlc;

import java.io.Serializable;

public class DemoSingleton implements Serializable {

    private static final long serialVersionUID = 1L;

    private volatile static DemoSingleton instance = null;

    private DemoSingleton() {

    }

    public static DemoSingleton getInstance() {
        if(instance == null) {
            instance = new DemoSingleton();
        }
        return instance;
    }

    // There were two instances of the object created, each when serialized and deserialized.
    // To avoid this, we need to include a readResolve() method in our DemoSingleton class.
    // This method will be invoked when you will de-serialize the object.
    // Inside of this method, you must return the existing instance to ensure a single instance application wide.
    protected Object readResolve() {
        return instance;
    }

    private int i = 10;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
