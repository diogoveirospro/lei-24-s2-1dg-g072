package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {

    private final List <Vehicle> vehicleList;

    public VehicleRepository() {
        vehicleList = new ArrayList<Vehicle>();
    }


}
