package com.sayzx.panel.locker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CriminalRecordSystem {
    private List<CriminalRecord> records = new ArrayList<>();
    private final String JSON_FILE_PATH = "src/main/java/com/sayzx/panel/locker/criminal_records.json";
    private final Gson gson = new GsonBuilder().create();

    public CriminalRecordSystem() {
        loadRecordsFromJson();
    }


    /**
     * Check if a record exists for a given user ID.
     * @param userId
     * @return
     */
    public boolean recordExists(int userId) {
        return records.stream().anyMatch(r -> r.getUserId() == userId);
    }


    /**
     * Add a new record to the system.
     * @param id The unique ID of the record.
     * @param userId The user ID associated with this record.
     * @param details The details of the new record.
     */
    public void addRecordToJson(int id, int userId, String details) {
        loadRecordsFromJson(); // Ensure existing records are loaded
        CriminalRecord record = records.stream()
                .filter(cr -> cr.getUserId() == userId)
                .findFirst()
                .orElse(new CriminalRecord(id, userId));
        record.addRecord(details);

        if (!records.contains(record)) {
            records.add(record);
        }
        saveRecordsToJson();
        System.out.println("Record added successfully for user ID: " + userId);
    }


    /**
     * Load records from JSON file.
     */
    public void loadRecordsFromJson() {
        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<CriminalRecord>>() {}.getType();
            records = gson.fromJson(reader, listType);
            if (records == null) {
                records = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("Error loading records from JSON file.");
            records = new ArrayList<>();
        }
    }


    /**
     * Save records to JSON file.
     */
    private void saveRecordsToJson() {
        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
            gson.toJson(records, writer);
        } catch (IOException e) {
            System.out.println("Error saving records to JSON file.");
        }
    }

    /**
        * Get all records in the system.
     * @return
     */
    public List<CriminalRecord> getRecords() {
        return records;
    }

    /**
     * Get a record by user ID.
     * @param userId The user ID to search for.
     */
    public void getRecordByUserId(int userId) {
        loadRecordsFromJson(); // Load the latest data from JSON
        CriminalRecord record = records.stream().filter(r -> r.getUserId() == userId).findFirst().orElse(null);
        if (record != null) {
            System.out.println(record);
        } else {
            System.out.println("Record not found.");
        }
    }


    /**
     * Show all records in the system.
     */
    public void showAllRecords() {
        loadRecordsFromJson(); // Load the latest data from JSON
        records.forEach(record -> System.out.println(record));
    }
}
