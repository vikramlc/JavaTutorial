package com.vikramlc;

public class Main {

    public static void main(String[] args) {
        BankAccount account = new BankAccount("12345-678", 1000.00);

        new Thread(new Runnable() {
            @Override
            public void run() {
                account.deposit(300.00);
                account.withdraw(50.00);
                System.out.println("Thread 1: Account balance = " + account.getBalance());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                account.deposit(203.75);
                account.withdraw(100.00);
                System.out.println("Thread 2: Account balance = " + account.getBalance());
            }
        }).start();
    }

}