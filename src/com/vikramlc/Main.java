package com.vikramlc;

public class Main {

    public static void main(String[] args) {
        System.out.println("Result: " + toMilesPerHour(10.5));
    }

    public static long toMilesPerHour(double kiloMetersPerHour) {
        if(kiloMetersPerHour < 0) {
            return -1;
        }

        return Math.round(kiloMetersPerHour/1.609);
    }


}