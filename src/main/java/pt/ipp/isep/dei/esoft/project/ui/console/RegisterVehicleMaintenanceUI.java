package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleMaintenanceController;
import pt.ipp.isep.dei.esoft.project.domain.Maintenance;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.List;
import java.util.Scanner;

/**
 * The RegisterVehicleMaintenanceUI class represents a user interface component responsible for
 * interacting with the system to addSkill new maintenances. It uses a RegisterVehicleMaintenanceController
 * to handle the maintenance registration.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class RegisterVehicleMaintenanceUI implements Runnable {

    private final RegisterVehicleMaintenanceController controller;
    private List<Vehicle> vehicleList;

    /**
     * Constructs a new RegisterVehicleMaintenanceUI object and initializes it with a
     * RegisterVehicleMaintenanceController instance for handling vehicle maintenance registration operations.
     */
    public RegisterVehicleMaintenanceUI() {
        this.controller = new RegisterVehicleMaintenanceController();
    }

    /**
     * Lets the UI get the controller
     *
     * @return controller
     */
    private RegisterVehicleMaintenanceController getController() {
        return controller;
    }

    @Override
    public void run() {
        System.out.println("\n\n--- Register Vehicle Maintenance ------------------------");

        vehicleList = displayAndSelectVehicles();

        submitData();
    }

    /**
     * Shows the user all the vehicles that were registered successfully.
     *
     */
    private void submitData() {
        List<Maintenance> maintenances = getController().registerVehicleMaintenance(vehicleList);

        if (!maintenances.isEmpty()) {
            System.out.println("\nVehicle maintenance successfully registered for the following vehicles:");
            for (Maintenance maintenance : maintenances) {
                System.out.println("- Vehicle: " + maintenance.getVehicle().getPlateNumber());
            }
        } else {
            System.out.println("\nNo vehicle maintenance was registered!");
        }
    }


    /**
     * This function will display a list of all vehicles registered in the system
     * and then will ask the user to select which vehicles would he want to addSkill for maintenance
     *
     * @return vehicles (All selected for maintenance)
     */

    private List<Vehicle> displayAndSelectVehicles() {
        List<Vehicle> vehicles = controller.getVehicleList();
        if (vehicleList!= null){
            int listSize = vehicleList.size();

            Scanner input = new Scanner(System.in);
            int answer = -1;
            System.out.print("If you want to stop selecting vehicles, press any number smaller than 0 or higher than" + listSize + ".");

            while (answer != 1) {
                displayVehiclesOptions(vehicleList);
                System.out.print("Select a vehicle: ");

                answer = input.nextInt();
                if (answer < 1 || answer > listSize) {
                    vehicles.add(vehicleList.get(answer - 1));
                }
            }
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
