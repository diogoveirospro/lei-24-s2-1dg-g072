package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 *
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class VehicleRepository implements Serializable {

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
        boolean success = true;
        if (checkVehicleInList(newVehicle) && (newVehicle.validateVehicle())){
            vehicleList.add(newVehicle.clone());
        }else {
            success = false;
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
        List<Vehicle> vehicles = vehicleList;
        sortByKms(vehicles);
        return (List.copyOf(vehicles));
    }

    /**
     * This method will get the difference of all vehicles, in terms of kilometers.
     *
     * @param difference deference of all vehicles
     */

    private void getDifferenceInKms(double[] difference,List<Vehicle> vehicleList) {
        int index = 0;
        for (Vehicle vehicle : vehicleList){

            difference[index] = (vehicle.getKmAtLastMaintenance() + vehicle.getServiceFrequency())- vehicle.getCurrentKms();

            index++;
        }
    }

    /**
     * This method will sort the vehicle by kms to the next maintenance
     *
     */

    public void sortByKms(List<Vehicle> vehicleList){
        double[] difference = new double[vehicleList.size()];
        getDifferenceInKms(difference,vehicleList);
        sortByKmsToMaintenance(difference,vehicleList);
    }

    /**
     * Will sort all the vehicles by kms to the maintenance
     *
     * @param difference between the current and last maintenance kms + frequency of maintenance
     * @param vehicleList list of all vehicles
     */
    private void sortByKmsToMaintenance(double[] difference, List<Vehicle> vehicleList) {
        int index1 = 0;
        for (Vehicle vehicle : vehicleList) {
            int index2 = 0;
            for (Vehicle otherVehicle : vehicleList) {
                if (difference[index1] < difference[index2]) {

                    Vehicle aux1 = vehicleList.get(index1);
                    vehicleList.set(index1, vehicleList.get(index2));
                    vehicleList.set(index2, aux1);

                    double aux2 = difference[index1];
                    difference[index1] = difference[index2];
                    difference[index2] = aux2;
                }
                index2++;
            }
            index1++;
        }
    }


    /**
     * Lets the system get the vehicle from is plate
     *
     * @return vehicle
     */

    public Vehicle getVehicleFromPlate(String plateNumber){
        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
        List<Vehicle> vehicleList = vehicleRepository.getVehicleList();
        for (Vehicle vehicle : vehicleList){
            if (Objects.equals(plateNumber, vehicle.getPlateNumber())){
                return vehicle;
            }
        }
        return null;
    }

    public List<Vehicle> getValidVehicles(AgendaEntry agendaEntry) {
        List<Vehicle> validVehicles = new ArrayList<>();
        List<Vehicle> allVehicles = Repositories.getInstance().getVehicleRepository().getVehicleList();

        for (Vehicle vehicle : allVehicles) {
            if (vehicle.validateVehicleForEntry(agendaEntry)) {
                validVehicles.add(vehicle);
            }
        }
        return validVehicles;
    }
}
