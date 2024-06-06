package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repository class for managing tasks.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class TaskRepository implements Serializable {

    private Map<String, Task> tasks;

    /**
     * Constructs a new TaskRepository with an empty task map.
     */
    public TaskRepository() {
        this.tasks = new HashMap<>();
    }

    /**
     * Adds a task to the repository.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        tasks.put(task.getTaskId(), task);
    }

    /**
     * Removes a task from the repository.
     *
     * @param taskId the ID of the task to be removed
     * @return true if the task was successfully removed, false otherwise
     */
    public boolean removeTask(String taskId) {
        return tasks.remove(taskId) != null;
    }

    /**
     * Finds a task by its ID.
     *
     * @param taskId the ID of the task to find
     * @return the found task, or null if not found
     */
    public Task findTaskById(String taskId) {
        return tasks.get(taskId);
    }

    /**
     * Retrieves all tasks stored in the repository.
     *
     * @return a list of all tasks
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }
}
