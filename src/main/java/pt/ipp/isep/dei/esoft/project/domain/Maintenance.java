package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.List;
import java.util.Objects;

/**
 * It represents the maintenance of one vehicle.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class Maintenance {

    /**
     * Plate number of a vehicle that needs maintenance
     *
     */
    private String plateNumber;
    /**
     * Date of the last maintenance of the vehicle
     *
     */
    private Date dateLastMaintenance;

    /**
     * kms back when the vehicle was serviced
     *
     */
    private Double kmAtMaintenance;

    /**
     * Constructor of maintenance that initiates the variables by receiving as a parameter
     * a vehicle
     *
     * @param vehicle to be maintenance
     */
    public Maintenance(Vehicle vehicle){
        plateNumber = vehicle.getPlateNumber();
        kmAtMaintenance = vehicle.getKmAtLastMaintenance();
        setDateLastMaintenance(dateLastMaintenance);
    }

    /**
     * Changes the data of the vehicle, so it updates the data to their new maintenance, last maintenance km.
     *
     * @param vehicle that needs maintenance
     */
    public void setVehicleMaintenance(Vehicle vehicle){
        vehicle.setKmAtLastMaintenance(vehicle.getCurrentKms());
        setDateLastMaintenance(Date.currentDate());
    }

    /**
     * Checks if the vehicle needs or not to be serviced
     *
     * @param vehicle to be checked
     * @return true or false depending on how many kms were done
     */
    public boolean validateVehicleMaintenance(Vehicle vehicle){
        return vehicle.getServiceFrequency() < vehicle.getCurrentKms() - vehicle.getKmAtLastMaintenance();
    }


    /**
     * Lets the system change the value of the kmAtLastMaintenance
     *
     * @param kmAtMaintenance kms when the last maintenance occurred
     */
    public void setKmAtMaintenance(Double kmAtMaintenance) {

        this.kmAtMaintenance = kmAtMaintenance;
    }

    /**
     * Lets the system set the maintenance date of the vehicle
     *
     * @param dateLastMaintenance date of the last maintenance made to the vehicle
     */
    public void setDateLastMaintenance(Date dateLastMaintenance) {
        Vehicle vehicle = getVehicleFromPlate();
        if (dateLastMaintenance == null){
            dateLastMaintenance = vehicle.getRegistrationDate();
        } else {
            this.dateLastMaintenance = dateLastMaintenance;
        }
    }

    /**
     * Lets the system get the last maintenance Date
     *
     * @return dateLastMaintenance
     */
    public Date getDateLastMaintenance() {
        return dateLastMaintenance;
    }

    /**
     * Lets the system get the kms at the maintenance
     *
     * @return kmAtMaintenance
     */

    public Double getKmAtMaintenance() {
        return kmAtMaintenance;
    }

    public String getPlateNumber() {
        return plateNumber;
    }
}
