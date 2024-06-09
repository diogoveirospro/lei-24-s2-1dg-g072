# US029 - Record the Completion of a Task

## 4. Tests 

### Class AgendaEntryTest

**Test 1:** Check if the task is correctly completed.

    @Test
    void taskScheduleTest(){
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);

            agendaEntry.taskPostponed();
            assertEquals(AgendaEntry.StatusOfEntry.POSTPONED, agendaEntry.getStatus());

            agendaEntry.taskSchedule();
            assertEquals(AgendaEntry.StatusOfEntry.SCHEDULED, agendaEntry.getStatus());

            agendaEntry.taskCanceled();
            assertEquals(AgendaEntry.StatusOfEntry.CANCELED, agendaEntry.getStatus());

            agendaEntry.taskDone();
            assertEquals(AgendaEntry.StatusOfEntry.DONE, agendaEntry.getStatus());


        } catch (InvalidEntryDataException e) {
            fail("Exception thrown when it shouldn't have been: " + e.getMessage());
        }
    }

## 5. Construction (Implementation)

### Class FinishEntryController 

```java
    public void finishTask(String taskId) throws InvalidTaskDataException {
        AgendaEntry agendaEntry = agenda.getAgendaEntry(taskId);
        agendaEntry.taskDone();
    }
```

## 6. Integration and Demo 

* A new option on the Collaborator menu options was added, so he can finish your tasks .
