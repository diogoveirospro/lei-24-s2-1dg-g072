package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TeamRepositoryTest {

    Collaborator c1 = new Collaborator("Ana", new Date(1990, 2, 3), new Date(2010, 3, 1), "Rua1", 912345669, "ana@gmail.com", "CC", 234564321);
    Collaborator c2 = new Collaborator("João", new Date(1980, 2, 3), new Date(2010, 3, 1), "Rua2", 912345669, "joao@gmail.com", "CC", 234564321);
    Collaborator c3 = new Collaborator("André", new Date(1970, 2, 3), new Date(2010, 3, 1), "Rua3", 912345669, "andre@gmail.com", "CC", 234564321);
    Collaborator c4 = new Collaborator("Manuel", new Date(1999, 2, 3), new Date(2015, 3, 1), "Rua4", 912345669, "manuel@gmail.com", "CC", 234564321);
    List<Collaborator> members1 = new ArrayList<>();
    List<Collaborator> members2 = new ArrayList<>();

    @Test
    void testGetTeam(){

        members1.add(c1);
        members1.add(c2);
        members2.add(c3);
        members2.add(c4);

        List<Team> teams = List.of(new Team(members1), new Team(members2));

        TeamRepository teamRepository = new TeamRepository();
        teamRepository.addTeam(new Team(members1));
        teamRepository.addTeam(new Team(members2));

        List<Team> retrievedTeams = teamRepository.getTeams();

        assertThrows(UnsupportedOperationException.class, () -> retrievedTeams.add(new Team(members2)));

        assertEquals(2, retrievedTeams.size());
        assertTrue(retrievedTeams.containsAll(teams));
    }
}
