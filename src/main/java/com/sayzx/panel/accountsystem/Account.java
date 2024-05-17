package com.sayzx.panel.accountsystem;

public class Account {
    private String username;
    private String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }


    /**
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username, String password) {
        // Login system
        return this.username.equals(username) && this.password.equals(password);
    }

    public void logout() {
        // Logout
    }

    // Getters et setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
