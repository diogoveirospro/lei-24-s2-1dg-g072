# US006 - Register a vehicle 

## 4. Tests 

### Class VehicleTest

**Test 1:** Check if method getPlateNumber() is working. 

    @Test
    void getPlateNumberTest(){
        assertEquals(v1.getPlateNumber(), "11-AA-22");
    }
	

**Test 2:** Check if method getCurrentKms is working. 

    @Test
    void getCurrentKmsTest(){
        assertEquals(v1.getCurrentKms(), 30000);
    }

**Test 3:** Check if method getGrossWeight is working.

    ±±±@Test
    void getGrossWeightTest(){
        assertEquals(v1.getGrossWeight(), 3000);
    }

**Test 4:** Check if method getKMAtLastMaintenance is working.

    @Test
    void getKmAtLastMaintenanceTest(){
        assertEquals(v1.getKmAtLastMaintenance(), 25000);
    }


**Test 5:** Check if method getServiceFrequency is working.
   
    @Test
    void getServiceFrequencyTest(){
        assertEquals(v1.getServiceFrequency(), 10000);
    }

**Test 6:** Check if method getTare is working.

    @Test
    void getTareTest(){
        assertEquals(v1.getTare(), 4000);
    }

**Test 7:** Check if the method getAcquisitionDate is working.

    @Test
    void getAcquisitionDateTest(){
        assertEquals(v1.getAcquisitionDate(), new Date(2020, 5, 20));
    }

**TEst 8:** Check if the method getRegistrationDate is working.

    @Test
    void getRegistrationDateTest(){
        assertEquals(v1.getRegistrationDate(), new Date(2020, 4, 19));
    }

**Test 10:** Check if the method getBrand is working.

    @Test
    void getBrandTest(){
       assertEquals(v1.getBrand(), "toyota");
    }

**Test 11:** Check if method getModel is working.

    @Test
    void getModelTest(){
        assertEquals(v1.getModel(), "avensis");
    }

**Test 12:** Check if method getType is working.

    @Test
    void getTypeTest(){
        assertEquals(v1.getType(), "combust");
    }

**Test 13:** Check if method setAcquisitionDate is working.

    @Test
    void setAcquisitionDateTest(){
        v1.setAcquisitionDate(new Date(2020, 7, 10));
        assertEquals(v1.getAcquisitionDate(), new Date(2020, 7, 10));
    }

**Test 15:** Check if method setRegistrationDate is working.

    @Test
    void setRegistrationDateTest(){
        v1.setRegistrationDate(new Date(2020, 7, 10));
        assertEquals(v1.getRegistrationDate(), new Date(2020, 7, 10));
    }


**Test 16:** Check if method setBrand is working.

    @Test
    void setBrandTest(){
        v1.setBrand("ferrari");
        assertEquals(v1.getBrand(), "ferrari");
    }

**Test 17:** Check if method setCurrentKms is working.

    @Test
    void setCurrentKmsTest(){
        v1.setCurrentKms(10000.0);
        assertEquals(v1.getCurrentKms(), 10000);
    }

**Test 18:** Check if method setKmAtrLastMaintenance is working.

    @Test
    void setKmAtLastMaintenanceTest(){
        v1.setKmAtLastMaintenance(10000.0);
        assertEquals(v1.getKmAtLastMaintenance(), 10000);
    }

**Test 19:** Check if method setGrossWeight is working.

    @Test
    void setGrossWeight(){
        v1.setGrossWeight(5000.0);
        assertEquals(v1.getGrossWeight(), 5000);
    }

**Test 20:** Check if method setModel is working.

    void setModelTest(){
        v1.setModel("aviento");
        assertEquals(v1.getModel(), "aviento");
    }

**Test 21:** Check if method setPlateNumber is working,

    @Test
    void setPlateNumberTest(){
        v1.setPlateNumber("BB-AA-12");
        assertEquals(v1.getPlateNumber(), "BB-AA-12");
    }

**Test 22:** Check if method setServiceFrequency is working.

    @Test
    void setServiceFrequencyTest(){
        v1.setServiceFrequency(15000.0);
        assertEquals(v1.getServiceFrequency(), 15000);
    }

**Test 23:** Check if method setTare is working.

    @Test
    void setTareTest(){
        v1.setTare(4000.0);
        assertEquals(v1.getTare(), 4000);
    }

**Test 24:** Check if method setType is working.

    @Test
    void setTypeTest(){
        v1.setType("electric");
        assertEquals(v1.getType(), "electric");
    }

**Test 25:** Check if method validateVehicle is working,

    @Test
    void validateVehicleTest(){
        assertFalse(v4.validateVehicle());
        assertTrue(v1.validateVehicle());
    }

**Test 26:** Check if method equals is working.

    @Test
    void equalsTest(){
        assertNotEquals(v1, v4);
        assertEquals(v1, v5);
    }

