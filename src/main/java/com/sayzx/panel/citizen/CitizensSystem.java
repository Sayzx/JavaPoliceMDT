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
    private static List<Citizen> citizens = new ArrayList<>();
    private static final String JSON_FILE_PATH = "src/main/java/com/sayzx/panel/citizen/citizens.json";
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    public CitizensSystem() {
        loadCitizensFromJson();
    }

    public static boolean citizenExists(String firstName, String lastName) {
        return citizens.stream().anyMatch(c -> c.getFirstName().equals(firstName) && c.getLastName().equals(lastName));
    }

    public static void addCitizenToJson(int id, String firstName, String lastName, LocalDate birthDate, String birthPlace, String address) {
        loadCitizensFromJson(); // Ensure existing citizens are loaded
        Citizen newCitizen = new Citizen(id, firstName, lastName, birthDate, birthPlace, address);
        citizens.add(newCitizen);
        saveCitizensToJson();
        System.out.println("Citizen added successfully: " + firstName + " " + lastName);
    }

    public static void loadCitizensFromJson() {
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

    private static void saveCitizensToJson() {
        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
            gson.toJson(citizens, writer);
        } catch (IOException e) {
            System.out.println("Error saving citizens to JSON file.");
        }
    }

    public static List<Citizen> getCitizens() {
        return citizens;
    }

    public static boolean idExists(int id) {
        return citizens.stream().anyMatch(c -> c.getId() == id);
    }

    public static Citizen getCitizenById(int id) {
        loadCitizensFromJson(); // Load the latest data from JSON
        Citizen citizen = citizens.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (citizen != null) {
            System.out.println(citizen);
        } else {
            System.out.println("Citizen not found.");
        }
        return citizen;
    }


    public static int getNumberOfCitizens() {
        return citizens.size();
    }
}
