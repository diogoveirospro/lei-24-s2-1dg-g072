# US008 - List vehicles that need maintenance 

## 4. Tests 

**Test 1:** Check that it is not possible to list with not registered maintenance vehicles. - AC1

**Test 1.1:** Test when they are registered.


    private void assertArrayEquals(List<Maintenance> expectedList, List<Maintenance> testList) {
        assertEquals(expectedList.size(), testList.size());
        for (int i = 0; i < expectedList.size(); i++) {
            Maintenance expected = expectedList.get(i);
            Maintenance actual = testList.get(i);
            assertEquals(expected, actual);
        }
    }

    @Test
    void testGetMaintenanceList() {
        MaintenanceRepository maintenanceRepository = new MaintenanceRepository();
        maintenanceRepository.addVehicleMaintenance(m1);
        maintenanceRepository.addVehicleMaintenance(m2);
        maintenanceRepository.addVehicleMaintenance(m3);
        List<Maintenance> maintenances = maintenanceRepository.getMaintenanceList();
        List<Maintenance> expected = new ArrayList<>();
        expected.add(m1);
        expected.add(m2);
        expected.add(m3);
        assertArrayEquals(expected, maintenances);
    }

**Test 1.2:** When the vehicles are not registered, and we attempt to get some of them.

    @Test
    void testCheckIfMaintenanceNotNull1() {
        MaintenanceRepository maintenanceRepository = new MaintenanceRepository();
        List<Vehicle> vehicleList = new ArrayList<Vehicle>();
        vehicleList.add(m1.getVehicle().clone());
        vehicleList.add(m2.getVehicle().clone());
        vehicleList.add(m3.getVehicle().clone());
        assertThrows(IllegalArgumentException.class, () -> maintenanceRepository.getVehicleMaintenance(vehicleList));
    }

**Test 1.3:** When the vehicles are not registered, and we attempt to get the list of all of them.

	@Test
    void testCheckIfMaintenanceNotNull2() {
        MaintenanceRepository maintenanceRepository = new MaintenanceRepository();
        assertThrows(IllegalArgumentException.class, maintenanceRepository::getMaintenanceList);
    }

**Test 2:** This test will check if the vehicle didn't have maintenance for more kms than the frequency of maintenance. 

**Test 2.1:** When it didn't still hit the km for maintenance.

	@Test
    void setVehicleMaintenance1() {
        Vehicle v1 = m1.getVehicle();
        m1.setVehicleMaintenance(v1);
        assertEquals(25000,m1.getVehicle().getKmAtLastMaintenance());
    }

**Test 2.2:** When it does hit the km for maintenance.

    @Test
    void setVehicleMaintenance2(){
        Vehicle v3 = m3.getVehicle();
        m3.setVehicleMaintenance(v3);
        assertEquals(40000,m3.getVehicle().getKmAtLastMaintenance());
    }

_It is also recommended to organize this content by subsections._ 


## 5. Construction (Implementation)

### Class ListMaintenanceController 

```java
public List<Maintenance> getMaintenanceList(){
    return maintenanceRepository.getMaintenanceList();

}

/**
 * Changes the value of the last maintenance if the difference between the current kms and the last maintenance is greater than the service frequency
 *
 * @param maintenance of the vehicle
 */
public void checkLastMaintenance(Maintenance maintenance) {
    maintenance.setVehicleMaintenance(maintenance.getVehicle());
}
```

### Class ListMaintenanceUI

```java

/**
 * Shows the list of all vehicles that need maintenance
 *
 */
private void submitData() {
    maintenances = controller.getMaintenanceList();
    if (!maintenances.isEmpty()) {
        System.out.println("\nVehicles in need of maintenance");
        for (Maintenance maintenance : maintenances) {
            controller.checkLastMaintenance(maintenance);
            System.out.println("|  Plate  |  Brand  |  Model  | Curr.Kms |  Freq  |  Last  |  Next  |" );
            System.out.printf("| %10s | %10s | %10s | %10f | %10f | %10f | %10f |", maintenance.getVehicle().getPlateNumber(), maintenance.getVehicle().getBrand(), maintenance.getVehicle().getModel(),maintenance.getVehicle().getCurrentKms(),maintenance.getVehicle().getServiceFrequency(),maintenance.getVehicle().getKmAtLastMaintenance(),maintenance.getVehicle().getKmAtLastMaintenance() + maintenance.getVehicle().getServiceFrequency());
            System.out.printf("%n");
            System.out.printf("%n");
        }
    } else {
        System.out.println("\nThere are no vehicles maintenances in the system!");
    }
}
```

### Class MaintenanceRepository
```java
    /**
    * Checks if vehicle exists or not
    *
    * @param aux if true the vehicle doesn't exist else it exists
    * @param vehicle message of the vehicle status
    */
    private static void checkIfMaintenanceNotNull(boolean aux, String vehicle) {
        if (aux) {
        throw new IllegalArgumentException(vehicle);
        }
    }
    /**
     * Lets the user get the list of all vehicles that need maintenance
     *
     * @return maintenanceList
     */
    public List<Maintenance> getMaintenanceList() {
        checkIfMaintenanceNotNull(maintenanceList.isEmpty(),"List has no Vehicles registered!!");
        return List.copyOf(maintenanceList);
    }
```


## 6. Integration and Demo 

* A new option on the VFM menu options was added.

* For demo purposes some Maintenance are bootstrapped while system starts.


## 7. Observations

n/a