package com.sayzx.panel.accountsystem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SysSystem {
    private static List<Account> accounts = new ArrayList<>();
    private static final String JSON_FILE_PATH = "src/main/java/com/sayzx/panel/accountsystem/data/accounts.json";
    private static final Gson gson = new Gson();

    public SysSystem() {
        loadAccountsFromJson();
    }


    /**
     * @param username
     * @param password
     * @return
     */
    public void createAccount(String username, String password) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                System.out.println("Account already exists.");
                return;
            }
        }
        Account newAccount = new Account(username, password);
        accounts.add(newAccount);
        saveAccountsToJson();
        System.out.println("Account created successfully. Username: " + username);
    }

    public void logout() {
        System.out.println("Logged out successfully.");
    }


    // This method load a json file containing accounts
    public static void loadAccountsFromJson() {
        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Account>>() {}.getType();
            accounts = gson.fromJson(reader, listType);
            if (accounts == null) {
                accounts = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("Error loading accounts from JSON file.");
            accounts = new ArrayList<>();
        }
    }

    // This method is not used in the current implementation
    public static List<Account> getAccounts() {
        return accounts;
    }



    // This method is not used in the current implementation
    private static void saveAccountsToJson() {
        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
            gson.toJson(accounts, writer);
        } catch (IOException e) {
            System.out.println("Error saving accounts to JSON file.");
        }
    }
}