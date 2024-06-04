package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;

import java.util.ArrayList;
import java.util.List;

public class Entry {
    private Task task;
    private GreenSpace greenSpace;


    public Entry(Task task, GreenSpace greenSpace) throws InvalidEntryDataException {

        if (task == null) {
            throw new InvalidEntryDataException ("The task cannot be null.");
        } else if (greenSpace == null) {
            throw new InvalidEntryDataException ("The green space cannot be null.");

        }
        this.task = task;
        this.greenSpace = greenSpace;
    }

    public Task getTask() {
        return task;
    }

    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    public void setGreenSpace(GreenSpace greenSpace) {
        this.greenSpace = greenSpace;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
