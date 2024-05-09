package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Maintenance;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.MaintenanceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.List;

/**
 *
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class ListMaintenanceController {
    private MaintenanceRepository maintenanceRepository;
    private AuthenticationRepository authenticationRepository;

    /**
     * Empty ListMaintenanceController builder.
     *
     */
    public ListMaintenanceController(){
        this.maintenanceRepository = Repositories.getInstance().getMaintenanceRepository();
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    /**
     * ListMaintenanceController builder
     *
     * @param maintenanceRepository contains all vehicles that need maintenance
     * @param authenticationRepository authenticates the app
     */
    public ListMaintenanceController(MaintenanceRepository maintenanceRepository, AuthenticationRepository authenticationRepository){
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
     * Lets the controller get access to the vehicleList
     *
     * @return vehicleList copy
     */
    public List<Maintenance> getMaintenanceList(){
        return maintenanceRepository.getMaintenanceList();

    }

    /**
     * Changes the value of the last maintenance if the difference between the current kms and the last maintenance is greater than the service frequency
     *
     * @param maintenance of the vehicle
     */
    public void checkLastMaintenance(Maintenance maintenance) {
        maintenance.setVehicleMaintenance(maintenance.getVehicle());
    }
}
