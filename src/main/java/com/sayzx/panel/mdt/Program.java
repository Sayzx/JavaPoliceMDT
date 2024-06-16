package com.sayzx.panel.mdt;

import com.sayzx.panel.accountsystem.Account;
import com.sayzx.panel.accountsystem.SysSystem;
import com.sayzx.panel.locker.CriminalRecordSystem;
import com.sayzx.panel.citizen.Citizen;
import com.sayzx.panel.citizen.CitizensSystem;
import com.sayzx.panel.vehicles.VehicleSystem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Program {
    private static final SysSystem system = new SysSystem();
    private static boolean isLoggedIn = false;
    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {
        int choice;
        do {
            System.out.println("Select an option:");
            System.out.println("[1] Account Management");
            System.out.println("[2] Citizen Management");
            System.out.println("[3] Vehicle Management");
            System.out.println("[4] Infraction Management");
            System.out.println("[0] Exit");

            while (true) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice >= 0 && choice <= 4) {
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter a number between 0 and 4.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
            }

            switch (choice) {
                case 1:
                    manageAccounts();
                    break;
                case 2:
                    manageCitizens();
                    break;
                case 3:
                    manageVehicles();
                    break;
                case 4:
                    infractionsManagement();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
            }
        } while (choice != 0);
    }

    private static void infractionsManagement() {
        if (!isLoggedIn) {
            System.out.println("You must be logged in to manage infractions.");
            return;
        }

        System.out.println("Infractions Management:");
        System.out.println("[1] Add Infraction on citizen (If citizen not created, it is not possible to add infraction)");
        System.out.println("[2] View All Infractions");
        System.out.println("[3] View Infractions by Citizen ID");

        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 3) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        switch (choice) {
            case 1:
                addInfraction();
                break;
            case 2:
                viewAllInfractions();
                break;
            case 3:
                viewInfractionsByCitizenId();
                break;
        }
    }

    private static void addInfraction() {
        System.out.println("Enter citizen ID:");
        int idCitizen = Integer.parseInt(scanner.nextLine());
        Citizen citizen = CitizensSystem.getCitizenById(idCitizen);

        if (citizen == null) {
            System.out.println("Citizen not found. Cannot add infraction.");
            return;
        }

        System.out.println("Enter infraction details:");
        String infractionDetails = scanner.nextLine();

        int recordId = CriminalRecordSystem.getRecords().size() + 1;
        CriminalRecordSystem.addRecordToJson(recordId, idCitizen, infractionDetails);

        System.out.println("Infraction added successfully.");
    }

    private static void viewAllInfractions() {
        CriminalRecordSystem.showAllRecords();
    }

    private static void viewInfractionsByCitizenId() {
        System.out.println("Enter citizen ID:");
        int citizenId = Integer.parseInt(scanner.nextLine());
        CriminalRecordSystem.getRecordByUserId(citizenId);
    }

    private static void manageAccounts() {
        System.out.println("Account Management:");
        System.out.println("[1] Create Account");
        System.out.println("[2] Login");
        System.out.println("[3] Logout");

        int accountChoice;
        while (true) {
            try {
                accountChoice = Integer.parseInt(scanner.nextLine());
                if (accountChoice >= 1 && accountChoice <= 3) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        switch (accountChoice) {
            case 1:
                System.out.println("Enter username:");
                String username = scanner.nextLine();
                System.out.println("Enter password:");
                String password = scanner.nextLine();
                system.createAccount(username, password);
                break;
            case 2:
                System.out.println("Enter username:");
                String loginUsername = scanner.nextLine();
                System.out.println("Enter password:");
                String loginPassword = scanner.nextLine();
                if (Account.login(loginUsername, loginPassword)) {
                    isLoggedIn = true;
                    System.out.println("Login successful!");
                } else {
                    System.out.println("Login failed!");
                }
                break;
            case 3:
                if (isLoggedIn) {
                    system.logout();
                    isLoggedIn = false;
                    System.out.println("Logout successful!");
                } else {
                    System.out.println("You are not logged in.");
                }
                break;
        }
    }

    private static void manageCitizens() {
        if (!isLoggedIn) {
            System.out.println("You must be logged in to manage citizens.");
            return;
        }

        System.out.println("Citizen Management:");
        System.out.println("[1] Add Citizen");
        System.out.println("[2] View Citizen Details");

        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 2) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        switch (choice) {
            case 1:
                addCitizen();
                break;
            case 2:
                viewCitizenById();
                break;
        }
    }

    private static void addCitizen() {
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();

        while (CitizensSystem.citizenExists(firstName, lastName)) {
            System.out.println("Citizen with the same first name and last name already exists. Please enter a different first name:");
            firstName = scanner.nextLine();
            System.out.println("Please enter a different last name:");
            lastName = scanner.nextLine();
        }

        System.out.println("Enter date of birth (yyyy-mm-dd):");
        String dobStr = scanner.nextLine();
        // Validate that the date of birth is in the correct format with regex
        while (!dobStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Invalid date format. Please enter a valid date of birth (yyyy-mm-dd):");
            dobStr = scanner.nextLine();
        }
        // Validate that the date of birth is a valid date
        while (dobStr.charAt(5) > '1' || dobStr.charAt(8) > '3' || dobStr.charAt(0) > '2' || dobStr.charAt(1) > '0' || dobStr.charAt(5) == '1' && dobStr.charAt(6) > '2' || dobStr.charAt(8) == '3' && dobStr.charAt(9) > '1') {
            System.out.println("Invalid date format. Please enter a valid date of birth (yyyy-mm-dd):");
            dobStr = scanner.nextLine();
        }
        LocalDate dob = LocalDate.parse(dobStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println("Enter birth place:");
        String birthPlace = scanner.nextLine();
        System.out.println("Enter address:");
        String address = scanner.nextLine();
        int id = CitizensSystem.getCitizens().size() + 1;
        CitizensSystem.addCitizenToJson(id, firstName, lastName, dob, birthPlace, address);
        System.out.println("Citizen added: " + firstName + " " + lastName);
    }

    private static void viewCitizenById() {
        System.out.println("Enter citizen ID:");
        int idCitizen = Integer.parseInt(scanner.nextLine());
        CitizensSystem.getCitizenById(idCitizen);
    }

    private static void manageVehicles() {
        if (!isLoggedIn) {
            System.out.println("You must be logged in to manage vehicles.");
            return;
        }

        System.out.println("Vehicle Management:");
        System.out.println("[1] Add Vehicle");
        System.out.println("[2] View All Vehicles");
        System.out.println("[3] View Vehicle Details by ID");

        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 3) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        switch (choice) {
            case 1:
                addVehicle();
                break;
            case 2:
                System.out.println("Showing all vehicles:");
                VehicleSystem.showAllVehicles();
                break;
            case 3:
                System.out.println("Enter vehicle ID:");
                int idVehicle = Integer.parseInt(scanner.nextLine());
                VehicleSystem.getVehicleById(idVehicle);
                break;
        }
    }

    private static void addVehicle() {
        int vehicleId = VehicleSystem.getVehicles().size() + 1;
        System.out.println("Enter make:");
        String make = scanner.nextLine();
        // Validate that make is a string
        while (!make.matches("[a-zA-Z]+")) {
            System.out.println("Invalid input. Please enter a valid make:");
            make = scanner.nextLine();
        }
        System.out.println("Enter model:");
        String model = scanner.nextLine();
        // Validate that model is a string
        while (!model.matches("[a-zA-Z]+")) {
            System.out.println("Invalid input. Please enter a valid model:");
            model = scanner.nextLine();
        }
        System.out.println("Enter license plate:");
        String plate = scanner.nextLine();
        // Validate that the plate is unique and not too long
        while (VehicleSystem.vehicleExists(plate) || plate.length() > 7) {
            System.out.println("Vehicle with the same plate already exists or plate is too long. Please enter a different plate:");
            plate = scanner.nextLine();
        }

        System.out.println("Enter year:");
        int year;
        // Validate that the year is a number and within a valid range
        while (true) {
            try {
                year = Integer.parseInt(scanner.nextLine());
                if (year >= 1900 && year <= LocalDate.now().getYear()) {
                    break;
                } else {
                    System.out.println("Invalid year. Please enter a valid year:");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid year:");
            }
        }
        VehicleSystem.addVehicleToJson(vehicleId, make, model, plate, year);
        System.out.println("Vehicle added: " + make + " " + model);
    }
}
