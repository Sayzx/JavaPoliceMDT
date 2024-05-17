package com.sayzx.panel.casier;

public class CriminalRecord {
    private int id;
    private String details;
    private int userId;


    /**
     * @param id
     * @param details
     * @param userId
     */
    public CriminalRecord(int id, String details, int userId) {
        this.id = id;
        this.details = details;
        this.userId = userId;
    }

    /**
     * @param details
     */
    public void addRecord(String details) {
        this.details += details;
    }

    public void removeRecord() {
        this.details = "";
    }

    
    public String getDetails() {
        return "CriminalRecord [ID=" + id + ", Details=" + details + ", UserID=" + userId + "]";
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
