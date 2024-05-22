package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Maintenance;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaintenanceRepositoryTest {
    VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
    Vehicle vehicle1 = new Vehicle("GG-69-EZ","BMW","i4","hibrid",3500.0,4500.0,1000.0,new Date(2024,1,10), new  Date(2024,1,26),10000.0,0.0);
    Vehicle vehicle2 = new Vehicle("69-WP-42","Toyota","Avensis","Diesel",3000.0,4000.0,42000.0,new Date(2018,12,10), new  Date(2019,1,10),20000.0,30000.0);
    Vehicle vehicle3 = new Vehicle("04-20-VC","Ferrari","Diablo","Petrol",3000.0,4000.0,100000.0,new Date(2000,12,10), new  Date(2003,10,11),10000.0,75432.3);

    @Test
    void getVehicleList() {
        MaintenanceRepository maintenanceRepository = Repositories.getInstance().getMaintenanceRepository();
        vehicleRepository.addVehicle(vehicle1);
        vehicleRepository.addVehicle(vehicle2);
        vehicleRepository.addVehicle(vehicle3);
        List<Vehicle> vehicleList = new ArrayList<>(vehicleRepository.getVehicleList());
        vehicleList = maintenanceRepository.getVehicleList(vehicleList);
        List<Vehicle> expected = new ArrayList<>();
        expected.add(vehicle3);
        assertEquals(expected, vehicleList);
    }

    @Test
    void addVehicleMaintenance1() {
        MaintenanceRepository maintenanceRepository = Repositories.getInstance().getMaintenanceRepository();
        vehicleRepository.addVehicle(vehicle3);
        Maintenance maintenance1 = new Maintenance(vehicle3);
        maintenanceRepository.addVehicleMaintenance(maintenance1);
        assertEquals(1, maintenanceRepository.getMaintenanceList().size());

        maintenance1.setKmAtMaintenance(80000.0);
        maintenanceRepository.addVehicleMaintenance(maintenance1);
        assertEquals(1, maintenanceRepository.getMaintenanceList().size());
        assertEquals(80000.0, maintenanceRepository.getMaintenanceList().get(0).getKmAtMaintenance());

        vehicleRepository.addVehicle(vehicle2);
        Maintenance maintenance2 = new Maintenance(vehicle2);
        maintenanceRepository.addVehicleMaintenance(maintenance2);
        assertEquals(1, maintenanceRepository.getMaintenanceList().size());
    }

    @Test
    void addVehicleMaintenance2() {
        MaintenanceRepository maintenanceRepository = Repositories.getInstance().getMaintenanceRepository();
        vehicleRepository.addVehicle(vehicle1);
        vehicleRepository.addVehicle(vehicle2);
        Maintenance maintenance1 = new Maintenance(vehicle1);
        Maintenance maintenance2 = new Maintenance(vehicle2);
        maintenanceRepository.addVehicleMaintenance(maintenance1);
        maintenanceRepository.addVehicleMaintenance(maintenance2);
        List<Maintenance> maintenanceList = maintenanceRepository.getMaintenanceList();
        assertEquals(0, maintenanceList.size());
        vehicleRepository.addVehicle(vehicle3);
        Maintenance maintenance3 = new Maintenance(vehicle3);
        maintenanceRepository.addVehicleMaintenance(maintenance3);
        maintenanceList = maintenanceRepository.getMaintenanceList();
        assertEquals(1,maintenanceList.size());
    }

    @Test
    void getMaintenanceListNull(){
        MaintenanceRepository maintenanceRepository = Repositories.getInstance().getMaintenanceRepository();
        Maintenance maintenanceNull = null;
        assertThrows(IllegalArgumentException.class, () -> maintenanceRepository.addVehicleMaintenance(maintenanceNull));

    }
}
