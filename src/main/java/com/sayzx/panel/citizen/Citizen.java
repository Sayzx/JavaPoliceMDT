package com.sayzx.panel.citizen;

import java.util.Date;

public class Citizen extends Person {
    private String address;

    /**
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param age
     * @param birthDate
     * @param birthPlace
     * @param address
     */
    public Citizen(int id, String firstName, String lastName, int age, Date birthDate, String birthPlace, String address) {
        super(id, firstName, lastName, age, birthDate, birthPlace);
        this.address = address;
    }

    @Override
    public String getDetails() {
        return "Citizen [ID=" + id + ", FirstName=" + firstName + ", LastName=" + lastName + ", Age=" + age +
                ", BirthDate=" + birthDate + ", BirthPlace=" + birthPlace + ", Address=" + address + "]";
    }


    /**
     * @return
     */
    public String getAddress() {
        return address;
    }


    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
