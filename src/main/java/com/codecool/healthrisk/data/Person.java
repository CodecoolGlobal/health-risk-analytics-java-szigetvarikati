package com.codecool.healthrisk.data;

import java.util.Arrays;

public class Person {
    private final int id;
    private final String birthDate;
    private final Gender gender;
    private final double height;
    private final int[] weights;

    public Person(int id, String birthDate, Gender gender, double height, int[] weights) {
        this.id = id;
        this.birthDate = birthDate;
        this.gender = gender;
        this.height = height;
        this.weights = weights;
    }

    public int getId() {return id;}

    public String getBirthDate() {
        return birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public double getHeight() {
        return height;
    }

    public int[] getWeights() {
        return weights;
    }

    @Override
    public String toString() {
        return "Person: " + birthDate + " " + gender + " " + String.format("%.1f", height) +
                " " + Arrays.toString(weights);
    }
}
