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


    private String plateNumber;
    private Date dateLastMaintenance;
    private Double kmAtMaintenance;

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
        Date currentDate = new Date();
        vehicle.setKmAtLastMaintenance(vehicle.getCurrentKms());
        setDateLastMaintenance(currentDate);

    }


    public boolean validateVehicleMaintenance(Vehicle vehicle){
        return vehicle.getServiceFrequency() < vehicle.getCurrentKms() - vehicle.getKmAtLastMaintenance();
    }

    public Vehicle getVehicleFromPlate(){
        VehicleRepository vehicleRepository = new VehicleRepository();
        List<Vehicle> vehicleList = vehicleRepository.getVehicleList();
        for (Vehicle vehicle : vehicleList){
            if (Objects.equals(vehicle.getPlateNumber(), plateNumber)){
                return vehicle;
            }
        }
        throw new IllegalArgumentException("There is no vehicle with the plate number: " + plateNumber);
    }

    public void setKmAtMaintenance(Double kmAtMaintenance) {

        this.kmAtMaintenance = kmAtMaintenance;
    }

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
}
