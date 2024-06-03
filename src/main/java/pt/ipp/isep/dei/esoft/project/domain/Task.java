package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidTaskDataException;

import java.util.List;
import java.util.Objects;


public class Task {
    public String duration;
    private String task;

    public String getDuration() {
        return duration;
    }

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
        if (!duration.matches("\\d+")) {
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

    public String getTaskId() {
        return task;
    }

    public boolean equals(Object vehicleObject) {
        if (this == vehicleObject) return true;
        if (vehicleObject == null || getClass() != vehicleObject.getClass()) return false;
        Task task1 = (Task) vehicleObject;
        return duration.equals(task1.duration) &&
                task.equals(task1.task);
    }
    public int hashCode() {
        return Objects.hash(duration, task);
    }


}
