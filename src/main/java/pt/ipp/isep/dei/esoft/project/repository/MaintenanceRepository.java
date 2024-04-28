package pt.ipp.isep.dei.esoft.project.repository;

import org.w3c.dom.ls.LSException;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Maintenance;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceRepository {
    /**
     * List of vehicles that will be registered for maintenance.
     */
    private List<Vehicle> vehicleList;
    /**
     * List of vehicles that need maintenance
     */
    private List<Maintenance> maintenanceList;

    /**
     * Get a vehicle from the repository by its details.
     *
     * @param vehicleList of all vehicles that need maintenance
     * @return maintenance
     */
    public List<Maintenance> getVehicleMaintenance(List<Vehicle> vehicleList) {
        List<Maintenance> maintenances = new ArrayList<Maintenance>();
        for (Vehicle vehicle : vehicleList) {
            Maintenance newMaintenance = new Maintenance(vehicle);
            Maintenance aux = null;
            if (maintenanceList.contains(newMaintenance)) {
                aux = maintenanceList.get(maintenanceList.indexOf(newMaintenance));
                maintenances.add(maintenanceList.get(maintenanceList.indexOf(newMaintenance)));
            }

            if (aux == null) {
                throw new IllegalArgumentException("Vehicle " + vehicle + " does not exist.");
            }
        }
        return maintenances;
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
     * @param vehicleList: all new vehicles that need maintenance.
     */
    public void addVehicleMaintenance(List<Vehicle> vehicleList) {

        for (Vehicle vehicle: vehicleList) {
            Maintenance newMaintenance = new Maintenance(vehicle);
            if (!validateVehicleMaintenance(newMaintenance)) {
                throw new IllegalArgumentException("Invalid vehicle that needs maintenance to add");
            }
            maintenanceList.add(newMaintenance);
        }
    }
}
