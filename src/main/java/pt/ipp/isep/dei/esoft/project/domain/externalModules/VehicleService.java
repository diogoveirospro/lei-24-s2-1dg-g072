package pt.ipp.isep.dei.esoft.project.domain.externalModules;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.io.FileNotFoundException;

/**
 * Implementation of the VehicleServicesExternalModule interface for handling vehicle services.
 */
public class VehicleService implements VehicleServicesExternalModule {

    /**
     * Request a service for the specified vehicle.
     *
     * @param vehicle the vehicle for which the service is requested
     * @throws FileNotFoundException if the service request cannot be completed due to file not found
     */
    @Override
    public void requestService(Vehicle vehicle) throws FileNotFoundException {
        System.out.println("Solicitando serviço para o veículo: " + vehicle.getPlateNumber());
    }

    /**
     * Schedule a service for the specified vehicle.
     *
     * @param vehicle the vehicle for which the service is scheduled
     * @throws FileNotFoundException if the service scheduling cannot be completed due to file not found
     */
    @Override
    public void scheduleService(Vehicle vehicle) throws FileNotFoundException {
        System.out.println("Agendando serviço para o veículo: " + vehicle.getPlateNumber());
    }
}