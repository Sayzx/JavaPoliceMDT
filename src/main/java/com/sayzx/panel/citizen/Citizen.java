package com.sayzx.panel.citizen;

import java.time.LocalDate;

public class Citizen extends Person {
    private String address;

    /**
     *
     * @param id Citizen ID
     * @param firstName Citizen First Name
     * @param lastName Citizen Last Name
     * @param birthDate Citizen Birth Date;
     * @param birthPlace Citizen BirthPlace
     * @param address Citizen Address
     */
    public Citizen(int id, String firstName, String lastName, LocalDate birthDate, String birthPlace, String address) {
        super(id, firstName, lastName, birthDate, birthPlace);
        this.address = address;
    }


    @Override
    public String toString() {
        return String.format(
                "----------CITIZENS------------" +
                "Citizen Details:\n" +
                        "ID:          %d\n" +
                        "First Name:  %s\n" +
                        "Last Name:   %s\n" +
                        "Birth Date:  %s\n" +
                        "Birth Place: %s\n" +
                        "Address:     %s\n" ,
                id, firstName, lastName, birthDate, birthPlace, address
        );
    }
    public int getId() {
        return id;
    }

    public Object getFirstName() {
        return firstName;
    }

    public Object getLastName() {
        return lastName;
    }
}
