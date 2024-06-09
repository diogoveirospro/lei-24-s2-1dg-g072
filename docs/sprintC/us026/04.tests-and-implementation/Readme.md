# US026 - Assign one or more Vehicles to an entry 

## 4. Tests 

### Class AgendaEntryTest

**Test 1:** Check if the vehicle list is correctly added to the agenda entry. 

    @Test
    void addVehicleListTest(){
        try {
            Vehicle v1 = new Vehicle("GG-69-EZ","BMW","i4","hibrid",3500.0,
                    4500.0,1000.0,new Date(2024,1,10),
                    new  Date(2024,1,26),10000.0,0.0);

            Vehicle v2 = new Vehicle("69-WP-42","Toyota","Avensis","Diesel",3000.0,
                    4000.0,42000.0,new Date(2018,12,10),
                    new  Date(2019,1,10),20000.0,30000.0);

            List<Vehicle> vehicleList = List.of(v1, v2);

            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            agendaEntry.addVehicleList(vehicleList);
            assertEquals(vehicleList, agendaEntry.getVehicleList());
        } catch (InvalidEntryDataException e) {
            assertEquals("The vehicle is not in the repository.", e.getMessage());
        }
    }

## 5. Construction (Implementation)

### Class AssignVehicleController 

```java
    public boolean assignVehicle(AgendaEntry agendaEntry, Vehicle vehicle) {
        return agenda.assignVehicleToAgendaEntry(agendaEntry, vehicle);
    }
```

### Class Agenda

```java
    public boolean assignVehicleToAgendaEntry(AgendaEntry agendaEntry, Vehicle vehicle) {
        try {
            agendaEntry.assignVehicle(vehicle);
            saveAgendaToFile();
            return true;
        } catch (InvalidEntryDataException e) {
            System.err.println("Erro ao atribuir veículo à entrada da agenda: " + e.getMessage());
            return false;
        }
    }
```

## 6. Integration and Demo 

* A new option on the Green Space Manager menu options was added, so he can assign vehicles to agenda entry .
