package pt.ipp.isep.dei.esoft.project.repository;


import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the TeamRepository class. It ensures that
 * the TeamRepository behaves as expected by testing various methods such as
 * adding teams, retrieving teams, and generating team proposals.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class TeamRepositoryTest {

    Collaborator c1;

    {
        try {
            c1 = new Collaborator("Ana");
        } catch (InvalidCollaboratorDataException e) {
            throw new RuntimeException(e);
        }
    }

    Collaborator c2;

    {
        try {
            c2 = new Collaborator("João");
        } catch (InvalidCollaboratorDataException e) {
            throw new RuntimeException(e);
        }
    }

    Collaborator c3;

    {
        try {
            c3 = new Collaborator("André");
        } catch (InvalidCollaboratorDataException e) {
            throw new RuntimeException(e);
        }
    }

    Collaborator c4;

    {
        try {
            c4 = new Collaborator("Manuel");
        } catch (InvalidCollaboratorDataException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Test to verify the behavior of getTeams method in TeamRepository.
     * Retrieves teams from TeamRepository and verifies the retrieved teams.
     */
    @Test
    void testGetTeams(){

        List<Collaborator> members1 = new ArrayList<>();
        List<Collaborator> members2 = new ArrayList<>();

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

    /**
     * Test to verify the behavior of getTeam method in TeamRepository.
     * Adds a team to TeamRepository and retrieves the team using getTeam method.
     * Verifies if the retrieved team matches the expected team.
     */
    @Test
    void testGetTeam1(){

        List<Collaborator> members = new ArrayList<>();

        members.add(c1);
        members.add(c2);

        TeamRepository teamRepository = new TeamRepository();
        teamRepository.addTeam(new Team(members));
        Team team = teamRepository.getTeam(members);
        Team expectedTeam = new Team(members);

        assertEquals(expectedTeam, team);

    }

    /**
     * Test to verify the behavior of getTeam method in TeamRepository when attempting to retrieve a non-existent team.
     * Verifies that an IllegalArgumentException is thrown with the correct error message.
     */
    @Test
    void testGetTeam2(){
        List<Collaborator> members = new ArrayList<>();

        members.add(c1);
        members.add(c2);

        TeamRepository teamRepository = new TeamRepository();

        try {
            teamRepository.getTeam(members);
            fail("The method should throw an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("The team composed of the members provided does not exist.", e.getMessage());
        }

    }

    /**
     * Test the generateTeamProposal method of TeamRepository class.
     * This test verifies whether the method generates a team of the correct size
     * and includes the expected collaborators with the specified skills.
     */
    @Test
    void testGenerateTeamProposal(){
        SkillRepository skillRepository = new SkillRepository();
        CollaboratorRepository collaboratorsRepository = new CollaboratorRepository(skillRepository);

        collaboratorsRepository.addCollaborator(c1);
        collaboratorsRepository.addCollaborator(c2);
        collaboratorsRepository.addCollaborator(c3);
        collaboratorsRepository.addCollaborator(c4);

        List<Collaborator> collaborators = collaboratorsRepository.getCollaborators();

        Skill skill1 = new Skill("Landscape Design");
        Skill skill2 = new Skill("Gardening");
        Skill skill3 = new Skill("Agriculture");
        Skill skill4 = new Skill("Sustainable Land Management");
        Skill skill5 = new Skill("Ecological Restoration");

        skillRepository.addSkill(skill1);
        skillRepository.addSkill(skill2);
        skillRepository.addSkill(skill3);
        skillRepository.addSkill(skill4);
        skillRepository.addSkill(skill5);

        List<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);
        skills.add(skill1);

        c1.assignSkill(skill1);
        c1.assignSkill(skill2);
        c2.assignSkill(skill3);
        c2.assignSkill(skill4);
        c3.assignSkill(skill1);
        c4.assignSkill(skill5);
        c4.assignSkill(skill2);


        Team team = TeamRepository.generateTeamProposal(2, 3, skills, collaborators);

        assertEquals(3, team.getTeam().size());
        assertTrue(team.getTeam().contains(collaborators.get(0)));
        assertTrue(team.getTeam().contains(collaborators.get(1)));
        assertTrue(team.getTeam().contains(collaborators.get(2)));
    }

    /**
     * Test to verify the behavior of addTeam method in TeamRepository when adding a new team.
     * Creates a team with a list of members, adds the team to the repository,
     * retrieves all teams from the repository, and verifies that the added team is present in the list of teams.
     */
    @Test
    void testAddTeam1(){

        TeamRepository teamRepository = new TeamRepository();
        List<Collaborator> members = new ArrayList<>();

        members.add(c1);
        members.add(c2);

        Team team = new Team(members);

        teamRepository.addTeam(team);

        List<Team> teams = teamRepository.getTeams();

        assertTrue(teams.contains(team));

    }

    /**
     * Test to verify the behavior of addTeam method in TeamRepository when adding an empty team.
     * Creates an empty team, adds the team to the repository,
     * retrieves all teams from the repository, and verifies that the empty team is not present in the list of teams.
     */
    @Test
    void testAddTeam2(){

        TeamRepository teamRepository = new TeamRepository();
        List<Collaborator> members = new ArrayList<>();

        Team team = new Team(members);

        teamRepository.addTeam(team);

        List<Team> teams = teamRepository.getTeams();

        assertFalse(teams.contains(team));

    }

    /**
     * Test to verify the behavior of validateTeam method in TeamRepository with an empty team.
     * Creates an empty team and checks if the repository correctly validates that the team is invalid.
     */
    @Test
    void testValidateTeam1(){

        TeamRepository teamRepository = new TeamRepository();
        List<Collaborator> members = new ArrayList<>();

        Team team = new Team(members);

        assertFalse(teamRepository.validateTeam(team));
    }

    /**
     * Test to verify the behavior of validateTeam method in TeamRepository with a null team.
     * Checks if the repository correctly validates that a null team is invalid.
     */
    @Test
    void testValidateTeam2(){

        TeamRepository teamRepository = new TeamRepository();

        assertFalse(teamRepository.validateTeam(null));
    }
}
