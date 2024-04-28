package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Maintenance;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.MaintenanceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.List;

public class RegisterVehicleMaintenanceController {
    private VehicleRepository vehicleRepository;
    private MaintenanceRepository maintenanceRepository;
    private AuthenticationRepository authenticationRepository;
    /**
     * Empty RegisterVehicleMaintenanceController builder.
     *
     */
    public RegisterVehicleMaintenanceController(){
        this.maintenanceRepository = Repositories.getInstance().getMaintenanceRepository();
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        this.vehicleRepository = Repositories.getInstance().getVehicleRepository();
    }

    /**
     * RegisterVehicleMaintenanceController builder
     *
     * @param vehicleRepository contains all vehicles
     * @param maintenanceRepository contains all vehicles that need maintenance
     * @param authenticationRepository authenticates the app
     */
    public RegisterVehicleMaintenanceController(VehicleRepository vehicleRepository, MaintenanceRepository maintenanceRepository, AuthenticationRepository authenticationRepository){
        this.vehicleRepository = vehicleRepository;
        this.authenticationRepository = authenticationRepository;
        this.maintenanceRepository = maintenanceRepository;
    }

    /**
     * Lets the controller get the maintenance repository
     *
     * @return maintenanceRepository
     */
    private MaintenanceRepository getMaintenanceRepository(){
        if (maintenanceRepository == null) {
            Repositories repositories = Repositories.getInstance();

            maintenanceRepository = repositories.getMaintenanceRepository();
        }
        return maintenanceRepository;
    }

    /**
     * Lets the controller get the vehicle repository
     *
     * @return vehicleRepository
     */
    private VehicleRepository getVehicleRepository(){
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();

            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }
    /**
     * Lets the controller get the authentication repository
     *
     * @return authenticationRepository
     */

    private AuthenticationRepository getAuthenticationRepository(){
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }
    /**
     * Register vehicle maintenance
     *
     * @param vehicleList list of all vehicles that need maintenance
     */
    public void registerVehicleMaintenance(List<Vehicle> vehicleList){

    }
}
