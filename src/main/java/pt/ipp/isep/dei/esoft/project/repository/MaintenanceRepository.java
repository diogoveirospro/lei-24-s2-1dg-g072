package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Maintenance;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import javax.management.InstanceAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<Vehicle> vehicles = vehicleList;
        for (Vehicle vehicle : vehicleList) {
            vehicles = getMaintenance(vehicle, vehicles);
        }
        return vehicles;
    }

    /**
     * Lets the user get the maintenance of a specific vehicle
     *
     * @param vehicle that we want to get
     * @param vehicles list of all vehicles (will be changed in accord with the fact of needing or not maintenance)
     */
    private List<Vehicle> getMaintenance(Vehicle vehicle, List<Vehicle> vehicles) {
        try {
            vehicles = removeVehicle(vehicle, vehicles);
        }catch (IllegalArgumentException e){
            System.out.println("There is no vehicle with the plate number: " + vehicle.getPlateNumber());
        }
        return vehicles;
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
     * @param vehicle the vehicle we want to check
     * @param vehicles    list of vehicles
     */
    private List<Vehicle> removeVehicle(Vehicle vehicle, List<Vehicle> vehicles) {
        Maintenance m1 = new Maintenance(vehicle);
        List<Vehicle> mutableVehicleList = new ArrayList<>(vehicles);
        if (!m1.validateVehicleMaintenance(vehicle)) {
            mutableVehicleList.remove(vehicle);
            vehicles = mutableVehicleList;
        }
        return vehicles;
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
            checkIfMaintenanceNotNull(vehicle);
            Optional<Maintenance> existingMaintenanceOpt = maintenanceList.stream()
                    .filter(m -> m.getPlateNumber().equals(maintenance.getPlateNumber()))
                    .findFirst();
            if (existingMaintenanceOpt.isPresent()) {
                Maintenance existingMaintenance = existingMaintenanceOpt.get();
                if (maintenance.validateVehicleMaintenance(vehicle)) {
                    existingMaintenance.updateFrom(maintenance);
                    System.out.println("Maintenance updated for vehicle with plate: " + maintenance.getPlateNumber());
                } else {
                    throw new IllegalArgumentException("Invalid maintenance data for vehicle with plate: " + maintenance.getPlateNumber());
                }
            } else {
                if (maintenance.validateVehicleMaintenance(vehicle)) {
                    maintenance.setVehicleMaintenance(vehicle);
                    if (validateVehicleMaintenance(maintenance)) {
                        maintenanceList.add(maintenance);
                        System.out.println("Maintenance added for vehicle with plate: " + maintenance.getPlateNumber());
                    } else {
                        throw new IllegalArgumentException("Invalid maintenance data.");
                    }
                } else {
                    throw new InstanceAlreadyExistsException("The vehicle with the plate: " + maintenance.getPlateNumber() + " already exists");
                }
            }
        } catch (IllegalArgumentException | InstanceAlreadyExistsException e) {
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
