# US022 - Add a new Entry in the Agenda 

## 4. Tests 

### Class AgendaEntryTest

**Test 1:** Check if the entry is correctly registered.

    @Test
    void constructor_ValidData_Success() {
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            assertNotNull(agendaEntry);
        } catch (InvalidEntryDataException e) {
            fail("Exception thrown when it shouldn't have been: " + e.getMessage());
        }
    }

**Test 2:** Check if the entry is correctly registered.

    @Test
    void constructor_InvalidData_ExceptionThrown() {
        assertThrows(InvalidEntryDataException.class, () -> new AgendaEntry(null, greenSpace, startDate, startHour, endDate, endHour));
        assertThrows(InvalidEntryDataException.class, () -> new AgendaEntry(task, null, startDate, startHour, endDate, endHour));
        assertThrows(InvalidEntryDataException.class, () -> new AgendaEntry(task, greenSpace, null, startHour, endDate, endHour));
        assertThrows(InvalidEntryDataException.class, () -> new AgendaEntry(task, greenSpace, startDate, null, endDate, endHour));
        assertThrows(InvalidEntryDataException.class, () -> new AgendaEntry(task, greenSpace, startDate, startHour, null, endHour));
        assertThrows(InvalidEntryDataException.class, () -> new AgendaEntry(task, greenSpace, startDate, startHour, endDate, null));
    }

### Class AgendaTest

**Test 1:** Check if the entry is correctly registered.

    @Test
    void createAgendaEntryTest1() throws InvalidEntryDataException, InvalidTaskDataException {
        AgendaEntry agendaEntry = agenda.createAgendaEntry(new Task("Task three", "2"), greenSpace1, new Date(2024, 6, 5),
                AgendaEntry.WorkingDayHours.H09, new Date(2024, 6, 7), AgendaEntry.WorkingDayHours.H10);

        assertEquals(agendaEntry, agendaEntry);
    }

**Test 2:** Check if the entry is correctly registered.

    @Test
    void createAgendaEntryTest2() {
        try {
            AgendaEntry agendaEntry = agenda.createAgendaEntry(task1, greenSpace1, new Date(2024, 6, 7),
                    AgendaEntry.WorkingDayHours.H10, new Date(2024, 6, 5), AgendaEntry.WorkingDayHours.H09);
        }catch (InvalidEntryDataException e){
            assertEquals("The start date of the task cannot be later than the end date.", e.getMessage());
        }
    }

## 5. Construction (Implementation)

### Class AddAgendaEntryController 

```java
    public AgendaEntry createAgendaEntry(Task task, GreenSpace greenSpace, Date startDate, AgendaEntry.WorkingDayHours startHour, Date endDate, AgendaEntry.WorkingDayHours endHour) throws InvalidEntryDataException {
        agenda = Repositories.getInstance().getAgenda();
        return agenda.createAgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
    }

    public boolean addAgendaEntry(AgendaEntry agendaEntry) throws InvalidEntryDataException {
        if (agendaEntry == null) {
            throw new InvalidEntryDataException("Agenda Entry is invalid.");
        }
        return agenda.addAgendaEntry(agendaEntry);
}
```

### Class Agenda

```java
    public boolean addAgendaEntry(AgendaEntry agendaEntry) {
        saveAgendaToFile();
        return entriesAgenda.add(agendaEntry);
    }

    public AgendaEntry createAgendaEntry(Task task, GreenSpace greenSpace, Date startDate, AgendaEntry.WorkingDayHours startHour, Date endDate, AgendaEntry.WorkingDayHours endHour) throws InvalidEntryDataException {
        agenda = Repositories.getInstance().getAgenda();
        return agenda.createAgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
    }
```

## 6. Integration and Demo 

* A new option on the Green Space Manager menu options was added, so he can register a new Agenda Entry .
