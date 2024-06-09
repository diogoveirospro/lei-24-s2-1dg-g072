# US025 - Cancel entry in the agenda. 

## 4. Tests 

### Class AgendaEntryTest

**Test 1:** Check if method cancelEntry() is working. 

    @Test
    void cancelEntryTest(){
        try{
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            agendaEntry.postponeEntry(new Date(2024, 6, 5), new Date(2024, 6, 8));
            agendaEntry.taskPostponed();
            assertEquals(agendaEntry.getStartDate(), new Date(2024, 6, 5));
            assertEquals(AgendaEntry.StatusOfEntry.CANCELED, agendaEntry.getStatus());
        } catch (InvalidEntryDataException e) {
            fail("Exception thrown when it shouldn't have been: " + e.getMessage());
        }
    }



## 5. Construction (Implementation)

### Class CancelEntryController

```java
    public void cancelAgendaEntry(AgendaEntry agendaEntry) throws InvalidEntryDataException {
        if (agendaEntry == null) {
            throw new InvalidEntryDataException("Agenda Entry is invalid.");
        } else
            agendaEntry.taskCanceled();
    }
```


## 6. Integration and Demo 

* A new option on the Green Space Manager menu options was added, so he can cancel an agenda entry .

