package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

public class Entry {
    private Task task;


    public Entry(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }


    protected void setTask(Task task) {

    }
}
