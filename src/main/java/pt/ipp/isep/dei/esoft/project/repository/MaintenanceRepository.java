package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Maintenance;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import javax.management.InstanceAlreadyExistsException;
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
     */
    private static void checkIfMaintenanceNotNull( Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Invalid vehicle that needs maintenance to add");
        }
    }

    /**
     * Lets the user get the maintenance of a specific vehicle if it exists
     *
     * @param newMaintenance the maintenance of the vehicle we want to get
     * @param vehicleList    list of vehicles
     */
    private void removeVehicle(Maintenance newMaintenance, List<Vehicle> vehicleList) {
        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
        List<Vehicle> mutableVehicleList = new ArrayList<>(vehicleList);
        if (newMaintenance.validateVehicleMaintenance(vehicleRepository.getVehicleFromPlate(newMaintenance.getPlateNumber()))) {
            Vehicle vehicle = vehicleRepository.getVehicleFromPlate(newMaintenance.getPlateNumber());
            mutableVehicleList.remove(vehicle);
            vehicleList = mutableVehicleList;
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
        try {
            VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
            Vehicle vehicle = vehicleRepository.getVehicleFromPlate(maintenance.getPlateNumber());
            checkIfMaintenanceNotNull( vehicle);
            if (maintenance.validateVehicleMaintenance(vehicle)) {
                maintenance.setVehicleMaintenance(vehicle);
                maintenanceList.add(maintenance);
                vehicle.setKmAtLastMaintenance(maintenance.getKmAtMaintenance());
            } else {
                throw new InstanceAlreadyExistsException("The vehicle with the plate: " + maintenance.getPlateNumber() + " already exists");
            }
        }catch (IllegalArgumentException | InstanceAlreadyExistsException e){
            System.out.println(e.getMessage());
        }

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
