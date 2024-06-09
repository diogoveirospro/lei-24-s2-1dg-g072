package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.data.SerializableRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing ToDoList entries.
 * Provides methods to retrieve and manage the list of ToDoListEntry objects.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class ToDoList extends SerializableRepository<List<ToDoListEntry>> implements Serializable {
    private List<ToDoListEntry> toDoListEntries;

    /**
     * Default constructor for the ToDoList class.
     * Initializes the list of ToDoListEntry objects.
     */
    public ToDoList() {
        super("toDoList.ser");
        toDoListEntries = super.load();
    }

    /**
     * Retrieves the list of status strings from AgendaEntry.StatusOfEntry.
     *
     * @return a list of status strings.
     */
    public List<String> getStatusList() {
        return AgendaEntry.StatusOfEntry.getStatusList();
    }

    /**
     * Retrieves a to-do list entry by task name.
     *
     * @param taskName the name of the task associated with the entry
     * @return the to-do list entry with the specified task name, or null if not found
     */

    public ToDoListEntry getToDoListEntryByTaskName(String taskName) {
        for (ToDoListEntry toDoListEntry : toDoListEntries) {
            if (toDoListEntry.getTask().getTaskId().equals(taskName)) {
                return toDoListEntry;
            }
        }
        return null;
    }

    /**
     * Retrieves the list of all ToDoListEntry objects.
     *
     * @return a list of ToDoListEntry objects.
     */
    public List<ToDoListEntry> getToDoListEntries() {
        return this.toDoListEntries;
    }
    public List<String> getDegreeOfUrgencyList() {
        List<String> degreeList = new ArrayList<>();
        for (ToDoListEntry.DegreeOfUrgency degree : ToDoListEntry.DegreeOfUrgency.values()) {
            degreeList.add(degree.getDegree());
        }
        return degreeList;
    }

    /**
     * Adds a to-do list entry to the to-do list.
     *
     * @param toDoListEntry the to-do list entry to add
     */

    public void addEntry(ToDoListEntry toDoListEntry) {
        this.toDoListEntries.add(toDoListEntry);
        save(toDoListEntries);
    }

    /**
     * Saves the to-do list entries to a file.
     */

    public void saveToDoListToFile() {
        save(toDoListEntries);
    }
}
