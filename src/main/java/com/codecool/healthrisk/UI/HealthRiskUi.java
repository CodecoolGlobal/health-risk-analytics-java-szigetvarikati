package com.codecool.healthrisk.UI;

import com.codecool.healthrisk.data.Gender;
import com.codecool.healthrisk.data.WeightCondition;
import com.codecool.healthrisk.service.AnalyticsService;
import com.codecool.healthrisk.data.Person;
import com.codecool.healthrisk.data.WeightCondition;

import java.util.Arrays;

public class HealthRiskUi {
    private AnalyticsService analyticsService;

    public HealthRiskUi(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    public void displayPersonAnalysis(Person person) {
        System.out.println("Id: " + person.getId());
        System.out.println("Gender: " + person.getGender());

        int age = analyticsService.calculateAge(person);
        System.out.println("Age: " + age);

        System.out.println("Weight series: " + person.getWeights());

        double[] bmiSeries = analyticsService.calculateBMISeries(person);
        System.out.println("BMI series: " + Arrays.toString(bmiSeries));

        WeightCondition weightCondition = analyticsService.determineWeightCondition(person);
        System.out.println("Weight condition: " + weightCondition);
    }

    public  void displayStatistics(Person[] people) {
        int totalCustomers = people.length;
        int overweightCustomers = 0;

        for (Person person : people) {
            WeightCondition weightCondition = analyticsService.determineWeightCondition(person);
            if (weightCondition == WeightCondition.OVERWEIGHT) {
                overweightCustomers++;
            }
        }

        double overweightRiskRatio = ((double) overweightCustomers / totalCustomers) *100;
        System.out.println("Overweight risk ratio: " + overweightRiskRatio + "%");
    }

    public static void main(String[] args) {
        Person person = new Person(1, "9/2/1985", Gender.FEMALE, 1.76, new int[] {115, 103, 97});
        Person[] people = {person};
        AnalyticsService analyticsService = new AnalyticsService();
        HealthRiskUi healthRiskUi = new HealthRiskUi(analyticsService);

        healthRiskUi.displayPersonAnalysis(person);
        healthRiskUi.displayStatistics(people);
    }
}
