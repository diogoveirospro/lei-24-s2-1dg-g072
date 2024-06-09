package pt.ipp.isep.dei.esoft.project.repository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidTaskDataException;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.repository.TaskRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskRepositoryTest {

    private TaskRepository taskRepository;
    private Task task1;
    private Task task2;

    @BeforeEach
    public void setUp() {
        taskRepository = new TaskRepository();
        try {
            task1 = new Task("Task 1", "12");
            task2 = new Task("Task 2", "10");
        } catch (InvalidTaskDataException e) {
            fail("Setup failed: " + e.getMessage());
        }
        taskRepository.addTask(task1);
        taskRepository.addTask(task2);
    }

    @Test
    public void testAddTask() {
        try {
            Task task3 = new Task("Task 3", "8");
            taskRepository.addTask(task3);
            List<Task> tasks = taskRepository.getAllTasks();
            assertEquals(3, tasks.size());
            assertTrue(tasks.contains(task3));
        } catch (InvalidTaskDataException e) {
            fail("Test failed: " + e.getMessage());
        }
    }

    @Test
    public void testRemoveTask() {
        boolean result = taskRepository.removeTask("Task 1");
        List<Task> tasks = taskRepository.getAllTasks();
        assertEquals(1, tasks.size());
        assertFalse(tasks.contains(task1));
        assertTrue(result);
    }

    @Test
    public void testRemoveTaskNotFound() {
        boolean result = taskRepository.removeTask("Task 3");
        List<Task> tasks = taskRepository.getAllTasks();
        assertEquals(2, tasks.size());
        assertFalse(result);
    }

    @Test
    public void testGetAllTasks() {
        List<Task> tasks = taskRepository.getAllTasks();
        assertEquals(2, tasks.size());
        assertTrue(tasks.contains(task1));
        assertTrue(tasks.contains(task2));
    }

    @Test
    public void testFindTaskById() {
        try {
            Task foundTask = taskRepository.findTaskById("Task 1");
            assertEquals(task1, foundTask);
        } catch (InvalidTaskDataException e) {
            fail("Test failed: " + e.getMessage());
        }
    }

    @Test
    public void testFindTaskByIdNotFound() {
        assertThrows(InvalidTaskDataException.class, () -> taskRepository.findTaskById("Task 3"));
    }
}

