package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidTaskDataException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an entry in the agenda.
 * An entry consists of a task and a green space.
 *
 * @author Group 072 - Byte Masters - ISEP
 */

public class Entry implements Serializable {
    private Task task;
    private GreenSpace greenSpace;

    /**
     * Constructs a new Entry with the specified task and green space.
     *
     * @param task      the task associated with the entry
     * @param greenSpace the green space associated with the entry
     * @throws InvalidEntryDataException if the task or green space is null
     */

    public Entry(Task task, GreenSpace greenSpace) throws InvalidEntryDataException {

        if (task == null) {
            throw new InvalidEntryDataException ("The task cannot be null.");
        } else if (greenSpace == null) {
            throw new InvalidEntryDataException ("The green space cannot be null.");

        }
        this.task = task;
        this.greenSpace = greenSpace;
    }

    /**
     * Gets the task associated with this entry.
     *
     * @return the task associated with this entry
     */

    public Task getTask() {
        return task;
    }

    /**
     * Gets the green space associated with this entry.
     *
     * @return the green space associated with this entry
     */

    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    /**
     * Sets the green space associated with this entry.
     *
     * @param greenSpace the green space to set
     */

    public void setGreenSpace(GreenSpace greenSpace) {
        this.greenSpace = greenSpace;
    }

    /**
     * Sets the task associated with this entry.
     *
     * @param task the task to set
     */

    public void setTask(Task task) {
        this.task = task;
    }

    /**
     * Clones the task associated with this entry.
     *
     * @return a clone of the task associated with this entry
     * @throws InvalidTaskDataException if the task data is invalid
     */

    protected Task cloneTask() throws InvalidTaskDataException {
        return new Task(this.task.getTaskId(), this.task.getDuration());
    }
}