**Test 27:** Check if getVehicleYear is working.

    @Test
    void getVehicleYearTest(){
        assertEquals(v1.getVehicleYear(), 2020);
    }

**Test 28:** Check if method clone is working.

    @Test
    void cloneTest(){
        assertEquals(v1, v1.clone());
    }

### Class VehicleRepositoryTest

**Test 1:** Check if the vehicle is added to the list in vehicle repository.

    @Test
    void testAddVehicle(){
        vehicleRepository.addVehicle(v1);
        vehicleRepository.addVehicle(v2);
        vehicleRepository.addVehicle(v3);

        List<Vehicle> vehicleList = vehicleRepository.getVehicleList();

        assertTrue(vehicleList.contains(v1));
        assertTrue(vehicleList.contains(v2));
        assertTrue(vehicleList.contains(v3));

    }

**Test 2:** Check if the vehicle is correctly registered.

    @Test
    void testRegisterVehicle() {

        String plate = "11-AA-22";
        String brand = "Toyota";
        String model = "Corolla";
        String type = "Sedan";
        double tare = 1500.0;
        double grossWeight = 2000.0;
        double currentKms = 10000.0;
        Date registrationDate = new Date(2020, 5, 20);
        Date acquisitionDate = new Date(2020, 6, 19);
        double serviceFrequency = 5000.0;
        double kmAtLastMaintenance = 8000.0;

        Optional<Vehicle> result = vehicleRepository.registerVehicle(plate, brand, model, type, tare, grossWeight, currentKms, registrationDate, acquisitionDate, serviceFrequency, kmAtLastMaintenance);

        assertTrue(result.isPresent());
        Vehicle registeredVehicle = result.get();
        assertEquals(plate, registeredVehicle.getPlateNumber());
        assertEquals(brand, registeredVehicle.getBrand());
        assertEquals(model, registeredVehicle.getModel());
        assertEquals(type, registeredVehicle.getType());
        assertEquals(tare, registeredVehicle.getTare(), 0.01);
        assertEquals(grossWeight, registeredVehicle.getGrossWeight(), 0.01);
        assertEquals(currentKms, registeredVehicle.getCurrentKms(), 0.01);
        assertEquals(registrationDate, registeredVehicle.getRegistrationDate());
        assertEquals(acquisitionDate, registeredVehicle.getAcquisitionDate());
        assertEquals(serviceFrequency, registeredVehicle.getServiceFrequency(), 0.01);
        assertEquals(kmAtLastMaintenance, registeredVehicle.getKmAtLastMaintenance(), 0.01);
    }

## 5. Construction (Implementation)

### Class RegisterVehicleController 

```java
    public Optional<Vehicle> registerVehicle(String plate, String model, String brand, String type, Double tare, Double grossWeight, Double currentKms, Date registrationDate, Date acquisitionDate, Double serviceFrequency, Double kmAtLastMaintenance) {

        Optional<VehicleRepository> vehicleRepository = Optional.ofNullable(getVehicleRepository());
        Optional<Vehicle> newVehicle = Optional.empty();

        if(vehicleRepository.isPresent()){
        newVehicle = vehicleRepository.get().registerVehicle(plate, model, brand, type, tare, grossWeight, currentKms, registrationDate, acquisitionDate, serviceFrequency, kmAtLastMaintenance);
        }
        return newVehicle;
        }
```

### Class VehicleRepository

```java
 public Optional<Vehicle> registerVehicle(String plate, String brand, String model, String type, Double tare, Double grossWeight, Double currentKms, Date registrationDate, Date acquisitionDate, Double serviceFrequency, Double kmAtLastMaintenance){

        Optional<Vehicle> vehicle = Optional.empty();
        Vehicle newVehicle = new Vehicle(plate, brand, model, type, tare, grossWeight, currentKms, registrationDate, acquisitionDate, serviceFrequency, kmAtLastMaintenance);
        if (addVehicle(newVehicle)){
        vehicle = Optional.of(newVehicle);
        }
        return vehicle;
        }

public boolean addVehicle(Vehicle newVehicle){
        boolean success = false;
        if (checkVehicleInList(newVehicle) || (newVehicle.validateVehicle())){
        success = vehicleList.add(newVehicle.clone());
        }else {
        throw new IllegalArgumentException("Invalid vehicle to add");
        }
        return success;
        }
        
private boolean checkVehicleInList(Vehicle vehicle){
        return !vehicleList.contains(vehicle);
        }
        
public List<Vehicle> getVehicleList() {
        return (List.copyOf(vehicleList));
        }
```

## 6. Integration and Demo 

* A new option on the Vehicle Fleet Manager menu options was added, so he can register a new vehicle .

* For demo purposes some vehicles are bootstrapped while system starts.
