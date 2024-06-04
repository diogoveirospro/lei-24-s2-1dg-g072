package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing ToDoList entries.
 * Provides methods to retrieve and manage the list of ToDoListEntry objects.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class ToDoList {
    private List<ToDoListEntry> toDoListEntries;

    /**
     * Default constructor for the ToDoList class.
     * Initializes the list of ToDoListEntry objects.
     */
    public ToDoList() {
        this.toDoListEntries = new ArrayList<>();
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
     * Retrieves a ToDoListEntry object by matching the task's hash code.
     *
     * @param taskHashCode the hash code of the task to search for.
     * @return the matching ToDoListEntry object, or null if not found.
     */
    public ToDoListEntry getToDoListEntryByTaskHashCode(int taskHashCode) {
        for (ToDoListEntry toDoListEntry : toDoListEntries) {
            if (toDoListEntry.getTask().hashCode() == taskHashCode) {
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

    public void addEntry(ToDoListEntry toDoListEntry) {
        this.toDoListEntries.add(toDoListEntry);
    }
}
