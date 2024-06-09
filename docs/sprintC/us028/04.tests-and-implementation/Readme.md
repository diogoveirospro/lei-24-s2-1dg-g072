# US028 - Consult Assigned Tasks

## 5. Construction (Implementation)

### Class ListTaskController 

```java
    public List<AgendaEntryDto> getTaskList(AgendaEntry.StatusOfEntry typeStatus, Date startDate, Date endDate) throws InvalidCollaboratorDataException, InvalidEntryDataException {
        Collaborator collaborator = getCollaboratorFromSession();
        List<Team> teamList = this.teamRepository.getTeamsByCollaborator(collaborator);
        List<AgendaEntry> agendaEntryList = this.agenda.getAgendaEntryList(teamList, startDate, endDate, typeStatus);
        AgendaEntryMapper mapper = new AgendaEntryMapper();
        return mapper.toDtoList(agendaEntryList);
    }
```

### Class Agenda

```java
 public List<AgendaEntry> getAgendaEntriesByTeamList(List<Team> teamList) {
    List<AgendaEntry> agendaEntries = new ArrayList<>();
    for (AgendaEntry agendaEntry : entriesAgenda) {
        if (teamList.contains(agendaEntry.getTeam())) {
            agendaEntries.add(agendaEntry);
        }
    }
    return agendaEntries;
}
```

## 6. Integration and Demo 

* A new option on the Collaborator menu options was added, so he can list your tasks .
