package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListMaintenanceController;
import pt.ipp.isep.dei.esoft.project.domain.Maintenance;

import java.util.List;

/**
 * The ListMaintenanceUI class represents a user interface component responsible for
 * interacting with the system to show the maintenance list. It uses a ListMaintenanceController
 * to handle the showing of the maintenance list.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class ListMaintenanceUI implements Runnable {
    private  final ListMaintenanceController controller;
    private List<Maintenance> maintenances;
    /**
     * Constructs a new ListMaintenanceUI object and initializes it with a
     * ListMaintenanceController instance for handling listing maintenance operations.
     */
    public ListMaintenanceUI(){
        this.controller = new ListMaintenanceController();
    }
    /**
     * Lets the UI get the controller
     *
     * @return controller
     */
    private ListMaintenanceController getController() {
        return controller;
    }

    @Override
    public void run() {
        System.out.println("\n\n--- List Maintenance ------------------------");

        submitData();
    }

    private void submitData() {
        maintenances = controller.getMaintenanceList();
        if (!maintenances.isEmpty()) {
            System.out.println("\nVehicles in need of maintenance");
            for (Maintenance maintenance : maintenances) {
                System.out.println("|  Plate  |  Brand  |  Model  | Curr.Kms |  Freq  |  Last  |  Next  |" );
                System.out.printf("| %10s | %10s | %10s | %10f | %10f | %10f | %10f |", maintenance.getVehicleList().getPlateNumber(), maintenance.getVehicleList().getBrand(), maintenance.getVehicleList().getModel(),maintenance.getVehicleList().getCurrentKms(),maintenance.getVehicleList().getServiceFrequency(),maintenance.getVehicleList().getKmAtLastMaintenance(),maintenance.getVehicleList().getKmAtLastMaintenance() + maintenance.getVehicleList().getServiceFrequency());
                System.out.printf("%n");
                System.out.printf("%n");
            }
        } else {
            System.out.println("\nThere are no vehicles maintenances in the system!");
        }
    }

}
