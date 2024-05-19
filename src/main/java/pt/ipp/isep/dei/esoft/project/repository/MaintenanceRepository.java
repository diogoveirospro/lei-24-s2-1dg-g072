package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Maintenance;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains all the existent vehicles that need maintenance.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class MaintenanceRepository {
    /**
     * List of vehicles that need maintenance
     */
    private List<Maintenance> maintenanceList;

    /**
     * Initiates the maintenanceList
     *
     */
    public MaintenanceRepository(){
        maintenanceList = new ArrayList<>();
    }
    /**
     * Get a vehicle from the repository by its details.
     *
     * @param vehicleList of all vehicles that need maintenance
     * @return maintenance
     */
    public List<Vehicle> getVehicleList(List<Vehicle> vehicleList) {
        for (Vehicle vehicle : vehicleList) {
            getMaintenance(vehicle, vehicleList);
        }
        return vehicleList;
    }

    /**
     * Lets the user get the maintenance of a specific vehicle
     *
     * @param vehicle that we want to get
     * @param vehicleList list of all vehicles (will be changed in accord with the fact of needing or not maintenance)
     */
    private void getMaintenance(Vehicle vehicle, List<Vehicle> vehicleList) {
        try {
            Maintenance newMaintenance = new Maintenance(vehicle);
            removeVehicle(newMaintenance, vehicleList);
        }catch (IllegalArgumentException e){
            System.out.println("There is no vehicle with the plate number: " + vehicle.getPlateNumber());
        }

    }

    /**
     * Checks if vehicle exists or not
     *
     * @param aux if true the vehicle doesn't exist else it exists
     * @param vehicle message of the vehicle status
     */
    private static void checkIfMaintenanceNotNull(boolean aux, String vehicle) {
        if (aux) {
            throw new IllegalArgumentException(vehicle);
        }
    }

    /**
     * Lets the user get the maintenance of a specific vehicle if it exists
     *
     * @param newMaintenance the maintenance of the vehicle we want to get
     * @param vehicleList    list of vehicles
     */
    private void removeVehicle(Maintenance newMaintenance, List<Vehicle> vehicleList) {
        if (newMaintenance.validateVehicleMaintenance(newMaintenance.getVehicleFromPlate())) {
            vehicleList.remove(newMaintenance.getVehicleFromPlate());
        }
    }

    /**
     * Private method to see if a vehicle is already in the repository.
     *
     * @param maintenance: vehicle that needs maintenance to be checked
     * @return if the vehicle is already in the repository or not
     */
    private boolean validateVehicleMaintenance(Maintenance maintenance) {
        return !maintenanceList.contains(maintenance);
    }

    /**
     * Add a vehicle that needs maintenance to the maintenance repository.
     *
     * @param maintenance: new vehicle that need maintenance.
     */
    public void addVehicleMaintenance(Maintenance maintenance) {
        checkIfMaintenanceNotNull(!validateVehicleMaintenance(maintenance), "Invalid vehicle that needs maintenance to add");
        maintenanceList.add(maintenance);
    }
    /**
     * Lets the user get the list of all vehicles that need maintenance
     *
     * @return maintenanceList
     */


    public List<Maintenance> getMaintenanceList(){
        return List.copyOf(maintenanceList);
    }
}
