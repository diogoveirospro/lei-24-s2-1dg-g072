package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Maintenance;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceRepository {
    /**
     * List of all vehicles, that need maintenance.
     *
     */
    private List<Vehicle> vehicleList;

    public List<Vehicle> addVehicleMaintenance(List<Vehicle> vehicleList){
        for (Vehicle vehicle : vehicleList){
            Maintenance maintenance = new Maintenance(vehicle);
            maintenance.setVehicleMaintenance(vehicle);
        }
        return vehicleList;
    }

    private  boolean  validateVehicleMaintenance(Vehicle vehicle){
        return !vehicleList.contains(vehicle);
    }
}
