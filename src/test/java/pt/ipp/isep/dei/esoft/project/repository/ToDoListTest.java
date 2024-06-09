package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidTaskDataException;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.ToDoListEntry;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for the ToDoList class.
 */
public class ToDoListTest {

    private static ToDoList toDoList;

    @BeforeEach
    public void setUp() {
        toDoList = new ToDoList();
    }

    @AfterAll
    public static void tearDown() {
        toDoList = null;
    }

    /**
     * Test adding and retrieving a to-do list entry.
     */
    @Test
    public void testAddAndGetToDoListEntry() throws InvalidEntryDataException, InvalidTaskDataException {
        // Arrange
        Task task = new Task("TaskName", "12");
        ToDoListEntry toDoListEntry = new ToDoListEntry(task, null, ToDoListEntry.DegreeOfUrgency.HIGH);

        // Act
        toDoList.addEntry(toDoListEntry);
        ToDoListEntry retrievedEntry = toDoList.getToDoListEntryByTaskName("TaskName");

        // Assert
        assertEquals(toDoListEntry, retrievedEntry);
    }

    /**
     * Test retrieving a non-existent to-do list entry.
     */
    @Test
    public void testGetNonExistentToDoListEntry() {
        // Arrange
        ToDoListEntry retrievedEntry;

        // Act
        retrievedEntry = toDoList.getToDoListEntryByTaskName("NonExistentTaskId");

        // Assert
        assertNull(retrievedEntry);
    }

    /**
     * Test retrieving the status list.
     */
    @Test
    public void testGetStatusList() {
        // Arrange
        List<String> statusList;

        // Act
        statusList = toDoList.getStatusList();

        // Assert
        assertEquals(3, statusList.size());
    }

    /**
     * Test retrieving the degree of urgency list.
     */
    @Test
    public void testGetDegreeOfUrgencyList() {
        // Arrange
        List<String> degreeList;

        // Act
        degreeList = toDoList.getDegreeOfUrgencyList();

        // Assert
        assertEquals(3, degreeList.size());
    }
}
