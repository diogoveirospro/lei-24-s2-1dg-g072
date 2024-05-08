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
        List<Vehicle> vehicleList = new ArrayList<Vehicle>();
        Vehicle v1 = new Vehicle(m1.getVehicle());
        Vehicle v2 = new Vehicle(m2.getVehicle());
        Vehicle v3 = new Vehicle(m3.getVehicle());
        vehicleList.add(v1);
        vehicleList.add(v2);
        vehicleList.add(v3);
        List<Maintenance> maintenances = maintenanceRepository.getVehicleMaintenance(vehicleList);
        maintenanceRepository.addVehicleMaintenance(m1);
        maintenanceRepository.addVehicleMaintenance(m2);
        maintenanceRepository.addVehicleMaintenance(m3);
        assertSame(maintenances,maintenanceRepository.getMaintenanceList());
    }

    @Test
    void testGetMaintenanceList() {
    }
}