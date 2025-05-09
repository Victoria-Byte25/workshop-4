package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DealershipFileManager {

    // File path
    private final String FILE_NAME = "src/main/resources/inventory.csv";

    // Reads the file and builds the dealership + inventory
    public Dealership getDealership() {
        Dealership dealership = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            // Read the first line
            String dealershipInfo = reader.readLine();

            if (dealershipInfo != null) {
                String[] infoParts = dealershipInfo.split("\\|");
                String name = infoParts[0];
                String address = infoParts[1];
                String phone = infoParts[2];

                // Create the dealership object using the info
                dealership = new Dealership(name, address, phone);

                // Read the rest of the lines (the vehicles)
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");

                    // Vehicle info
                    int vin = Integer.parseInt(parts[0]);
                    int year = Integer.parseInt(parts[1]);
                    String make = parts[2];
                    String model = parts[3];
                    String type = parts[4];
                    String color = parts[5];
                    int odometer = Integer.parseInt(parts[6]);
                    double price = Double.parseDouble(parts[7]);

                    // Vehicle and the inventory
                    Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                    dealership.addVehicle(vehicle);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        return dealership;
    }


    public void saveDealership(Dealership dealership) {

    }
}

