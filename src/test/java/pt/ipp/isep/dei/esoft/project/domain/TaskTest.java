package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidTaskDataException;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TaskRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    private Task task1;
    private Task task2;
    private Task task3;
    private static TaskRepository taskRepository = Repositories.getInstance().getTaskRepository();

    @BeforeEach
    void setUp() throws InvalidTaskDataException {
        task1 = new Task("Task One", "1");
        task2 = new Task("Task Two", "2");
        task3 = new Task("Task Three", "3");
        taskRepository.addTask(task1);
        taskRepository.addTask(task2);
        taskRepository.addTask(task3);
    }

    @Test
    void testTaskInvalidDuration() throws InvalidTaskDataException {
        assertThrows(InvalidTaskDataException.class, () -> new Task("Task", "a"));

    }

    @Test
    void testTaskInvalidName() throws InvalidTaskDataException {
        assertThrows(InvalidTaskDataException.class, () -> new Task("", "1"));
    }

    @Test
    void testTaskNullName() throws InvalidTaskDataException {
        assertThrows(InvalidTaskDataException.class, () -> new Task(null, "1"));
    }

    @Test
    void testTaskInvalidCharactersName() throws InvalidTaskDataException {
        assertThrows(InvalidTaskDataException.class, () -> new Task("123", "1"));
    }

    @Test
    void testTaskNullDuration() throws InvalidTaskDataException {
        assertThrows(InvalidTaskDataException.class, () -> new Task("Task", null));
    }

    @Test
    void testTaskBlankDuration() throws InvalidTaskDataException {
        assertThrows(InvalidTaskDataException.class, () -> new Task("Task", ""));
    }

    @Test
    void testGetTask() {
        assert (task1.getTaskId().equals("Task One"));
    }

    @Test
    void testGetDuration() {
        assert (task1.getDuration().equals("1"));
    }

    @Test
    void testEquals() throws InvalidTaskDataException {
        Task task4 = new Task("Task One", "1");
        assertTrue(task1.equals(task4));
    }

    @Test
    void testNotEquals() {
        assertFalse(task1.equals(task2));
    }

    @Test
    void testEqualsSameObject() {
        assertTrue(task1.equals(task1));
    }

    @Test
    void testEqualsDifferentObject() {
        assertFalse(task1.equals("Task One"));
    }

    @Test
    void testEqualsNullObject() {
        assertFalse(task1.equals(null));
    }
    @Test
    void testEqualsHashCode() throws InvalidTaskDataException {
        Task task4 = new Task("Task One", "1");
        assertEquals(task1.hashCode(), task4.hashCode());
    }
    @Test
    void testNotEqualsHashCode() {
        assertNotEquals(task1.hashCode(), task2.hashCode());
    }
    @Test
    void testEqualsHashCodeSameObject() {
        assertEquals(task1.hashCode(), task1.hashCode());
    }
    @Test
    void testEqualsHashCodeDifferentObject() {
        assertNotEquals(task1.hashCode(), "Task One".hashCode());
    }
    @Test
    void testEqualsHashCodeNullObject() {
        assertNotEquals(task1.hashCode(), Optional.ofNullable(null));
    }

    @AfterAll
    static void tearDown() {
        taskRepository = new TaskRepository();
    }


}
