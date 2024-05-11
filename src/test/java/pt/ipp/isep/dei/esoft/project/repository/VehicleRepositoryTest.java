package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleRepositoryTest {

    VehicleRepository vehicleRepository = new VehicleRepository();

    Vehicle v1 = new Vehicle("11-AA-22", "toyota", "avensis", "combust", 4000.0, 3000.0, 30000.0, new Date(2020, 4, 19), new Date(2020, 5, 20), 10000.0, 25000.0);

    Vehicle v2 = new Vehicle("ZA-38-UI", "toyota", "corolla", "combust", 4000.0, 3000.0, 30000.0, new Date(2014, 7, 15), new Date(2014, 8, 20), 10000.0, 25000.0);

    Vehicle v3 = new Vehicle("BB-AA-22", "ferrary", "aviento", "combust", 4000.0, 3000.0, 30000.0, new Date(2000, 4, 19), new Date(2001, 5, 20), 10000.0, 25000.0);

    Vehicle v4 = new Vehicle("11-AA-AA", "ferrari", "aviento", "combust", 4000.0, 3000.0, 30000.0, new Date(2020, 4, 19), new Date(2020, 5, 20), 10000.0, 25000.0);


    /**
     * Test to add a vehicle.
     */
    @Test
    void testAddVehicle(){
        vehicleRepository.addVehicle(v1);
        vehicleRepository.addVehicle(v2);
        vehicleRepository.addVehicle(v3);
        vehicleRepository.addVehicle(v4);

        List<Vehicle> vehicleList = vehicleRepository.getVehicleList();

        assertTrue(vehicleList.contains(v1));
        assertTrue(vehicleList.contains(v2));
        assertTrue(vehicleList.contains(v3));

    }

    /**
     * Test to register a vehicle.
     */
    @Test
    void testRegisterVehicle() {

        String plate = "11-AA-22";
        String brand = "Toyota";
        String model = "Corolla";
        String type = "Sedan";
        double tare = 1500.0;
        double grossWeight = 2000.0;
        double currentKms = 10000.0;
        Date registrationDate = new Date(2020, 5, 20);
        Date acquisitionDate = new Date(2020, 6, 19);
        double serviceFrequency = 5000.0;
        double kmAtLastMaintenance = 8000.0;

        Optional<Vehicle> result = vehicleRepository.registerVehicle(plate, brand, model, type, tare, grossWeight, currentKms, registrationDate, acquisitionDate, serviceFrequency, kmAtLastMaintenance);

        assertTrue(result.isPresent());
        Vehicle registeredVehicle = result.get();
        assertEquals(plate, registeredVehicle.getPlateNumber());
        assertEquals(brand, registeredVehicle.getBrand());
        assertEquals(model, registeredVehicle.getModel());
        assertEquals(type, registeredVehicle.getType());
        assertEquals(tare, registeredVehicle.getTare(), 0.01);
        assertEquals(grossWeight, registeredVehicle.getGrossWeight(), 0.01);
        assertEquals(currentKms, registeredVehicle.getCurrentKms(), 0.01);
        assertEquals(registrationDate, registeredVehicle.getRegistrationDate());
        assertEquals(acquisitionDate, registeredVehicle.getAcquisitionDate());
        assertEquals(serviceFrequency, registeredVehicle.getServiceFrequency(), 0.01);
        assertEquals(kmAtLastMaintenance, registeredVehicle.getKmAtLastMaintenance(), 0.01);
    }


}
