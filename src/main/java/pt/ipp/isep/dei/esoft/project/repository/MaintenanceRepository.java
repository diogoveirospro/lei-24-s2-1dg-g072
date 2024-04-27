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
     *
     */
    private List<Vehicle> vehicleList;
    /**
     * List of vehicles that need maintenance
     *
     */
    private List<Maintenance> maintenanceList;

    /**
     * Get a vehicle from the repository by its details.
     *
     * @param vehicle that needs maintenance
     * @return maintenance
     */
    public Maintenance getVehicleMaintenance(Vehicle vehicle){
        Maintenance newMaintenance = new Maintenance(vehicle);
        Maintenance maintenance = null;
        if (maintenanceList.contains(newMaintenance)){
            maintenance = maintenanceList.get(maintenanceList.indexOf(newMaintenance));
        }

        if (maintenance == null){
            throw new IllegalArgumentException("Vehicle " + vehicle + " does not exist.");
        }

        return maintenance;
    }
    /**
     * Private method to see if a vehicle is already in the repository.
     *
     * @param maintenance: vehicle that needs maintenance to be checked
     * @return if the vehicle is already in the repository or not
     */
    private  boolean  validateVehicleMaintenance(Maintenance maintenance){
        return !maintenanceList.contains(maintenance);
    }
    /**
     * Add a job to the repository.
     * @param newMaintenance: new vehicle that needs maintenance.
     */
    public void addVehicleMaintenance(Maintenance newMaintenance){

        if (!validateVehicleMaintenance(newMaintenance)) {
            throw new IllegalArgumentException("Invalid vehicle that needs maintenance to add");
        }

        maintenanceList.add(newMaintenance);

    }
}
