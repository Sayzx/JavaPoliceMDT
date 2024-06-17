package com.sayzx.panel.vehicles;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class VehicleSystem {
    private List<Vehicle> vehicles = new ArrayList<>();
    private final String JSON_FILE_PATH = "src/main/java/com/sayzx/panel/vehicles/vehicles.json";
    private final Gson gson = new GsonBuilder().create();

    public VehicleSystem() {
        loadVehiclesFromJson();
    }

    public boolean vehicleExists(String plate) {
        return vehicles.stream().anyMatch(v -> v.getPlate().equals(plate));
    }

    public void addVehicleToJson(int id, String make, String model, String plate, int year) {
        loadVehiclesFromJson(); // Ensure existing vehicles are loaded
        Vehicle newVehicle = new Vehicle(id, make, model, plate, year);
        vehicles.add(newVehicle);
        saveVehiclesToJson();
        System.out.println("Vehicle added successfully: " + make + " " + model + " with plate " + plate);
    }

    public void loadVehiclesFromJson() {
        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Vehicle>>() {}.getType();
            vehicles = gson.fromJson(reader, listType);
            if (vehicles == null) {
                vehicles = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("Error loading vehicles from JSON file.");
            vehicles = new ArrayList<>();
        }
    }

    private void saveVehiclesToJson() {
        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
            gson.toJson(vehicles, writer);
        } catch (IOException e) {
            System.out.println("Error saving vehicles to JSON file.");
        }
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void getVehicleById(int id) {
        loadVehiclesFromJson(); // Load the latest data from JSON
        Vehicle vehicle = vehicles.stream().filter(v -> v.getId() == id).findFirst().orElse(null);
        if (vehicle != null) {
            System.out.println(vehicle.getDetails());
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    public void showAllVehicles() {
        loadVehiclesFromJson(); // Load the latest data from JSON
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle.getDetails());
            }
        }
    }


}