package com.sayzx.panel;

import com.sayzx.panel.accountsystem.SysSystem;
import com.sayzx.panel.casier.*;
import com.sayzx.panel.citizen.*;
import com.sayzx.panel.vehicles.*;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Created a system object and Create a new account
        SysSystem system = new SysSystem();
        system.createAccount("john_doe", "password123");

        // if user login with correct credentials then create a citizen, vehicle and criminal record
        if (system.login("john_doe", "password123")) {
            System.out.println("Login successful!");

            // Create a citizen object
            Citizen citizen = new Citizen(1, "John", "Doe", 30, new Date(), "City", "123 Main St");
            System.out.println(citizen.getDetails());

            // Create a vehicle object
            Vehicle vehicle = new Vehicle(1, "Toyota", "Corolla", "XYZ123", 2020);
            System.out.println(vehicle.getDetails());

            // Create a criminal record object
            CriminalRecord record = new CriminalRecord(1, "Theft", 1);
            CriminalRecord record2 = new CriminalRecord(2, "Dev", 2);
            record.addRecord(", Assault");
            record.addRecord(", Dev");
            record2.addRecord("Pas Genial");
            System.out.println(record.getDetails());
            System.out.println(record2.getDetails());

            // if users finish a task then logout
            system.logout();
        } else {
            System.out.println("Login failed!");
        }
    }
}
