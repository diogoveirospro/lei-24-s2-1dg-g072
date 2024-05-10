package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleTest {
    Vehicle v1 = new Vehicle("11-AA-22", "toyota", "avensis", "combust", 4000.0, 3000.0, 30000.0, new Date(2020, 4, 19), new Date(2020, 5, 20), 10000.0, 25000.0);

    Vehicle v2 = new Vehicle("ZA-38-UI", "toyota", "corolla", "combust", 4000.0, 3000.0, 30000.0, new Date(2014, 7, 15), new Date(2014, 8, 20), 10000.0, 25000.0);

    Vehicle v3 = new Vehicle("BB-AA-22", "ferrary", "aviento", "combust", 4000.0, 3000.0, 30000.0, new Date(2000, 4, 19), new Date(2001, 5, 20), 10000.0, 25000.0);

    Vehicle v4 = new Vehicle("11-AA-AA", "ferrari", "aviento", "combust", 4000.0, 3000.0, 30000.0, new Date(2020, 4, 19), new Date(2020, 5, 20), 10000.0, 25000.0);

    @Test
    void getPlateNumberTest(){
        assertEquals(v1.getPlateNumber(), "11-AA-22");
    }

    @Test
    void getCurrentKmsTest(){
        assertEquals(v1.getCurrentKms(), 30000);
    }

    @Test
    void getGrossWeightTest(){
        assertEquals(v1.getGrossWeight(), 3000);
    }

    @Test
    void getKmAtLastMaintenanceTest(){
        assertEquals(v1.getKmAtLastMaintenance(), 25000);
    }

    @Test
    void getServiceFrequencyTest(){
        assertEquals(v1.getServiceFrequency(), 10000);
    }

    @Test
    void getTareTest(){
        assertEquals(v1.getTare(), 4000);
    }

    @Test
    void getAcquisitionDateTest(){
        assertEquals(v1.getAcquisitionDate(), new Date(2020, 5, 20));
    }

    @Test
    void getRegistrationDateTest(){
        assertEquals(v1.getRegistrationDate(), new Date(2020, 4, 19));
    }

    @Test
    void getBrandTest(){
       assertEquals(v1.getBrand(), "toyota");
    }

}
