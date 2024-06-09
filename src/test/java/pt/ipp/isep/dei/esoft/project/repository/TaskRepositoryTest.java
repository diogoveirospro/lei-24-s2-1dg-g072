package pt.ipp.isep.dei.esoft.project.repository;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
    public void setUp() throws InvalidTaskDataException {
        String tempFileName = "taskRepositoryTest.ser";
        taskRepository = new TaskRepository(tempFileName);
        taskRepository.clear();
        task1 = new Task("Task One", "12");
        task2 = new Task("Task Two", "10");
        taskRepository.addTask(task1);
        taskRepository.addTask(task2);
    }

    @AfterEach
    public void tearDown() {
        taskRepository.clear();
    }

    @Test
    public void testAddTask() {
        try {
            Task task3 = new Task("Task Three", "8");
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
        boolean result = taskRepository.removeTask("Task One");
        List<Task> tasks = taskRepository.getAllTasks();
        assertEquals(1, tasks.size());
        assertFalse(tasks.contains(task1));
        assertTrue(result);
    }

    @Test
    public void testRemoveTaskNotFound() {
        boolean result = taskRepository.removeTask("Task Three");
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
            Task foundTask = taskRepository.findTaskById("Task One");
            assertEquals(task1, foundTask);
        } catch (InvalidTaskDataException e) {
            fail("Test failed: " + e.getMessage());
        }
    }

    @Test
    public void testFindTaskByIdNotFound() {

        try {
            taskRepository.findTaskById("Task Three");
        } catch (InvalidTaskDataException e) {
            assertEquals("Task not found", e.getMessage());
        }
    }

}

