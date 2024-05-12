package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CollaboratorRepository class.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class CollaboratorRepositoryTest {

    private CollaboratorRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new CollaboratorRepository();
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
        repository.addCollaborator(collaborator);

        // Assert
        assertTrue(repository.getCollaborators().contains(collaborator));
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
        repository.addCollaborator(existingCollaborator);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> repository.addCollaborator(existingCollaborator));
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
        repository.addCollaborator(collaborator);

        // Act
        Collaborator retrievedCollaborator = repository.getCollaborator("Ana");

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
        assertThrows(IllegalArgumentException.class, () -> repository.getCollaborator("Nonexistent"));
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
        repository.addCollaborator(collaborator1);
        repository.addCollaborator(collaborator2);

        // Act
        List<Collaborator> collaborators = repository.getCollaborators();

        // Assert
        assertNotNull(collaborators);
        assertEquals(2, collaborators.size());
        assertTrue(collaborators.contains(collaborator1));
        assertTrue(collaborators.contains(collaborator2));
    }
}
