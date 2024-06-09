# US025 - Cancel entry in the agenda

## 4. Tests

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
