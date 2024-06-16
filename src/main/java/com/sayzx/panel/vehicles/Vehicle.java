package com.sayzx.panel.vehicles;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

public class Vehicle {
    private int id;
    private String make;
    private String model;
    private String plate;
    private int year;

    /**
     * @param id
     * @param make
     * @param model
     * @param plate
     * @param year
     */
    public Vehicle(int id, String make, String model, String plate, int year) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.plate = plate;
        this.year = year;
    }

    /**
     * @return details in tabular format
     */
    public String getDetails() {
        return String.format(
                "----------VEHICLE #%d ------------\n" +
                        "%-10s %-15s %-15s %-10s %-5s\n" +
                        "%-10d %-15s %-15s %-10s %-5d",
                id, "ID", "Make", "Model", "Plate", "Year",
                id, make, model, plate, year);
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getPlate() {
        return plate;
    }
}
