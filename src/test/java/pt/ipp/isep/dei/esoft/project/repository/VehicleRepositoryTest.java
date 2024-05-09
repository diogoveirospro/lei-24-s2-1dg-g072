package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VehicleRepositoryTest {

    VehicleRepository vehicleRepository = new VehicleRepository();

    List<Vehicle> vehicleList = vehicleRepository.getVehicleList();

    Vehicle v1 = new Vehicle("11-AA-22", "toyota", "avensis", "combust", 4000.0, 3000.0, 30000.0, new Date(2020, 4, 19), new Date(2020, 5, 20), 10000.0, 25000.0);

    Vehicle v2 = new Vehicle("ZA-38-UI", "toyota", "corolla", "combust", 4000.0, 3000.0, 30000.0, new Date(2014, 7, 15), new Date(2014, 8, 20), 10000.0, 25000.0);

    Vehicle v3 = new Vehicle("BB-AA-22", "ferrary", "aviento", "combust", 4000.0, 3000.0, 30000.0, new Date(2000, 4, 19), new Date(2001, 5, 20), 10000.0, 25000.0);

    Vehicle v4 = new Vehicle("11-AA-AA", "ferrari", "aviento", "combust", 4000.0, 3000.0, 30000.0, new Date(2020, 4, 19), new Date(2020, 5, 20), 10000.0, 25000.0);

    @Test
    void testAddVehicle(){
        vehicleRepository.addVehicle(v1);
        vehicleRepository.addVehicle(v2);
        vehicleRepository.addVehicle(v3);

        assertTrue(vehicleList.contains(v1));
        assertTrue(vehicleList.contains(v2));
        assertTrue(vehicleList.contains(v3));
    }

    @Test
    void testRegisterVehicle(){
        vehicleRepository.registerVehicle("11-AA-22", "toyota", "avensis", "combust", 4000.0, 3000.0, 30000.0, new Date(2020, 4, 19), new Date(2020, 5, 20), 10000.0, 25000.0);
        vehicleRepository.registerVehicle("11-AA-AA", "ferrari", "aviento", "combust", 4000.0, 3000.0, 30000.0, new Date(2020, 4, 19), new Date(2020, 5, 20), 10000.0, 25000.0);

        assertTrue(vehicleList.contains(v1));
        assertFalse(vehicleList.contains(v4));
    }
}
