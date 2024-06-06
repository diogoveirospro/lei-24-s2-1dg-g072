package pt.ipp.isep.dei.esoft.project.domain.externalModules;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.io.FileNotFoundException;

public interface VehicleServicesExternalModule {
    void requestService(Vehicle vehicle) throws FileNotFoundException;
    void scheduleService(Vehicle vehicle) throws FileNotFoundException;
}