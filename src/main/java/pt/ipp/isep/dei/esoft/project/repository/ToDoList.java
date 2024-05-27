package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Task;

import java.util.List;

public class ToDoList extends Entry {
    private List<ToDoList> toDoListEntries;

    public List<String> getStatusList() {
        return StatusOfEntry.getStatusList();
    }
}
