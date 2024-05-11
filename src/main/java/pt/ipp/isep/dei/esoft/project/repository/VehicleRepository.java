package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class VehicleRepository {

    /**
     * List containing all vehicles
     */
    private final List <Vehicle> vehicleList;

    /**
     * Repository builder
     */
    public VehicleRepository() {
        vehicleList = new ArrayList<Vehicle>();
    }

    public Optional<Vehicle> registerVehicle(String plate, String brand, String model, String type, Double tare, Double grossWeight, Double currentKms, Date registrationDate, Date acquisitionDate, Double serviceFrequency, Double kmAtLastMaintenance){

        Optional<Vehicle> vehicle = Optional.empty();
        Vehicle newVehicle = new Vehicle(plate, brand, model, type, tare, grossWeight, currentKms, registrationDate, acquisitionDate, serviceFrequency, kmAtLastMaintenance);
        if (addVehicle(newVehicle)){
            vehicle = Optional.of(newVehicle);
        }
        return vehicle;
    }

    /**
     * Add new vehicle to list
     * @param newVehicle vehicle to be added to the list
     */
    public boolean addVehicle(Vehicle newVehicle){
        boolean success = false;
        if (checkVehicleInList(newVehicle) || (newVehicle.validateVehicle())){
            success = vehicleList.add(newVehicle.clone());
        }else {
            throw new IllegalArgumentException("Invalid vehicle to add");
        }
        return success;
    }

    /**
     * Private method to see if the vehicle is already on the list
     * @param vehicle vehicle to be checked.
     * @return True if the vehicle isn't on the list and false if it is
     */
    private boolean checkVehicleInList(Vehicle vehicle){
        return !vehicleList.contains(vehicle);
    }

    /**
     * This method returns a defensive (immutable) copy of the vehicle list.
     * @return The vehicle list
     */
    public List<Vehicle> getVehicleList() {
        return (List.copyOf(vehicleList));
    }
}
