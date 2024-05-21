

package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;



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
public class MaintenanceTest {
    VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
    Vehicle vehicle1 = new Vehicle("GG-69-EZ","BMW","i4","hibrid",3500.0,4500.0,1000.0,new Date(2024,1,10), new  Date(2024,1,26),10000.0,0.0);
    Vehicle vehicle2 = new Vehicle("69-WP-42","Toyota","Avensis","Diesel",3000.0,4000.0,42000.0,new Date(2018,12,10), new  Date(2019,1,10),20000.0,30000.0);
    Vehicle vehicle3 = new Vehicle("04-20-VC","ferrari","diablo","Petrol",3000.0,4000.0,100000.0,new Date(2000,12,10), new  Date(2003,10,11),10000.0,75432.3);
    Maintenance m1;
    Maintenance m2;
    Maintenance m3;

    @Test
    void setVehicleMaintenance() {
        m1 = new Maintenance(vehicle1);
        m1.setVehicleMaintenance(vehicle2);
        assertEquals(vehicle2.getKmAtLastMaintenance(),m1.getKmAtMaintenance());
        assertEquals(Date.currentDate(),m1.getDateLastMaintenance());
    }

    @Test
    void validateVehicleMaintenanceTrue() {
        m1 = new Maintenance(vehicle1);
        boolean vehicleValidation = m1.validateVehicleMaintenance(vehicle1);
        assertFalse(vehicleValidation);
    }
    @Test
    void validateVehicleMaintenanceFalse() {
        m1 = new Maintenance(vehicle3);
        boolean vehicleValidation = m1.validateVehicleMaintenance(vehicle3);
        assertTrue(vehicleValidation);
    }

    @Test
    void setKmAtMaintenance() {
        m1 = new Maintenance(vehicle1);
        m1.setKmAtMaintenance(vehicle1.getKmAtLastMaintenance());
        assertEquals(0.0,m1.getKmAtMaintenance());
    }

    @Test
    void setDateLastMaintenanceDateRegistration() {
        m1 = new Maintenance(vehicle2);
        m1.setDateLastMaintenance(vehicle1.getRegistrationDate());
        assertEquals(vehicle1.getRegistrationDate(),m1.getDateLastMaintenance());
    }

    @Test
    void getDateLastMaintenanceToCurrentDate() {
        m1 = new Maintenance(vehicle2);
        m1.setDateLastMaintenance(Date.currentDate());
        assertEquals(Date.currentDate(),m1.getDateLastMaintenance());
    }


    @Test
    void getKmAtMaintenance() {
        m1 = new Maintenance(vehicle1);
        assertEquals(0.0,m1.getKmAtMaintenance());
    }

    @Test
    void getPlateNumber() {
        m1 = new Maintenance(vehicle1);
        assertEquals("GG-69-EZ",m1.getPlateNumber());
    }


}
