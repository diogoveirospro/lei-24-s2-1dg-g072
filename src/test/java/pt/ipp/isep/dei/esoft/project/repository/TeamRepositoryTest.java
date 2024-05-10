package pt.ipp.isep.dei.esoft.project.repository;


import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamProposalController;
import pt.ipp.isep.dei.esoft.project.domain.*;

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

    Collaborator c1 = new Collaborator("Ana", new Date(1990, 2, 3), new Date(2010,
            3, 1), "Rua1", 912345669, "ana@gmail.com", 12345678,
            "CC", 234564321);

    Collaborator c2 = new Collaborator("João", new Date(1980, 2, 3), new Date(2010,
            3, 1), "Rua2", 912345669, "joao@gmail.com", 12345678,
            "CC", 234564321);

    Collaborator c3 = new Collaborator("André", new Date(1970, 2, 3), new Date(2010,
            3, 1), "Rua3", 912345669, "andre@gmail.com", 12345678,
            "CC", 234564321);

    Collaborator c4 = new Collaborator("Manuel", new Date(1999, 2, 3), new Date(2015,
            3, 1), "Rua4", 912345669, "manuel@gmail.com", 12345678,
            "CC", 234564321);

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
     * Test to verify the behavior of generateTeamProposal method in GenerateTeamProposalController.
     * Generates a team proposal with specified skills and collaborators and verifies the generated team members.
     * Ensures that the generated team meets the criteria of having a specific number of members with required skills.
     */
    @Test
    public void testGenerateTeamProposal1() {

        GenerateTeamProposalController controller = new GenerateTeamProposalController();
        CollaboratorRepository collaboratorsRepository = new CollaboratorRepository();
        SkillRepository skillRepository = new SkillRepository();

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
        c4.assignSkill(skill5);


        List<Collaborator> teamMembers = controller.generateTeamProposal(2, 3, skills, collaborators);

        assertEquals(3, teamMembers.size());
        assertTrue(teamMembers.contains(collaborators.get(0)));
        assertTrue(teamMembers.contains(collaborators.get(2)));
        assertTrue(teamMembers.contains(collaborators.get(3)));
    }

    /**
     * Test to verify the behavior of generateTeamProposal method in GenerateTeamProposalController when no collaborators have the required skills.
     * Adds collaborators with specific skills to the repository, attempts to generate a team proposal with specified skills,
     * and expects an IllegalArgumentException to be thrown with a message indicating the lack of collaborators with the required skill.
     */
    @Test
    public void testGenerateTeamProposal2() {
        GenerateTeamProposalController controller = new GenerateTeamProposalController();
        CollaboratorRepository collaboratorsRepository = new CollaboratorRepository();
        SkillRepository skillRepository = new SkillRepository();

        collaboratorsRepository.addCollaborator(c1);
        collaboratorsRepository.addCollaborator(c2);

        List<Collaborator> collaborators = collaboratorsRepository.getCollaborators();

        Skill skill1 = new Skill("Landscape Design");
        Skill skill2 = new Skill("Gardening");

        skillRepository.addSkill(skill1);
        skillRepository.addSkill(skill2);

        List<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);

        c1.assignSkill(skill1);

        try {
            controller.generateTeamProposal(2, 5, skills, collaborators);
            fail("The method should throw an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("There are no collaborators with the specified skill: "));
        }
    }

    /**
     * Test to verify the behavior of generateTeamProposal method in GenerateTeamProposalController when there are not enough collaborators with required skills.
     * Adds a single collaborator with a specific skill to the repository, attempts to generate a team proposal with specified skills,
     * and expects an IllegalArgumentException to be thrown with a message indicating the lack of sufficient collaborators with required skills.
     */
    @Test
    public void testGenerateTeamProposal3() {
        GenerateTeamProposalController controller = new GenerateTeamProposalController();
        CollaboratorRepository collaboratorsRepository = new CollaboratorRepository();
        SkillRepository skillRepository = new SkillRepository();

        collaboratorsRepository.addCollaborator(c1);

        List<Collaborator> collaborators = collaboratorsRepository.getCollaborators();

        Skill skill1 = new Skill("Landscape Design");
        Skill skill2 = new Skill("Gardening");

        skillRepository.addSkill(skill1);
        skillRepository.addSkill(skill2);

        List<Skill> skills = new ArrayList<>();

        c1.assignSkill(skill1);

        try {
            controller.generateTeamProposal(2, 5, skills, collaborators);
            fail("The method should throw an IllegalArgumentException");
        }catch (IllegalArgumentException e){
            assertEquals("There aren't enough collaborators with the specified skills to create a team!", e.getMessage());
        }
    }

    /**
     * Test to verify the behavior of createTeam method in TeamRepository.
     * Creates a team with a list of members, retrieves the created team,
     * and verifies that the retrieved team matches the expected team with the same members.
     */
    @Test
    void testCreateTeam(){

        TeamRepository teamRepository = new TeamRepository();
        List<Collaborator> members = new ArrayList<>();

        members.add(c1);
        members.add(c2);

        Team team = teamRepository.createTeam(members);
        Team expectedTeam = new Team(members);

        assertEquals(expectedTeam, team);

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
