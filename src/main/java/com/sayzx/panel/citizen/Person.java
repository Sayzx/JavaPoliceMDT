package com.sayzx.panel.citizen;

import java.time.LocalDate;

public abstract class Person {
    protected int id;
    protected String firstName;
    protected String lastName;
    protected int age;
    protected LocalDate birthDate;
    protected String birthPlace;


    /**
     * @param id
     * @param firstName
     * @param lastName
     * @param birthDate
     * @param birthPlace
     */
    public Person(int id, String firstName, String lastName, LocalDate birthDate, String birthPlace) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
    }

    public abstract String toString();
}
