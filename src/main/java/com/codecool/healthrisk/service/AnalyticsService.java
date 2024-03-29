package com.codecool.healthrisk.service;

import com.codecool.healthrisk.data.Person;
import com.codecool.healthrisk.data.WeightCondition;

import java.time.LocalDate;

public class AnalyticsService {

    public static final double OVERWEIGHT_BMI_LIMIT = 25.0;
    public static final int WEIGHT_CONDITION_YEARS_LIMIT = 3;

    public int calculateAge(Person person) {
        String birthDateStr = person.getBirthDate();

        String[] parts = birthDateStr.split("/");
        int birthDay = Integer.parseInt(parts[0]);
        int birthMonth = Integer.parseInt(parts[1]);
        int birthYear = Integer.parseInt(parts[2]);

        LocalDate currentDate = LocalDate.now();

        int age = currentDate.getYear() - birthYear;
        if (currentDate.getMonthValue() < birthMonth ||
                (currentDate.getMonthValue() == birthMonth && currentDate.getDayOfMonth() < birthDay)) {
           age--;
        }
        return age;
    }

    public double[] calculateBMISeries(Person person) {
        int[] weights = person.getWeights();
        double heightInMeters = person.getHeight();
        double[] BMIs = new double[weights.length];

        for (int i= 0; i < weights.length; i++) {
            double weightInKg = weights[i];
            double BMI = weightInKg / (heightInMeters*heightInMeters);
            BMIs[i] = BMI;
        }
        return BMIs;
    }

    public WeightCondition determineWeightCondition(Person person) {
        double[] bmiSeries = calculateBMISeries(person);
        int numYears = bmiSeries.length;

        if (numYears < 3) {
            return WeightCondition.UNKNOWN;
        }
        for (int i = 0; i<3; i++) {
            if (bmiSeries[i] <= 25) {
                return WeightCondition.HEALTHY;
            }
        }
        return WeightCondition.OVERWEIGHT;
    }

    public double calculateORR(Person[] persons) {
        int atRiskCount = 0;
        for (Person person: persons) {
            WeightCondition weightCondition = determineWeightCondition(person);
            if (weightCondition == WeightCondition.OVERWEIGHT) {
                atRiskCount++;
            }
        }
        return (double) atRiskCount / persons.length;
    }
}
