package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the Maintenance class, checking the behaviour
 * of the getVehicle, setVehicle, setVehicleMaintenance, equals and hashCode methods.
 * The tests cover different scenarios to ensure the correct functioning of the functionalities
 * implemented in the Maintenance class.
 * <p>
 * The tests include cases of obtaining and defining vehicles, configuring vehicle maintenance
 * with different pass and fail frequency scenarios, comparing equality between objects and
 * verifying the hash code.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
class MaintenanceTest {
    Maintenance m1 = new Maintenance(new Vehicle("11-AA-22", "toyota", "avensis", "combust", 4000.0, 3000.0, 30000.0, new Date(2020, 4, 19), new Date(2020, 5, 20), 10000.0, 25000.0));
    Maintenance m2 = new Maintenance(new Vehicle("ZA-38-UI", "toyota", "corolla", "combust", 4000.0, 3000.0, 30000.0, new Date(2014, 7, 15), new Date(2014, 8, 20), 10000.0, 25000.0));
    Maintenance m3 = new Maintenance(new Vehicle("BB-AA-22", "ferrary", "aviento", "combust", 4000.0, 3000.0, 40000.0, new Date(2000, 4, 19), new Date(2001, 5, 20), 10000.0, 25000.0));
    Maintenance m4 = new Maintenance(m1.getVehicle());

    /**
     * Test for getting a vehicle
     */
    @Test
    void getVehicle() {
        Vehicle v1 = m1.getVehicle();
        assertEquals(v1,m1.getVehicle());
    }

    /**
     * Test for setting a vehicle
     */
    @Test
    void setVehicle() {
        Vehicle v1 = m1.getVehicle();
        m2.setVehicle(v1);
        assertEquals(m1,m2);
    }

    /**
     * Test for setting a new vehicle check up but for the case where it didn't pass the frequency
     */

    @Test
    void setVehicleMaintenance1() {
        Vehicle v1 = m1.getVehicle();
        m1.setVehicleMaintenance(v1);
        assertEquals(25000,m1.getVehicle().getKmAtLastMaintenance());
    }
    /**
     * Test for setting a new vehicle check up but for the case where it did pass the frequency
     */
    @Test
    void setVehicleMaintenance2(){
        Vehicle v3 = m3.getVehicle();
        m3.setVehicleMaintenance(v3);
        assertEquals(40000,m3.getVehicle().getKmAtLastMaintenance());
    }

    /**
     * Test of equals for the same object
     */
    @Test
    void testEquals1() {
        assertTrue(m1.equals(m1));
    }

    /**
     * Test equals with a null object
     */
    @Test
    void testEquals2() {
        assertFalse(m1.equals(null));
    }

    /**
     * Test equals for different types of objects
     */
    @Test
    void testEquals3() {
        assertFalse(m1.equals(m2.getVehicle()));
    }



    @Test
    void testHashCode1() {
        assertEquals(m1.hashCode(), m4.hashCode());
    }
    @Test
    void testHashCode2(){
        assertNotEquals(m1.hashCode(),m2.hashCode());
    }
}