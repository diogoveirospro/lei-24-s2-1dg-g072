package pt.ipp.isep.dei.esoft.project.domain;

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
    public void setVehicleList(Vehicle vehicle) {
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

}
