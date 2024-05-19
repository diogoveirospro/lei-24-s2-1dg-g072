package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDto;
import pt.ipp.isep.dei.esoft.project.Mapper.VehicleMapper;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;

/**
 * This class serves as the controller for the functionality of listing all the vehicles
 * that need maintenance.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class ListMaintenanceController {
    private MaintenanceRepository maintenanceRepository;
    private AuthenticationRepository authenticationRepository;
    private VehicleRepository vehicleRepository;
    /**
     * Empty ListMaintenanceController builder.
     *
     */
    public ListMaintenanceController(){
        this.maintenanceRepository = Repositories.getInstance().getMaintenanceRepository();
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        this.vehicleRepository = Repositories.getInstance().getVehicleRepository();
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
     * Lets the controller get access to the maintenanceList
     *
     * @return vehicleList copy
     */
    public List<VehicleDto> getVehicleList(){
        List<Vehicle> vehicleList =  vehicleRepository.getVehicleList();
        vehicleList = maintenanceRepository.getVehicleList(vehicleList);
        VehicleMapper vehicleMapper = new VehicleMapper();
        return vehicleMapper.toDTO(vehicleList);

    }

}
