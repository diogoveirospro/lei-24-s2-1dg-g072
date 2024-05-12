# US007 - Register the inspection of a vehicle 

## 4. Tests 

**Test 1:** Test that sees if a vehicle is added

	@Test
    void testAddVehicleMaintenance() {
        MaintenanceRepository maintenanceRepository = new MaintenanceRepository();
        maintenanceRepository.addVehicleMaintenance(m1);
        List<Maintenance> maintenances = maintenanceRepository.getMaintenanceList();
        assertTrue(maintenances.contains(m1));
    }




_It is also recommended to organize this content by subsections._ 


## 5. Construction (Implementation)

### Class RegisterVehicleMaintenanceController 

```java
/**
 * Register vehicle maintenance
 *
 * @param vehicleList list of all vehicles that need maintenance
 * @return maintenance
 */
public List<Maintenance> registerVehicleMaintenance(List<Vehicle> vehicleList) {
    List<Maintenance> maintenances = new ArrayList<>();
    for (Vehicle vehicle : vehicleList) {
        Maintenance maintenance = new Maintenance(vehicle);
        maintenanceRepository.addVehicleMaintenance(maintenance);
        maintenances.add(maintenance);
    }
    return maintenances;
}
```

### Class MaintenanceRepository

```java
public void addVehicleMaintenance(Maintenance maintenance) {
    checkIfMaintenanceNotNull(!validateVehicleMaintenance(maintenance), "Invalid vehicle that needs maintenance to add");
    maintenanceList.add(maintenance);
}
```


## 6. Integration and Demo 

* A new option on the Maintenance menu options was added.

* For demo purposes some maintenances are bootstrapped while system starts.


## 7. Observations

n/a