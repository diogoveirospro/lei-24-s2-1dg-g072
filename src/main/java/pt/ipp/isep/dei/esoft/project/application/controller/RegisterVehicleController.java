package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.Optional;

public class RegisterVehicleController {
    private AuthenticationRepository authenticationRepository;

    private VehicleRepository vehicleRepository;

    public RegisterVehicleController(){
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        this.vehicleRepository = Repositories.getInstance().getVehicleRepository();
    }


    private VehicleRepository getVehicleRepository(){
        if(vehicleRepository == null){
            Repositories repositories = Repositories.getInstance();
            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }

    private AuthenticationRepository getAuthenticationRepository(){
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    public Optional<Vehicle> registerVehicle(String plate, String model, String brand, String type, Double tare, Double grossWeight, Double currentKms, Date registrationDate, Date acquisitionDate, Double serviceFrequency, Double kmAtLastMaintenance) {

        Optional<VehicleRepository> vehicleRepository = Optional.ofNullable(getVehicleRepository());
        Optional<Vehicle> newVehicle = Optional.empty();

        if(vehicleRepository.isPresent()){
            newVehicle = vehicleRepository.get().registerVehicle(plate, model, brand, type, tare, grossWeight, currentKms, registrationDate, acquisitionDate, serviceFrequency, kmAtLastMaintenance);
        }
        return newVehicle;
    }
}
