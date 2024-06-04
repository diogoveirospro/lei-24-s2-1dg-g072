package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.Optional;

/**
 *
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class RegisterVehicleUIController {

    /**
     * Authentication repository.
     */
    private AuthenticationRepository authenticationRepository;

    /**
     * Vehicle repository.
     */
    private VehicleRepository vehicleRepository;

    /**
     * Empty RegisterVehicleController builder
     */
    public RegisterVehicleUIController(){
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        this.vehicleRepository = Repositories.getInstance().getVehicleRepository();
    }

    /**
     * Lets the controller get the vehicle repository.
     * @return vehicleRepository.
     */
    private VehicleRepository getVehicleRepository(){
        if(vehicleRepository == null){
            Repositories repositories = Repositories.getInstance();
            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }

    /**
     * Lets the controller get the authentication repository.
     * @return authenticationRepository.
     */
    private AuthenticationRepository getAuthenticationRepository(){
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     *
     * @param plate plate number.
     * @param model vehicle model.
     * @param brand vehicle brand.
     * @param type vehicle type.
     * @param tare vehicle tare.
     * @param grossWeight vehicle gross weight.
     * @param currentKms vehicle's currrent kms.
     * @param registrationDate vehicle's registration date.
     * @param acquisitionDate vehicle's acquisition date.
     * @param serviceFrequency vehicle service frequency.
     * @param kmAtLastMaintenance vehicle's km at last maintenance.
     * @return new vehicle.
     */
    public Optional<Vehicle> registerVehicle(String plate, String model, String brand, String type, Double tare, Double grossWeight, Double currentKms, Date registrationDate, Date acquisitionDate, Double serviceFrequency, Double kmAtLastMaintenance) {

        Optional<VehicleRepository> vehicleRepository = Optional.ofNullable(getVehicleRepository());
        Optional<Vehicle> newVehicle = Optional.empty();

        if(vehicleRepository.isPresent()){
            newVehicle = vehicleRepository.get().registerVehicle(plate, model, brand, type, tare, grossWeight, currentKms, registrationDate, acquisitionDate, serviceFrequency, kmAtLastMaintenance);
        }
        return newVehicle;
    }
}
