package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {
    Vehicle v1 = new Vehicle("11-AA-22", "toyota", "avensis", "combust", 4000.0, 3000.0, 30000.0, new Date(2020, 4, 19), new Date(2020, 5, 20), 10000.0, 25000.0);

    Vehicle v2 = new Vehicle("ZA-38-UI", "toyota", "corolla", "combust", 4000.0, 3000.0, 30000.0, new Date(2014, 7, 15), new Date(2014, 8, 20), 10000.0, 25000.0);

    Vehicle v3 = new Vehicle("BB-AA-22", "ferrari", "aviento", "combust", 4000.0, 3000.0, 30000.0, new Date(2000, 4, 19), new Date(2001, 5, 20), 10000.0, 25000.0);

    Vehicle v4 = new Vehicle("11-AA-AA", "ferrari", "aviento", "combust", 4000.0, 3000.0, 30000.0, new Date(2020, 4, 19), new Date(2020, 5, 20), 10000.0, 25000.0);

    Vehicle v5  = new Vehicle("11-AA-22", "toyota", "avensis", "combust", 4000.0, 3000.0, 30000.0, new Date(2020, 4, 19), new Date(2020, 5, 20), 10000.0, 25000.0);

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

    @Test
    void getModelTest(){
        assertEquals(v1.getModel(), "avensis");
    }

    @Test
    void getTypeTest(){
        assertEquals(v1.getType(), "combust");
    }

    @Test
    void setAcquisitionDateTest(){
        v1.setAcquisitionDate(new Date(2020, 7, 10));
        assertEquals(v1.getAcquisitionDate(), new Date(2020, 7, 10));
    }

    @Test
    void setRegistrationDateTest(){
        v1.setRegistrationDate(new Date(2020, 7, 10));
        assertEquals(v1.getRegistrationDate(), new Date(2020, 7, 10));
    }

    @Test
    void setBrandTest(){
        v1.setBrand("ferrari");
        assertEquals(v1.getBrand(), "ferrari");
    }

    @Test
    void setCurrentKmsTest(){
        v1.setCurrentKms(10000.0);
        assertEquals(v1.getCurrentKms(), 10000);
    }

    @Test
    void setKmAtLastMaintenanceTest(){
        v1.setKmAtLastMaintenance(10000.0);
        assertEquals(v1.getKmAtLastMaintenance(), 10000);
    }

    @Test
    void setGrossWeight(){
        v1.setGrossWeight(5000.0);
        assertEquals(v1.getGrossWeight(), 5000);
    }

    @Test
    void setModelTest(){
        v1.setModel("aviento");
        assertEquals(v1.getModel(), "aviento");
    }

    @Test
    void setPlateNumberTest(){
        v1.setPlateNumber("BB-AA-12");
        assertEquals(v1.getPlateNumber(), "BB-AA-12");
    }

    @Test
    void setServiceFrequencyTest(){
        v1.setServiceFrequency(15000.0);
        assertEquals(v1.getServiceFrequency(), 15000);
    }

    @Test
    void setTareTest(){
        v1.setTare(4000.0);
        assertEquals(v1.getTare(), 4000);
    }

    @Test
    void setTypeTest(){
        v1.setType("electric");
        assertEquals(v1.getType(), "electric");
    }

    @Test
    void validateVehicleTest(){
        assertFalse(v4.validateVehicle());
        assertTrue(v1.validateVehicle());
    }

    @Test
    void equalsTest(){
        assertNotEquals(v1, v4);
        assertEquals(v1, v5);
    }

    @Test
    void getVehicleYearTest(){
        assertEquals(v1.getVehicleYear(), 2020);
    }

    @Test
    void cloneTest(){
        assertEquals(v1, v1.clone());
    }

}
