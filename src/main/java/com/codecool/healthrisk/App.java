package com.codecool.healthrisk;

import com.codecool.healthrisk.data.Person;
import com.codecool.healthrisk.service.PersonProvider;

public class App {

    public static void main(String[] args) {
        PersonProvider personProvider = new PersonProvider(20);
        Person[] persons = personProvider.getPersons();

    }

}
