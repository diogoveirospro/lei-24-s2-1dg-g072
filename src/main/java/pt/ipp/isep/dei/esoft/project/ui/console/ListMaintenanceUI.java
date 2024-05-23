package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListMaintenanceController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDto;

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
    private List<VehicleDto> vehicleListDto;
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

    /**
     * Runs the program
     *
     */
    @Override
    public void run() {
        System.out.println("\n\n--- List Maintenance ------------------------");

        submitData();
    }

    /**
     * Shows the list of all vehicles that need maintenance
     *
     */
    private void submitData() {
        vehicleListDto = controller.getVehicleList();
        double next;
        if (!vehicleListDto.isEmpty()) {
            System.out.println("\nVehicles in need of maintenance:");
            System.out.println("| Plate | Brand | Model | Curr.Kms | Freq | Last | Next |" );
            for (VehicleDto vehicle : vehicleListDto) {
                if (vehicle.getKmAtLastMaintenance() + vehicle.getServiceFrequency() < vehicle.getCurrentKms()){
                    next = vehicle.getCurrentKms();
                } else {
                    next = vehicle.getKmAtLastMaintenance() + vehicle.getServiceFrequency();
                }


                System.out.printf("| %s | %s | %s | %.0f | %.0f | %.0f | %.0f |", vehicle.getPlateNumber(), vehicle.getBrand(), vehicle.getModel(),vehicle.getCurrentKms(),vehicle.getServiceFrequency(),vehicle.getKmAtLastMaintenance(),next);
                System.out.printf("%n");
            }
        } else {
            System.out.println("\nThere are no vehicles needing maintenance in the system!");
        }
    }


}
