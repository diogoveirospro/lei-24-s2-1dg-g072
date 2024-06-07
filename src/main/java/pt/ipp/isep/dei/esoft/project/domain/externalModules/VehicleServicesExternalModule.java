package pt.ipp.isep.dei.esoft.project.domain.externalModules;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.io.FileNotFoundException;

/**
 * The VehicleServicesExternalModule interface represents a module for handling vehicle services.
 */
public interface VehicleServicesExternalModule {

    /**
     * Request a service for the specified vehicle.
     *
     * @param vehicle the vehicle for which the service is requested
     * @throws FileNotFoundException if the service request cannot be completed due to file not found
     */
    void requestService(Vehicle vehicle) throws FileNotFoundException;

    /**
     * Schedule a service for the specified vehicle.
     *
     * @param vehicle the vehicle for which the service is scheduled
     * @throws FileNotFoundException if the service scheduling cannot be completed due to file not found
     */
    void scheduleService(Vehicle vehicle) throws FileNotFoundException;
}