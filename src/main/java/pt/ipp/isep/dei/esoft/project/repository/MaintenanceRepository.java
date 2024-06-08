package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Maintenance;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.data.SerializableRepository;

import javax.management.InstanceAlreadyExistsException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class contains all the existent vehicles that need maintenance.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class MaintenanceRepository extends SerializableRepository<List<Maintenance>> implements Serializable {
    /**
     * List of vehicles that need maintenance
     */
    private List<Maintenance> maintenanceList;

    /**
     * Initiates the maintenanceList
     */
    public MaintenanceRepository() {
        super("maintenanceRepository.ser");
        maintenanceList = super.load();
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
     * @param vehicle  that we want to get
     * @param vehicles list of all vehicles (will be changed in accord with the fact of needing or not maintenance)
     */
    private List<Vehicle> getMaintenance(Vehicle vehicle, List<Vehicle> vehicles) {
        try {
            vehicles = removeVehicle(vehicle, vehicles);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("There is no vehicle with the plate number: " + vehicle.getPlateNumber());
        }
        return vehicles;
    }

    /**
     * Checks if vehicle exists or not
     */
    private static void checkIfMaintenanceNotNull(Maintenance maintenance) {
        if (maintenance == null) {
            throw new NullPointerException("Invalid vehicle that needs maintenance to add");
        }
    }

    /**
     * Lets the user get the maintenance of a specific vehicle if it exists
     *
     * @param vehicle  the vehicle we want to check
     * @param vehicles list of vehicles
     */
    private List<Vehicle> removeVehicle(Vehicle vehicle, List<Vehicle> vehicles) {
        Maintenance m1 = new Maintenance(vehicle);
        List<Vehicle> mutableVehicleList = new ArrayList<>(vehicles);
        Maintenance maintenance1 = new Maintenance(vehicle);
        checkIfMaintenanceNotNull(maintenance1);
        if (!m1.validateVehicleMaintenance(vehicle)) {
            mutableVehicleList.remove(vehicle);
            vehicles = mutableVehicleList;
        }
        saveMaintenanceRepositoryToFile();
        return vehicles;
    }


    /**
     * Add a vehicle that needs maintenance to the maintenance repository.
     *
     * @param maintenance: new vehicle that need maintenance.
     */
    public void addVehicleMaintenance(Maintenance maintenance) {

        checkIfMaintenanceNotNull(maintenance);
        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
        Vehicle vehicle = vehicleRepository.getVehicleFromPlate(maintenance.getPlateNumber());

        if (!maintenance.validateVehicleMaintenance(vehicle)) {
            throw new IllegalArgumentException("Invalid maintenance data for vehicle with plate: " + maintenance.getPlateNumber());
        }

        Optional<Maintenance> existingMaintenanceOpt = maintenanceList.stream()
                .filter(m -> m.getPlateNumber().equals(maintenance.getPlateNumber()))
                .findFirst();

        if (existingMaintenanceOpt.isPresent()) {
            Maintenance existingMaintenance = existingMaintenanceOpt.get();
            existingMaintenance.updateFrom(maintenance);
            System.out.println("Maintenance updated for vehicle with plate: " + maintenance.getPlateNumber());
        } else {
            maintenance.setVehicleMaintenance(vehicle);

            maintenanceList.add(maintenance);
            saveMaintenanceRepositoryToFile();
            System.out.println("Maintenance added for vehicle with plate: " + maintenance.getPlateNumber());

        }

    }

    /**
     * Lets the user get the list of all vehicles that need maintenance
     *
     * @return maintenanceList
     */


    public List<Maintenance> getMaintenanceList() {
        return List.copyOf(maintenanceList);
    }

    public void saveMaintenanceRepositoryToFile() {
        save(maintenanceList);
    }

}
