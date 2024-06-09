# US023 - Assign a Team to an Entry in the Agenda

## 4. Tests 

### Class AgendaEntryTest

**Test 1:** Check if the team is added to the list of teams in the agenda entry.

    @Test
    void addTeam(){
        try {
            Collaborator c1 = new Collaborator("Ana", new Date(1990, 2, 3),
                    new Date(2010, 3, 1), "Rua1", "912345669", "ana@collaborator.com",
                    "123456789", Collaborator.IdDocType.CC, "234564321zx7", "ABC1234");

            Collaborator c2 = new Collaborator("João", new Date(1980, 2, 3), new Date(2010,
                    3, 1), "Rua2", "912345669", "joao@collaborator.com", "234567899",
                    Collaborator.IdDocType.BI, "232566381", "ABC1234");

            List<Collaborator> members = List.of(c1, c2);
            Team team = new Team(members);
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            agendaEntry.addTeam(team);
        } catch (InvalidCollaboratorDataException ignored) {

        } catch (InvalidEntryDataException e) {
            assertEquals("The team is not in the repository.", e.getMessage());
        }
    }

## 5. Construction (Implementation)

### Class AssignTeamController 

```java
    public boolean assignTeamToAgendaEntry (AgendaEntry agendaEntry, Team team){
        return agenda.assignTeamToAgendaEntry(agendaEntry, team);
    }
```

### Class Agenda

```java
    public boolean assignTeamToAgendaEntry(AgendaEntry agendaEntry, Team team) {
        agendaEntry.assignTeam(team);
        saveAgendaToFile();
        return true;
    }
```

## 6. Integration and Demo 

* A new option on the Green Space Manager menu options was added, so he can assign team to agenda entry .
