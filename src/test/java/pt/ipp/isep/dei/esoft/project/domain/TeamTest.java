package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the Team class, checking the behaviour of the
 * getTeam, setTeam, toString and equals methods.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class TeamTest {

    Collaborator c1 = new Collaborator("Ana", new Date(1990, 2, 3), new Date(2010, 3, 1), "Rua1", 912345669, "ana@gmail.com", 12345678, "CC", 234564321);
    Collaborator c2 = new Collaborator("João", new Date(1980, 2, 3), new Date(2010, 3, 1), "Rua2", 912345669, "joao@gmail.com", 12345678, "CC", 234564321);
    Collaborator c3 = new Collaborator("André", new Date(1970, 2, 3), new Date(2010, 3, 1), "Rua3", 912345669, "andre@gmail.com", 12345678, "CC", 234564321);
    Collaborator c4 = new Collaborator("Manuel", new Date(1999, 2, 3), new Date(2015, 3, 1), "Rua4", 912345669, "manuel@gmail.com", 12345678, "CC", 234564321);

    /**
     * Test to verify the behavior of getTeam method in Team class.
     * Creates a team with a list of members, retrieves the team members using getTeam method,
     * and verifies that the retrieved members match the expected members.
     */
    @Test
    void getTeamTest(){
        List<Collaborator> members = new ArrayList<>();
        members.add(c1);
        members.add(c2);

        Team team = new Team(members);
        List<Collaborator> teamMembers = team.getTeam();

        assertEquals(members, teamMembers);

    }

    /**
     * Test to verify the behavior of setTeam method in Team class.
     * Creates a team with an initial list of members, sets a new list of members using setTeam method,
     * and verifies that the team members have been updated to the new list of members.
     */
    @Test
    void setTeamTest(){
        List<Collaborator> members1 = new ArrayList<>();
        members1.add(c1);
        members1.add(c2);

        Team team = new Team(members1);

        List<Collaborator> members2 = new ArrayList<>();
        members2.add(c3);
        members2.add(c4);

        team.setTeam(members2);
        List<Collaborator> teamMembers = team.getTeam();

        assertNotSame(teamMembers, members1);
    }

    /**
     * Test to verify the behavior of toString method in Team class.
     * Creates a team with a list of members, generates the expected string representation manually,
     * and verifies that the generated string matches the string returned by the team's toString method.
     */
    @Test
    void toStringTest(){
        List<Collaborator> members = new ArrayList<>();
        members.add(c1);
        members.add(c2);

        Team team = new Team(members);


        StringBuilder stringExpected = new StringBuilder("The team members are: \n");

        for (Collaborator member : members){
            stringExpected.append("Collaborator{" + "name='").append(member.getName()).append('\'').append
                    (", birthDate=").append(member.getBirthDate()).append(", admissionDate=").
                    append(member.getAdmissionDate()).append(", address='").append(member.getAddress()).append('\'').
                    append(", mobile='").append(member.getMobile()).append('\'').append(", email='").append(member.getEmail())
                    .append('\'').append(", taxpayerNumber='").append(member.getTaxpayerNumber()).append('\'').
                    append(", idDocType='").append(member.getIdDocType()).append('\'').append(", idDocNumber='").
                    append(member.getIdDocNumber()).append('\'').append(", job='").append(member.getJob()).append('\'').
                    append("}\n");

        }

        assertEquals(stringExpected.toString(), team.toString());
    }

    /**
     * Test to verify the behavior of equals method in Team class.
     * Creates multiple teams with different lists of members, and performs equality and inequality checks.
     * Verifies that the equals method correctly identifies equality and inequality based on member lists.
     */
    @Test
    void equalsTest(){
        List<Collaborator> members1 = new ArrayList<>();
        List<Collaborator> members2 = new ArrayList<>();

        members1.add(c1);
        members1.add(c2);
        members2.add(c3);
        members2.add(c4);

        Team team1 = new Team(members1);
        Team team2 = new Team(members2);
        Team team3 = new Team(members2);

        assertEquals(team1, team1);
        assertNotEquals(team1, null);
        assertNotEquals(team1, members1);
        assertNotEquals(team1, team2);
        assertEquals(team2, team3);
    }
}
