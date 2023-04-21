package com.codecool.healthrisk;

import com.codecool.healthrisk.UI.HealthRiskUi;
import com.codecool.healthrisk.data.Person;
import com.codecool.healthrisk.service.AnalyticsService;
import com.codecool.healthrisk.service.PersonProvider;

public class App {

    public static void main(String[] args) {
        PersonProvider personProvider = new PersonProvider(20);
        Person[] persons = personProvider.getPersons();

        AnalyticsService analyticsService = new AnalyticsService();
        HealthRiskUi healthRiskUi = new HealthRiskUi(analyticsService);

        for (Person person : persons) {
            healthRiskUi.displayPersonAnalysis(person);
        }
        healthRiskUi.displayStatistics(persons);
    }

}
