package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Maintenance;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaintenanceRepositoryTest {
    Maintenance m1 = new Maintenance(new Vehicle("11-AA-22","toyota","avensis","combust",4000.0,3000.0,30000.0 ,new Date(2020,4,19),new Date(2020,5,20),10000.0,25000.0));
    Maintenance m2 = new Maintenance(new Vehicle("ZA-38-UI","toyota","corolla","combust",4000.0,3000.0,30000.0 ,new Date(2014,7,15),new Date(2014,8,20),10000.0,25000.0));
    Maintenance m3 = new Maintenance(new Vehicle("BB-AA-22","ferrary","aviento","combust",4000.0,3000.0,30000.0 ,new Date(2000,4,19),new Date(2001,5,20),10000.0,25000.0));

    @Test
    void testAddVehicleMaintenance1() {
        MaintenanceRepository maintenanceRepository = new MaintenanceRepository();
        maintenanceRepository.addVehicleMaintenance(m1);
        List<Maintenance> maintenances = maintenanceRepository.getMaintenanceList();
        assertTrue(maintenances.contains(m1));
    }

    @Test
    void testGetVehicleMaintenance() {
        MaintenanceRepository maintenanceRepository = new MaintenanceRepository();
        maintenanceRepository.addVehicleMaintenance(m1);
        maintenanceRepository.addVehicleMaintenance(m2);
        maintenanceRepository.addVehicleMaintenance(m3);

        List<Vehicle> vehicleList = new ArrayList<Vehicle>();
        vehicleList.add(m1.getVehicle().clone());
        vehicleList.add(m2.getVehicle().clone());
        vehicleList.add(m3.getVehicle().clone());

        List<Maintenance> maintenances = maintenanceRepository.getVehicleMaintenance(vehicleList);
        List<Maintenance> expectedList = new ArrayList<>(maintenanceRepository.getVehicleMaintenance(vehicleList));
        assertArrayEquals(expectedList,maintenances);
    }

    private void assertArrayEquals(List<Maintenance> expectedList, List<Maintenance> maintenances) {
        assertEquals(expectedList.size(), maintenances.size());
        for (int i = 0; i < expectedList.size(); i++) {
            Maintenance expected = expectedList.get(i);
            Maintenance actual = maintenances.get(i);
            assertEquals(expected, actual);
        }
    }



    @Test
    void testGetMaintenanceList() {
    }
}