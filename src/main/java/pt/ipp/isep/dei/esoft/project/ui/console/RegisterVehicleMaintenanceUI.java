package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleMaintenanceController;
import pt.ipp.isep.dei.esoft.project.domain.Maintenance;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegisterVehicleMaintenanceUI implements Runnable {
    private final RegisterVehicleMaintenanceController controller;
    private List<Vehicle> vehicleList;

    public RegisterVehicleMaintenanceUI() {
        this.controller = new RegisterVehicleMaintenanceController();
    }


    private RegisterVehicleMaintenanceController getController() {
        return controller;
    }

    @Override
    public void run() {
        System.out.println("\n\n--- Register Vehicle Maintenance ------------------------");

        vehicleList = displayAndSelectVehicles();

        submitData();
    }

    private void submitData() {
        List<Maintenance> maintenances = getController().registerVehicleMaintenance(vehicleList);

        if (!maintenances.isEmpty()) {
            System.out.println("\nVehicle maintenance successfully registered for the following vehicles:");
            for (Maintenance maintenance : maintenances) {
                System.out.println("- Vehicle: " + maintenance.getPlateNumber());
            }
        } else {
            System.out.println("\nNo vehicle maintenance was registered!");
        }
    }

    public List<Vehicle> displayAndSelectVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        List<Vehicle> vehicleList = new ArrayList<>(controller.getVehicleList()); // Create a mutable copy of the list

        if (!vehicleList.isEmpty()) {
            Scanner input = new Scanner(System.in);
            String answer = "";

            while (!answer.equalsIgnoreCase("done")) {
                displayVehiclesOptions(vehicleList);
                System.out.print("Select a vehicle by number or type 'done' to finish: ");
                answer = input.nextLine().trim();

                if (answer.equalsIgnoreCase("done")) {
                    break;
                }

                try {
                    int choice = Integer.parseInt(answer);
                    if (choice >= 1 && choice <= vehicleList.size()) {
                        vehicles.add(vehicleList.get(choice - 1));
                        System.out.println("Vehicle selected successfully!");
                        vehicleList.remove(choice - 1);
                    } else {
                        System.out.println("Invalid selection. Please select a number between 1 and " + vehicleList.size() + ".");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number or 'done' to finish.");
                }
            }
        } else {
            System.out.println("No vehicles available to select.");
        }
        return vehicles;
    }

    private void displayVehiclesOptions(List<Vehicle> vehicleList) {
        int i = 1;
        for (Vehicle vehicle : vehicleList) {
            System.out.println("  " + i + " - " + vehicle.getPlateNumber());
            i++;
        }
    }
}
