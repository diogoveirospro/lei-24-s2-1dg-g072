package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Add new vehicle to list
     * @param newVehicle
     */
    public void addVehicle(Vehicle newVehicle){
        if (!validateVehicle(newVehicle)){
            throw new IllegalArgumentException("Invalid vehicle to add");
        }

        vehicleList.add(newVehicle);

    }

    /**
     * Private method to see if the vehicle is already on the list
     * @param vehicle
     * @return True if the vehicle isn't on the list and false if it is
     */
    private boolean validateVehicle(Vehicle vehicle){
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
