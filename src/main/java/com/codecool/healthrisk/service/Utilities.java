package com.codecool.healthrisk.service;

public class Utilities {

    public static double roundToTwoDecimals(double number) {
        return Math.round(number * 100.0) / 100.0;
    }
}
