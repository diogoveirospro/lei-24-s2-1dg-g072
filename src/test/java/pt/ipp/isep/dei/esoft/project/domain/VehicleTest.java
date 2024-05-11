package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {
    Vehicle v1 = new Vehicle("11-AA-22", "toyota", "avensis", "combust", 4000.0, 3000.0, 30000.0, new Date(2020, 4, 19), new Date(2020, 5, 20), 10000.0, 25000.0);

    Vehicle v4 = new Vehicle("11-AA-AA", "ferrari", "aviento", "combust", 4000.0, 3000.0, 30000.0, new Date(2020, 4, 19), new Date(2020, 5, 20), 10000.0, 25000.0);

    Vehicle v5  = new Vehicle("11-AA-22", "toyota", "avensis", "combust", 4000.0, 3000.0, 30000.0, new Date(2020, 4, 19), new Date(2020, 5, 20), 10000.0, 25000.0);

    /**
     * Test for getting the vehicle plate number.
     */
    @Test
    void getPlateNumberTest(){
        assertEquals(v1.getPlateNumber(), "11-AA-22");
    }

    /**
     * Test for getting the vehicle current Kms.
     */
    @Test
    void getCurrentKmsTest(){
        assertEquals(v1.getCurrentKms(), 30000);
    }

    /**
     * Test for getting the vehicle gross weight.
     */
    @Test
    void getGrossWeightTest(){
        assertEquals(v1.getGrossWeight(), 3000);
    }

    /**
     * Test for getting the vehicle Km at last maintenance.
     */
    @Test
    void getKmAtLastMaintenanceTest(){
        assertEquals(v1.getKmAtLastMaintenance(), 25000);
    }

    /**
     * Test for getting the vehicle service frequency.
     */
    @Test
    void getServiceFrequencyTest(){
        assertEquals(v1.getServiceFrequency(), 10000);
    }

    /**
     * Test for getting the vehicle tare.
     */
    @Test
    void getTareTest(){
        assertEquals(v1.getTare(), 4000);
    }

    /**
     * Test for getting the vehicle acquisition date.
     */
    @Test
    void getAcquisitionDateTest(){
        assertEquals(v1.getAcquisitionDate(), new Date(2020, 5, 20));
    }

    /**
     * Test for getting the vehicle registration date.
     */
    @Test
    void getRegistrationDateTest(){
        assertEquals(v1.getRegistrationDate(), new Date(2020, 4, 19));
    }

    /**
     * Test for getting the vehicle brand.
     */
    @Test
    void getBrandTest(){
       assertEquals(v1.getBrand(), "toyota");
    }

    /**
     * Test for getting the vehicle model.
     */
    @Test
    void getModelTest(){
        assertEquals(v1.getModel(), "avensis");
    }

    /**
     * Test for getting the vehicle type.
     */
    @Test
    void getTypeTest(){
        assertEquals(v1.getType(), "combust");
    }

    /**
     * Test for setting the vehicle acquisition date.
     */
    @Test
    void setAcquisitionDateTest(){
        v1.setAcquisitionDate(new Date(2020, 7, 10));
        assertEquals(v1.getAcquisitionDate(), new Date(2020, 7, 10));
    }

    /**
     * Test for setting the vehicle registration date.
     */
    @Test
    void setRegistrationDateTest(){
        v1.setRegistrationDate(new Date(2020, 7, 10));
        assertEquals(v1.getRegistrationDate(), new Date(2020, 7, 10));
    }

    /**
     * Test for setting the vehicle brand.
     */
    @Test
    void setBrandTest(){
        v1.setBrand("ferrari");
        assertEquals(v1.getBrand(), "ferrari");
    }

    /**
     * Test for setting the vehicle current Kms.
     */
    @Test
    void setCurrentKmsTest(){
        v1.setCurrentKms(10000.0);
        assertEquals(v1.getCurrentKms(), 10000);
    }

    /**
     * Test for setting the vehicle Km at last maintenance.
     */
    @Test
    void setKmAtLastMaintenanceTest(){
        v1.setKmAtLastMaintenance(10000.0);
        assertEquals(v1.getKmAtLastMaintenance(), 10000);
    }

    /**
     * Test for setting the vehicle gross weight.
     */
    @Test
    void setGrossWeight(){
        v1.setGrossWeight(5000.0);
        assertEquals(v1.getGrossWeight(), 5000);
    }

    /**
     * Test for setting the vehicle model.
     */
    @Test
    void setModelTest(){
        v1.setModel("aviento");
        assertEquals(v1.getModel(), "aviento");
    }

    /**
     * Test for setting the vehicle plate number.
     */
    @Test
    void setPlateNumberTest(){
        v1.setPlateNumber("BB-AA-12");
        assertEquals(v1.getPlateNumber(), "BB-AA-12");
    }

    /**
     * Test for setting the vehicle service frequency.
     */
    @Test
    void setServiceFrequencyTest(){
        v1.setServiceFrequency(15000.0);
        assertEquals(v1.getServiceFrequency(), 15000);
    }

    /**
     * Test for setting the vehicle tare.
     */
    @Test
    void setTareTest(){
        v1.setTare(4000.0);
        assertEquals(v1.getTare(), 4000);
    }

    /**
     * Test for setting the vehicle type.
     */
    @Test
    void setTypeTest(){
        v1.setType("electric");
        assertEquals(v1.getType(), "electric");
    }

    /**
     * Test for validating the vehicle,
     */
    @Test
    void validateVehicleTest(){
        assertFalse(v4.validateVehicle());
        assertTrue(v1.validateVehicle());
    }

    /**
     * Test for validating if a vehicle is equal to another.
     */
    @Test
    void equalsTest(){
        assertNotEquals(v1, v4);
        assertEquals(v1, v5);
    }

    /**
     * Test for getting the vehicle year.
     */
    @Test
    void getVehicleYearTest(){
        assertEquals(v1.getVehicleYear(), 2020);
    }

    /**
     * Test for cloning the vehicle.
     */
    @Test
    void cloneTest(){
        assertEquals(v1, v1.clone());
    }

}
