package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Maintenance;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 *
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
    public List<Maintenance> getVehicleMaintenance(List<Vehicle> vehicleList) {
        List<Maintenance> maintenances = new ArrayList<Maintenance>();
        for (Vehicle vehicle : vehicleList) {
            getMaintenance(vehicle, maintenances);
        }
        return maintenances;
    }

    /**
     * Lets the user get the maintenance of a specific vehicle
     *
     * @param vehicle that we want to get
     * @param maintenances list of all vehicles registered for maintenance
     */
    private void getMaintenance(Vehicle vehicle, List<Maintenance> maintenances) {
        Maintenance newMaintenance = new Maintenance(vehicle);
        Maintenance aux = null;
        aux = getMaintenanceOfVehicle(newMaintenance, aux, maintenances);

        checkIfMaintenanceNotNull(aux == null, "Vehicle " + vehicle + " does not exist.");
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
     * @param aux lets the program save the info of the new maintenance
     * @param maintenances adds a new entry to the maintenance
     * @return aux
     */
    private Maintenance getMaintenanceOfVehicle(Maintenance newMaintenance, Maintenance aux, List<Maintenance> maintenances) {
        if (maintenanceList.contains(newMaintenance)) {
            aux = maintenanceList.get(maintenanceList.indexOf(newMaintenance));
            maintenances.add(maintenanceList.get(maintenanceList.indexOf(newMaintenance)));
        }
        return aux;
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
        if (maintenanceList == null) {
            maintenanceList = new ArrayList<>();
        }
        maintenanceList.add(maintenance);
    }
    /**
     * Lets the user get the list of all vehicles that need maintenance
     *
     * @return maintenanceList
     */
    public List<Maintenance> getMaintenanceList() {
        return List.copyOf(maintenanceList);
    }
}
