package pt.ipp.isep.dei.esoft.project.domain.externalModules;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.io.FileNotFoundException;

public class VehicleService implements VehicleServicesExternalModule {

    @Override
    public void requestService(Vehicle vehicle) throws FileNotFoundException {

        System.out.println("Solicitando serviço para o veículo: " + vehicle.getPlateNumber());
    }

    @Override
    public void scheduleService(Vehicle vehicle) throws FileNotFoundException {

        System.out.println("Agendando serviço para o veículo: " + vehicle.getPlateNumber());
    }
}