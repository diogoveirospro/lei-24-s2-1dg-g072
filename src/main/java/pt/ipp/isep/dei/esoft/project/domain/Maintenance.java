package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 *
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class Maintenance {

    /**
     * Vehicle that needs maintenance
     *
     */
    private Vehicle vehicle;
    /**
     * A constructor of Maintenance that creates an object that initiates the instance kmAtMaintenance, and the list vehicles
     *
     * @param vehicle that needs maintenance
     */
    public Maintenance(Vehicle vehicle){
        this.vehicle = vehicle;
    }
    /**
     * Lets the user get the Vehicle
     *
     * @return vehicle
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Lets the user change the Vehicle
     *
     * @param vehicle A vehicle that needs maintenance
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Changes the data of the vehicle, so it updates the data to their new maintenance, last maintenance km.
     *
     * @param vehicle that needs maintenance
     */
    public void setVehicleMaintenance(Vehicle vehicle){
        vehicle.setKmAtLastMaintenance(vehicle.getCurrentKms());
    }

    /**
     * It will compare two Maintenance vehicles
     * @param vehicleMaintenance a vehicle that needs maintenance
     * @return the equality between two vehicles
     */
    @Override
    public boolean equals(Object vehicleMaintenance) {
        if (this == vehicleMaintenance) return true;
        if (vehicleMaintenance == null || getClass() != vehicleMaintenance.getClass()) return false;
        Maintenance that = (Maintenance) vehicleMaintenance;
        return Objects.equals(vehicle, that.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicle);
    }

}
