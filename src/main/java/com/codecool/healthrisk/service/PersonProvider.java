package com.codecool.healthrisk.service;

import com.codecool.healthrisk.data.Gender;
import com.codecool.healthrisk.data.Person;

import java.util.Random;

public class PersonProvider {
    public static final String DateFormat = "d/M/yyyy";
    private static final Random Random = new Random();
    private final Person[] persons;

    public PersonProvider(int count) {
        this.persons = generateRandomPersons(count);
    }

    public Person[] getPersons() {
        return persons;
    }

    private static Person[] generateRandomPersons(int count) {
        int id = 0;
        Person[] persons = new Person[count];

        for (int i = 0; i < count; i++) {
            persons[i] = new Person(id++, getRandomBirthDate(), getRandomGender(), getRandomHeight(), getRandomWeights());
        }
        return persons;
    }

    private static String getRandomBirthDate() {
        int year = Random.nextInt(1950, 2000);
        int month = Random.nextInt(1, 12);
        int day = Random.nextInt(1, 25);

        return day + "/" + month + "/" + year;
    }

    private static double getRandomHeight() {
        return Random.nextDouble(1.5, 1.95);
    }

    private static int[] getRandomWeights() {
        int rangeStart = Random.nextInt(40, 110);
        int[] weights = new int[5];

        for (int i = 0; i < 5; i++) {
            weights[i] = Random.nextInt(rangeStart, rangeStart + 10);
        }
        return weights;
    }

    private static Gender getRandomGender() {
        double chance = Random.nextDouble(0, 1);
        if (chance > 0.5) {
            return Gender.MALE;
        }
        return Gender.FEMALE;
    }

}
