# US024 - Postpone entry in the Agenda

## 4. Tests 

### Class AgendaEntryTest

**Test 1:** Check if the entry is postponed correctly.

    @Test
    void postponeEntryTest(){
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            agendaEntry.postponeEntry(new Date(2024, 6, 5), new Date(2024, 6, 8));
            agendaEntry.taskPostponed();
            assertEquals(agendaEntry.getStartDate(), new Date(2024, 6, 5));
            assertEquals(AgendaEntry.StatusOfEntry.POSTPONED, agendaEntry.getStatus());
        } catch (InvalidEntryDataException e) {
            fail("Exception thrown when it shouldn't have been: " + e.getMessage());
        }
    }

## 5. Construction (Implementation)

### Class PostponeEntryController 

```java
    public void postponeAgendaEntry(AgendaEntry agendaEntry, Date newDate, Date endDate) throws InvalidEntryDataException, CloneNotSupportedException, InvalidTaskDataException {
        if (agendaEntry == null || newDate == null) {
            throw new InvalidEntryDataException("Agenda Entry is invalid.");
        } else {
            agendaEntry.postponeEntry(newDate, endDate);
            agendaEntry.taskPostponed();
            AgendaEntry newEntry = agendaEntry.cloneEntry();
            agenda.addAgendaEntry(newEntry);

        }
    }
```

## 6. Integration and Demo 

* A new option on the Green Space Manager menu options was added, so he can postpone an agenda entry .

