package com.sayzx.panel.vehicles;

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
     * @return
     */
    public String getDetails() {
        return "Vehicle [ID=" + id + ", Make=" + make + ", Model=" + model + ", Plate=" + plate + ", Year=" + year + "]";
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
