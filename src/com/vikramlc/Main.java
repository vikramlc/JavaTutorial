package com.vikramlc;

import java.io.*;

public class Main {
    static DemoSingleton instanceOne = DemoSingleton.getInstance();

    public static void main(String[] args) {
        try {
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream("filename.ser"));
            out.writeObject(instanceOne);
            out.close();

            instanceOne.setI(20);

            ObjectInput in = new ObjectInputStream(new FileInputStream("filename.ser"));
            DemoSingleton instanceTwo = (DemoSingleton) in.readObject();
            in.close();


            // Unfortunately, there are two instances of our class. So, again we are in the
            // same problem of multiple instances in our application.
            // To resolve this, we use readResolve.
            System.out.println(instanceOne.getI());
            System.out.println(instanceTwo.getI());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




}