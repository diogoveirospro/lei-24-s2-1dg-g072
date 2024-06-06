package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidGreenSpaceDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidTaskDataException;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AgendaTest {

    private Agenda agenda;
    private CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
    private TeamRepository teamRepository = Repositories.getInstance().getTeamRepository();

    private Collaborator gsm;
    private Collaborator c1;
    private Collaborator c2;
    private Collaborator c3;
    private Collaborator c4;

    private Team team1;
    private Team team2;

    private AgendaEntry agendaEntry1;
    private Task task1;
    private GreenSpace greenSpace1;

    private AgendaEntry agendaEntry2;
    private Task task2;
    private GreenSpace greenSpace2;



    @BeforeEach
    void setUp() throws InvalidTaskDataException, InvalidCollaboratorDataException, InvalidGreenSpaceDataException, InvalidEntryDataException {
        agenda = new Agenda();

        gsm = new Collaborator("GSM"
        );

        collaboratorRepository.addCollaborator(gsm);

        c1 = new Collaborator("Ana"
        );

        c2 = new Collaborator("João"
        );

        c3 = new Collaborator("André"
        );

        c4 = new Collaborator("Manuel"
        );

        team1 = new Team(List.of(c1, c2));
        team2 = new Team(List.of(c3, c4));

        teamRepository.addTeam(team1);
        teamRepository.addTeam(team2);

        task1 = new Task("Task one", "2");
        greenSpace1 = new GreenSpace(GreenSpace.TypeOfGreenSpace.LPARK, "Cidade", 83,
                "Estrada Interior da Circunvalação, 4100-083 Porto", gsm);


        agendaEntry1 = new AgendaEntry(task1, greenSpace1, new Date(2024, 6, 5),
                AgendaEntry.WorkingDayHours.H09, new Date(2024, 6, 7), AgendaEntry.WorkingDayHours.H10);

        agendaEntry1.addTeam(team1);

        agenda.addAgendaEntry(agendaEntry1);


        task2 = new Task("Task two", "3");
        greenSpace2 = new GreenSpace(GreenSpace.TypeOfGreenSpace.GARDEN, "São Roque", 4,
                "R. São Roque da Lameira 2040, 4350-307 Porto", gsm);

        agendaEntry2 = new AgendaEntry(task2, greenSpace2, new Date(2024, 6, 5), AgendaEntry.WorkingDayHours.H16,
                new Date(2024, 6, 8), AgendaEntry.WorkingDayHours.H17);

        agendaEntry2.addTeam(team2);

        agenda.addAgendaEntry(agendaEntry2);
    }

    @Test
    void getAgendaEntryListTest(){
        List<AgendaEntry> entries = List.of(agendaEntry1, agendaEntry2);

        List<AgendaEntry> result = agenda.getAgendaEntryList(List.of(team1, team2), new Date(2024, 6, 5),
                new Date(2024, 6, 7), AgendaEntry.StatusOfEntry.SCHEDULED);

        assertEquals(entries, result);
    }

    @Test
    void createAgendaEntryTest1() throws InvalidEntryDataException, InvalidTaskDataException {
        AgendaEntry agendaEntry = agenda.createAgendaEntry(new Task("Task three", "2"), greenSpace1, new Date(2024, 6, 5),
                AgendaEntry.WorkingDayHours.H09, new Date(2024, 6, 7), AgendaEntry.WorkingDayHours.H10);

        assertEquals(agendaEntry, agendaEntry);
    }

    @Test
    void createAgendaEntryTest2() {
        try {
            AgendaEntry agendaEntry = agenda.createAgendaEntry(task1, greenSpace1, new Date(2024, 6, 7),
                    AgendaEntry.WorkingDayHours.H10, new Date(2024, 6, 5), AgendaEntry.WorkingDayHours.H09);
        }catch (InvalidEntryDataException e){
            assertEquals("The start date of the task cannot be later than the end date.", e.getMessage());
        }
    }

    @Test
    void getStatusListTest(){
        List<String> statusList = List.of("Scheduled", "Postponed", "Canceled", "Done");

        assertEquals(statusList, agenda.getStatusList());
    }

    @Test
    void getEntryListTest(){
        List<AgendaEntry> entries = List.of(agendaEntry1, agendaEntry2);

        assertEquals(entries, agenda.getEntryList());
    }

    @Test
    void getAgendaEntryListStringTest(){
        List<String> entries = List.of(agendaEntry1.getName(), agendaEntry2.getName());

        List<String> result = agenda.getAgendaEntryList();

        assertEquals(entries, result);
    }

}
