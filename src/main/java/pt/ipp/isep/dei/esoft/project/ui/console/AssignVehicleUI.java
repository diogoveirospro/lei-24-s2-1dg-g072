package pt.ipp.isep.dei.esoft.project.ui.console;


import pt.ipp.isep.dei.esoft.project.application.controller.AssignVehicleController;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.List;
import java.util.Scanner;

public class AssignVehicleUI {
    private final AssignVehicleController controller;

    public AssignVehicleUI() {
        this.controller = new AssignVehicleController();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Assign Vehicle to Agenda Entry");
        System.out.println("==============================");


        List<AgendaEntry> agendaEntries = controller.getAgendaEntries();
        System.out.println("Agenda Entries:");
        for (int i = 0; i < agendaEntries.size(); i++) {
            System.out.println((i + 1) + ". " + agendaEntries.get(i).getName());
        }


        System.out.print("Select an agenda entry (1-" + agendaEntries.size() + "): ");
        int entryIndex = Integer.parseInt(scanner.nextLine()) - 1;
        AgendaEntry selectedEntry = agendaEntries.get(entryIndex);


        List<Vehicle> validVehicles = controller.getValidVehicles(selectedEntry);
        System.out.println("Valid Vehicles for Selected Entry:");
        for (int i = 0; i < validVehicles.size(); i++) {
            System.out.println((i + 1) + ". " + validVehicles.get(i).getPlateNumber());
        }


        System.out.print("Select a vehicle (1-" + validVehicles.size() + "): ");
        int vehicleIndex = Integer.parseInt(scanner.nextLine()) - 1;
        Vehicle selectedVehicle = validVehicles.get(vehicleIndex);


        boolean success = controller.assignVehicleToAgendaEntry(selectedEntry, selectedVehicle);
        if (success) {
            System.out.println("Vehicle successfully assigned to agenda entry.");
        } else {
            System.out.println("Failed to assign vehicle to agenda entry.");
        }
    }
}