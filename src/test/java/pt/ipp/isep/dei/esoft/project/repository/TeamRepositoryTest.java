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

    Collaborator c1 = new Collaborator("Ana", new Date(1990, 2, 3), new Date(2010, 3, 1), "Rua1", 912345669, "ana@gmail.com", 12345678, "CC", 234564321);
    Collaborator c2 = new Collaborator("João", new Date(1980, 2, 3), new Date(2010, 3, 1), "Rua2", 912345669, "joao@gmail.com", 12345678, "CC", 234564321);
    Collaborator c3 = new Collaborator("André", new Date(1970, 2, 3), new Date(2010, 3, 1), "Rua3", 912345669, "andre@gmail.com", 12345678, "CC", 234564321);
    Collaborator c4 = new Collaborator("Manuel", new Date(1999, 2, 3), new Date(2015, 3, 1), "Rua4", 912345669, "manuel@gmail.com", 12345678, "CC", 234564321);


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

    @Test
    void testAddTeam2(){

        TeamRepository teamRepository = new TeamRepository();
        List<Collaborator> members = new ArrayList<>();

        Team team = new Team(members);

        teamRepository.addTeam(team);

        List<Team> teams = teamRepository.getTeams();

        assertFalse(teams.contains(team));

    }

    @Test
    void testValidateTeam1(){

        TeamRepository teamRepository = new TeamRepository();
        List<Collaborator> members = new ArrayList<>();

        Team team = new Team(members);

        assertFalse(teamRepository.validateTeam(team));
    }

    @Test
    void testValidateTeam2(){

        TeamRepository teamRepository = new TeamRepository();

        assertFalse(teamRepository.validateTeam(null));
    }
}
