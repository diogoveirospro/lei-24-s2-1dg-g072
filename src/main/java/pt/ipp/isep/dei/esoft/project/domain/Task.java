package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidTaskDataException;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a Task in a project domain.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class Task implements Serializable {
    /**
     * The duration of the task.
     */
    public String duration;
    /**
     * The name of the task.
     */
    private String task;

    /**
     * Constructs a new Task with the specified task name and duration.
     *
     * @param task     the name of the task
     * @param duration the duration of the task
     * @throws InvalidTaskDataException if the task name or duration is invalid
     */
    public Task(String task, String duration) throws InvalidTaskDataException {
        if (isValidTask(task)) {
            this.task = task;
        } else {
            throw new InvalidTaskDataException("Invalid task");
        }

        if (isValidDuration(duration)) {
            this.duration = duration;
        } else {
            throw new InvalidTaskDataException("Invalid duration");
        }
    }

    private boolean isValidDuration(String duration) throws InvalidTaskDataException {
        if (duration == null || duration.isBlank()) {
            throw new InvalidTaskDataException("Invalid Input. The duration of the task cannot be empty or blank.");
        } else if (!duration.matches("\\d+")) {
            throw new InvalidTaskDataException("Invalid Input. The duration of the task must be a number.");
        } else {
            return true;
        }
    }

    /**
     * Validates a task name.
     *
     * @param task the task to validate
     * @return true if the task is valid
     * @throws InvalidTaskDataException if the task is null, blank, or contains invalid characters
     */
    private static boolean isValidTask(String task) throws InvalidTaskDataException {
        if (task == null || task.isBlank()) {
            throw new InvalidTaskDataException("Invalid input. The task cannot be empty or blank.");
        } else if (!task.matches("[a-zA-ZÀ-ÿ ]+")) {
            throw new InvalidTaskDataException("Invalid input. The task must not contain numbers or special characters, and must be composed of letters and spaces only.");
        } else {
            return true;
        }
    }

    /**
     * Retrieves the duration of the task.
     *
     * @return the duration of the task
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Retrieves the name of the task.
     *
     * @return the name of the task
     */
    public String getTaskId() {
        return task;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param vehicleObject the object to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object vehicleObject) {
        if (this == vehicleObject) return true;
        if (vehicleObject == null || getClass() != vehicleObject.getClass()) return false;
        Task task1 = (Task) vehicleObject;
        return duration.equals(task1.duration) &&
                task.equals(task1.task);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(duration, task);
    }
}
