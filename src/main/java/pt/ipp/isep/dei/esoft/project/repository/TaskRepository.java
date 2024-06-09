package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidTaskDataException;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.repository.data.SerializableRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing tasks.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class TaskRepository extends SerializableRepository<List<Task>> implements Serializable {

    private List<Task> tasks;

    /**
     * Constructs a new TaskRepository with an empty task list.
     */
    public TaskRepository() {
        super("taskRepository.ser");
        tasks = super.load();
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
    }
    public TaskRepository(String filename) {
        super(filename);
        tasks = super.load();
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
    }

    /**
     * Adds a task to the repository.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
        saveTaskRepositoryToFile();
    }

    /**
     * Removes a task from the repository.
     *
     * @param taskId the ID of the task to be removed
     * @return true if the task was successfully removed, false otherwise
     */
    public boolean removeTask(String taskId) {
        boolean b = tasks.removeIf(task -> task.getTaskId().equals(taskId));
        saveTaskRepositoryToFile();
        return b;
    }

    /**
     * Retrieves all tasks stored in the repository.
     *
     * @return a list of all tasks
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Saves the current list of tasks to a file.
     */
    public void saveTaskRepositoryToFile() {
        save(tasks);
    }

    /**
     * Finds a task by its ID.
     *
     * @param taskOne the ID of the task to find
     * @return the task with the specified ID
     * @throws InvalidTaskDataException if no task with the specified ID is found
     */
    public Task findTaskById(String taskOne) throws InvalidTaskDataException {
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskOne)) {
                return task;
            }
        }
        throw new InvalidTaskDataException("Task not found");
    }

    /**
     * Clears the current list of tasks.
     */
    public void clear() {
        if (tasks != null) {
            tasks.clear();
        }
        super.clear();
    }
}
