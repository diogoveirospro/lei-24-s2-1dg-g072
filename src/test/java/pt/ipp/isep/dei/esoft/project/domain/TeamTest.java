package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamProposalController;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

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
     * Test to verify the behavior of generateTeamProposal method in GenerateTeamProposalController.
     * Generates a team proposal with specified skills and collaborators and verifies the generated team members.
     * Ensures that the generated team meets the criteria of having a specific number of members with required skills.
     */
    @Test
    void teamTest1(){
        GenerateTeamProposalController controller = new GenerateTeamProposalController();
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


        Team team = controller.generateTeamProposal(2, 3, skills, collaborators);

        assertEquals(3, team.getTeam().size());
        assertTrue(team.getTeam().contains(collaborators.get(0)));
        assertTrue(team.getTeam().contains(collaborators.get(1)));
        assertTrue(team.getTeam().contains(collaborators.get(2)));
    }

    /**
     * Test to verify the behavior of generateTeamProposal method in GenerateTeamProposalController when no collaborators have the required skills.
     * Adds collaborators with specific skills to the repository, attempts to generate a team proposal with specified skills,
     * and expects an IllegalArgumentException to be thrown with a message indicating the lack of collaborators with the required skill.
     */
    @Test
    void teamTest2(){
        GenerateTeamProposalController controller = new GenerateTeamProposalController();
        SkillRepository skillRepository = new SkillRepository();
        CollaboratorRepository collaboratorsRepository = new CollaboratorRepository(skillRepository);

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
            assertTrue(e.getMessage().contains("There are no collaborators with the required skills: "));
        }
    }

    /**
     * Test to verify the behavior of generateTeamProposal method in GenerateTeamProposalController when there are not enough collaborators with required skills.
     * Adds a single collaborator with a specific skill to the repository, attempts to generate a team proposal with specified skills,
     * and expects an IllegalArgumentException to be thrown with a message indicating the lack of sufficient collaborators with required skills.
     */
    @Test
    void teamTest3(){
        GenerateTeamProposalController controller = new GenerateTeamProposalController();
        SkillRepository skillRepository = new SkillRepository();
        CollaboratorRepository collaboratorsRepository = new CollaboratorRepository(skillRepository);

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
     * Test to verify the behavior of generateTeamProposal method in GenerateTeamProposalController when
     * there are not enough collaborators with required skills but there are still collaborators remaining.
     * Adds a single collaborator with no skills to the repository while also adding, three more with the wanted skills
     * attempts to generate a team proposal with specified skills, and expects the program success as it will
     * get the collaborator with no skills to generate the team.
     */
    @Test
    void teamTest4(){
        GenerateTeamProposalController controller = new GenerateTeamProposalController();
        SkillRepository skillRepository = new SkillRepository();
        CollaboratorRepository collaboratorsRepository = new CollaboratorRepository(skillRepository);

        collaboratorsRepository.addCollaborator(c1);
        collaboratorsRepository.addCollaborator(c2);
        collaboratorsRepository.addCollaborator(c3);
        collaboratorsRepository.addCollaborator(c4);
        List<Collaborator> collaborators = collaboratorsRepository.getCollaborators();

        Skill skill1 = new Skill("Landscape Design");
        Skill skill2 = new Skill("Gardening");

        skillRepository.addSkill(skill1);
        skillRepository.addSkill(skill2);

        List<Skill> skills = new ArrayList<>();

        c1.assignSkill(skill1);
        c2.assignSkill(skill2);
        c3.assignSkill(skill1);
        c3.assignSkill(skill2);
        Team team = controller.generateTeamProposal(4, 4, skills, collaborators);

        assertEquals(4, team.getTeam().size());
        assertTrue(team.getTeam().contains(collaborators.get(0)));
        assertTrue(team.getTeam().contains(collaborators.get(1)));
        assertTrue(team.getTeam().contains(collaborators.get(2)));
        assertTrue(team.getTeam().contains(collaborators.get(3)));
    }

    /**
     * Test to verify the behavior of generateTeamProposal method in GenerateTeamProposalController.
     * Generates a team proposal with specified skills and collaborators and verifies the generated team members.
     * Ensures that the generated team meets the criteria of having a specific number of members with required skills.
     */
    @Test
    void teamTest5(){
        GenerateTeamProposalController controller = new GenerateTeamProposalController();
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
        skills.add(skill3);
        skills.add(skill4);
        skills.add(skill5);


        c1.assignSkill(skill1);
        c1.assignSkill(skill2);
        c2.assignSkill(skill3);
        c2.assignSkill(skill4);
        c3.assignSkill(skill5);


        Team team = controller.generateTeamProposal(2, 3, skills, collaborators);

        assertEquals(3, team.getTeam().size());
        assertTrue(team.getTeam().contains(collaborators.get(0)));
        assertTrue(team.getTeam().contains(collaborators.get(1)));
        assertTrue(team.getTeam().contains(collaborators.get(2)));
    }

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
            stringExpected.append(member.getName()).append(" - ").append(member.getIdDocNumber()).append("\n");

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
