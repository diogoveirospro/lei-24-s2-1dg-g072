package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.exceptions.InvalidCollaboratorDataException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CollaboratorRepositoryTest {

    Collaborator c1;

    {
        try {
            c1 = new Collaborator("Ana", new Date(1990, 2, 3), new Date(2010, 3, 1), "Rua1", "912345669", "ana@gmail.com", "123456789", Collaborator.IdDocType.CC, "234564321zx7");
        } catch (InvalidCollaboratorDataException e) {
            throw new RuntimeException(e);
        }
    }

    Collaborator c2;

    {
        try {
            c2 = new Collaborator("João", new Date(1980, 2, 3), new Date(2010, 3, 1), "Rua2", "912345669", "joao@gmail.com", "234567899", Collaborator.IdDocType.BI, "232566381");
        } catch (InvalidCollaboratorDataException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGetCollaborator1() {
        SkillRepository skillRepository = new SkillRepository();
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository(skillRepository);
        collaboratorRepository.addCollaborator(c1);
        collaboratorRepository.addCollaborator(c2);

        Collaborator collaborator = collaboratorRepository.getCollaborator("232566381");

        assertEquals(c2, collaborator);
    }

    @Test
    void testGetCollaborator2() {
        SkillRepository skillRepository = new SkillRepository();
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository(skillRepository);
        collaboratorRepository.addCollaborator(c1);
        collaboratorRepository.addCollaborator(c2);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> collaboratorRepository.getCollaborator("123456789"));
        assertEquals("The collaborator whose ID number is 123456789 does not exist.", exception.getMessage());
    }

    @Test
    void testAddCollaborator1() {
        SkillRepository skillRepository = new SkillRepository();
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository(skillRepository);
        collaboratorRepository.addCollaborator(c1);

        assertTrue(collaboratorRepository.getCollaborators().contains(c1));
    }

    @Test
    void testAddCollaborator2() {
        SkillRepository skillRepository = new SkillRepository();
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository(skillRepository);
        collaboratorRepository.addCollaborator(c1);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> collaboratorRepository.addCollaborator(c1));
        assertEquals("Invalid collaborator to add", exception.getMessage());
    }

    @Test
    void testGetCollaborators() {
        SkillRepository skillRepository = new SkillRepository();
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository(skillRepository);
        collaboratorRepository.addCollaborator(c1);
        collaboratorRepository.addCollaborator(c2);

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(c1);
        collaborators.add(c2);

        assertEquals(collaboratorRepository.getCollaborators(), collaborators);
    }

    @Test
    void testAssignSkill1() {
        SkillRepository skillRepository = new SkillRepository();
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository(skillRepository);
        collaboratorRepository.addCollaborator(c1);
        collaboratorRepository.addCollaborator(c2);

        Skill skill1 = new Skill("Landscape Design");
        Skill skill2 = new Skill("Gardening");
        Skill skill3 = new Skill("Agriculture");

        skillRepository.addSkill(skill1);
        skillRepository.addSkill(skill2);
        skillRepository.addSkill(skill3);

        collaboratorRepository.assignSkill(c1, skill1);

        assertTrue(c1.getSkillSet().contains(skill1));
    }

    @Test
    void testAssignSkill2() {
        SkillRepository skillRepository = new SkillRepository();
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository(skillRepository);

        Skill skill1 = new Skill("Landscape Design");
        Skill skill2 = new Skill("Gardening");
        Skill skill3 = new Skill("Agriculture");

        skillRepository.addSkill(skill1);
        skillRepository.addSkill(skill2);
        skillRepository.addSkill(skill3);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> collaboratorRepository.assignSkill(c1, skill1));
        assertEquals("Collaborator not found in repository", exception.getMessage());
    }

    @Test
    void testAssignSkill3() {
        SkillRepository skillRepository = new SkillRepository();
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository(skillRepository);
        collaboratorRepository.addCollaborator(c1);
        collaboratorRepository.addCollaborator(c2);

        Skill skill1 = new Skill("Landscape Design");
        Skill skill2 = new Skill("Gardening");
        Skill skill3 = new Skill("Agriculture");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> collaboratorRepository.assignSkill(c1, skill1));
        assertEquals("Invalid skill", exception.getMessage());
    }
}
