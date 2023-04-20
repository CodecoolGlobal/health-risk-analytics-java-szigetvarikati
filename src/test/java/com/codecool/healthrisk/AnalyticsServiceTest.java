package com.codecool.healthrisk;


import com.codecool.healthrisk.data.Gender;
import com.codecool.healthrisk.data.Person;
import com.codecool.healthrisk.data.WeightCondition;
import com.codecool.healthrisk.service.AnalyticsService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AnalyticsServiceTest {
    private static final int CURRENT_MONTH = LocalDate.now().getMonth().getValue();
    private static final int CURRENT_YEAR = LocalDate.now().getYear();

    private final AnalyticsService analyticsService = new AnalyticsService();

    @Test
    public void calculateAgeWhenCurrentTimeIsAfterBirthday() {
        int birthYear = 1992;
        int expected = CURRENT_YEAR - birthYear;
        String birthDate = "21/" + (CURRENT_MONTH - 1) + "/" + birthYear;

        int age = analyticsService.calculateAge(aPerson(birthDate));

        assertEquals(expected, age);
    }

    @Test
    public void calculateAgeWhenCurrentTimeIsBeforeBirthday() {
        int birthYear = 1992;
        int expected = CURRENT_YEAR - birthYear - 1;
        String birthDate = "21/" + (CURRENT_MONTH + 1) + "/" + birthYear;

        int age = analyticsService.calculateAge(aPerson(birthDate));

        assertEquals(expected, age);
    }

    @Test
    public void calculateBMISeriesReturnsArrayWithCorrectSize() {
        Person person = healthyPerson();

        double[] bmiSeries = analyticsService.calculateBMISeries(person);

        assertEquals(bmiSeries.length, person.getWeights().length);
    }

    @Test
    public void calculateBMISeriesReturnsCorrectValues() {
        int[] weights = new int[]{60, 61, 63, 65, 61};
        Person person = aPerson(weights);

        double[] expectedBmiSeries = Arrays.stream(weights)
                .mapToDouble(w -> (w / (Math.pow(person.getHeight(), 2))))
                .toArray();

        double[] bmiSeries = analyticsService.calculateBMISeries(person);

        assertArrayEquals(expectedBmiSeries, bmiSeries);
    }

    @Test
    public void determineWeightConditionReturnsOverWeight() {
        Person overWeightPerson = overweightPerson();

        WeightCondition weightCondition = analyticsService.determineWeightCondition(overWeightPerson);

        assertEquals(WeightCondition.OVERWEIGHT, weightCondition);
    }

    @Test
    public void determineWeightConditionReturnsHealthy() {
        Person healthyPerson = healthyPerson();

        WeightCondition weightCondition = analyticsService.determineWeightCondition(healthyPerson);

        assertEquals(WeightCondition.HEALTHY, weightCondition);
    }

    @Test
    public void determineWeightConditionReturnsUnknown() {
        Person person = aPerson(new int[]{60});

        WeightCondition weightCondition = analyticsService.determineWeightCondition(person);

        assertEquals(WeightCondition.UNKNOWN, weightCondition);
    }

    @Test
    public void calculateORRReturnsCorrectValue() {
        Person[] persons = new Person[]{
                overweightPerson(),
                healthyPerson(),
                overweightPerson(),
                healthyPerson()
        };

        double orr = analyticsService.calculateORR(persons);

        assertEquals(0.5, orr);
    }

    private Person overweightPerson() {

        return overweightPerson(Gender.MALE);
    }

    private Person overweightPerson(Gender gender) {
        return new Person(1, "21/7/1992", gender, 1.7, new int[]{100, 103, 110, 109, 108});
    }

    private Person healthyPerson() {
        return healthyPerson(Gender.MALE);
    }

    private Person healthyPerson(Gender gender) {
        return new Person(1, "21/7/1992", gender, 1.7, new int[]{60, 61, 63, 65, 61});
    }

    private Person aPerson(String birthDate) {
        return new Person(1, birthDate, Gender.MALE, 1.7, new int[]{60, 61, 63, 65, 61});
    }

    private Person aPerson(int[] weights) {

        return new Person(1, "21/7/1992", Gender.MALE, 1.7, weights);
    }
}
