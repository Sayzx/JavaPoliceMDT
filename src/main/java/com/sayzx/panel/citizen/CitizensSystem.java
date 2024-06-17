package com.sayzx.panel.citizen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CitizensSystem {
    private List<Citizen> citizens = new ArrayList<>();
    private final String JSON_FILE_PATH = "src/main/java/com/sayzx/panel/citizen/citizens.json";
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    public CitizensSystem() {
        loadCitizensFromJson();
    }

    public boolean citizenExists(String firstName, String lastName) {
        return citizens.stream().anyMatch(c -> c.getFirstName().equals(firstName) && c.getLastName().equals(lastName));
    }

    public void addCitizenToJson(int id, String firstName, String lastName, LocalDate birthDate, String birthPlace, String address) {
        loadCitizensFromJson(); // Ensure existing citizens are loaded
        Citizen newCitizen = new Citizen(id, firstName, lastName, birthDate, birthPlace, address);
        citizens.add(newCitizen);
        saveCitizensToJson();
        System.out.println("Citizen added successfully: " + firstName + " " + lastName);
    }

    public void loadCitizensFromJson() {
        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Citizen>>() {}.getType();
            citizens = gson.fromJson(reader, listType);
            if (citizens == null) {
                citizens = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("Error loading citizens from JSON file.");
            citizens = new ArrayList<>();
        }
    }

    private void saveCitizensToJson() {
        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
            gson.toJson(citizens, writer);
        } catch (IOException e) {
            System.out.println("Error saving citizens to JSON file.");
        }
    }

    public List<Citizen> getCitizens() {
        return citizens;
    }

    public Citizen getCitizenById(int id) {
        loadCitizensFromJson(); // Load the latest data from JSON
        Citizen citizen = citizens.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (citizen != null) {
            System.out.println(citizen);
        } else {
            System.out.println("Citizen not found.");
        }
        return citizen;
    }

    public void showAllCitizens() {
        loadCitizensFromJson(); // Load the latest data from JSON
        for (Citizen citizen : citizens) {
            System.out.println(citizen);
        }
    }

    public void removeCitizenById(int idCitizen) {
        loadCitizensFromJson(); // Load the latest data from JSON
        Citizen citizen = citizens.stream().filter(c -> c.getId() == idCitizen).findFirst().orElse(null);
        if (citizen != null) {
            citizens.remove(citizen);
            saveCitizensToJson();
            System.out.println("Citizen removed successfully: " + citizen.getFirstName() + " " + citizen.getLastName());
        } else {
            System.out.println("Citizen not found.");
        }
    }
}
