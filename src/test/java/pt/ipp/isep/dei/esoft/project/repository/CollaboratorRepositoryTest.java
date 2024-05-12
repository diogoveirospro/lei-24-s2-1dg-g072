package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CollaboratorRepository class.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class CollaboratorRepositoryTest {

    private CollaboratorRepository collaboratorRepository;
    private List<Collaborator> collaborators;

    @BeforeEach
    public void setUp() {
        collaboratorRepository = new CollaboratorRepository();
    }

    /**
     * Test adding a new collaborator to the repository.
     */
    @Test
    public void testAddCollaborator() {
        // Arrange
        Collaborator collaborator = new Collaborator("Ana", new Date(1990, 2, 3), new Date(2010,
                3, 1), "Rua1", 912345669, "ana@gmail.com", 12345678,
                "CC", 234564321);

        // Act
        collaboratorRepository.addCollaborator(collaborator);

        // Assert
        assertTrue(collaboratorRepository.getCollaborators().contains(collaborator));
    }

    /**
     * Test adding an invalid collaborator to the repository.
     */
    @Test
    public void testAddInvalidCollaborator() {
        // Arrange
        Collaborator existingCollaborator = new Collaborator("Ana", new Date(1990, 2, 3), new Date(2010,
                3, 1), "Rua1", 912345669, "ana@gmail.com", 12345678,
                "CC", 234564321);
        collaboratorRepository.addCollaborator(existingCollaborator);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> collaboratorRepository.addCollaborator(existingCollaborator));
    }

    /**
     * Test retrieving a collaborator from the repository by name.
     */
    @Test
    public void testGetCollaborator() {
        // Arrange
        Collaborator collaborator = new Collaborator("Ana", new Date(1990, 2, 3), new Date(2010,
                3, 1), "Rua1", 912345669, "ana@gmail.com", 12345678,
                "CC", 234564321);
        collaboratorRepository.addCollaborator(collaborator);

        // Act
        Collaborator retrievedCollaborator = collaboratorRepository.getCollaborator("Ana");

        // Assert
        assertNotNull(retrievedCollaborator);
        assertEquals(collaborator, retrievedCollaborator);
    }

    /**
     * Test retrieving a non-existing collaborator from the repository.
     */
    @Test
    public void testGetNonExistingCollaborator() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> collaboratorRepository.getCollaborator("Nonexistent"));
    }

    /**
     * Test getting the list of collaborators from the repository.
     */
    @Test
    public void testGetCollaborators() {
        // Arrange
        Collaborator collaborator1 = new Collaborator("Ana", new Date(1990, 2, 3), new Date(2010,
                3, 1), "Rua1", 912345669, "ana@gmail.com", 12345678,
                "CC", 234564321);
        Collaborator collaborator2 = new Collaborator("João", new Date(1980, 2, 3), new Date(2010,
                3, 1), "Rua2", 912345669, "joao@gmail.com", 12345678,
                "CC", 234564321);
        collaboratorRepository.addCollaborator(collaborator1);
       collaboratorRepository.addCollaborator(collaborator2);

        // Act
        List<Collaborator> collaborators = collaboratorRepository.getCollaborators();

        // Assert
        assertNotNull(collaborators);
        assertEquals(2, collaborators.size());
        assertTrue(collaborators.contains(collaborator1));
        assertTrue(collaborators.contains(collaborator2));
    }

    @Test
    void testAssignSkill_ValidSkill_AssignedSuccessfully() {
        Collaborator newCollaborator = new Collaborator("João", new Date(1980, 2, 3), new Date(2010,
                3, 1), "Rua2", 912345669, "joao@gmail.com", 12345678,
                "CC", 234564321);;
        Skill skill = new Skill("Programming");
        collaboratorRepository.addCollaborator(newCollaborator);
        collaboratorRepository.assignSkill(newCollaborator, skill);
        assertTrue(newCollaborator.getSkillSet().contains(skill));
    }

    @Test
    void testAssignSkill_InvalidSkill_ExceptionThrown() {
        Collaborator newCollaborator = new Collaborator("João", new Date(1980, 2, 3), new Date(2010,
                3, 1), "Rua2", 912345669, "joao@gmail.com", 12345678,
                "CC", 234564321);;
        Skill skill = new Skill("Invalid Skill");
        collaboratorRepository.addCollaborator(newCollaborator);
        assertThrows(IllegalArgumentException.class, () -> collaboratorRepository.assignSkill(newCollaborator, skill));
    }
}
