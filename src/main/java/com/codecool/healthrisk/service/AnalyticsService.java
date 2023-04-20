package com.codecool.healthrisk.service;

import com.codecool.healthrisk.data.Person;
import com.codecool.healthrisk.data.WeightCondition;

public class AnalyticsService {

    public static final double OVERWEIGHT_BMI_LIMIT = 25.0;
    public static final int WEIGHT_CONDITION_YEARS_LIMIT = 3;

    public int calculateAge(Person person) {
        return 0;
    }

    public double[] calculateBMISeries(Person person) {
        return new double[0];
    }

    public WeightCondition determineWeightCondition(Person person) {
        return null;
    }

    public double calculateORR(Person[] persons) {
        return 0;
    }
}
