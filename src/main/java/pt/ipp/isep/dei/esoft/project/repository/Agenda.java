package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing agenda entries.
 * Provides methods to retrieve, create, and add entries to the agenda.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class Agenda {
    private List<AgendaEntry> entriesAgenda;

    /**
     * Retrieves a list of entries for the specified tasks within the given date range.
     *
     * @param taskList the list of tasks to filter entries.
     * @param startDate the start date of the range to filter entries.
     * @param endDate the end date of the range to filter entries.
     * @return a list of entries matching the specified tasks and date range.
     */
    public List<Entry> getEntryList(List<Task> taskList, Date startDate, Date endDate) {
        List<Entry> entryList = new ArrayList<>();
        for (Task task : taskList) {
            for (AgendaEntry entry : entriesAgenda) {
                if (entry.getTask().equals(task) && entry.getStartDate().compareTo(startDate) <= 0 && entry.getEndDate().compareTo(endDate) >= 0) {
                    entryList.add(entry.getEntry());
                }
            }
        }
        return entryList;
    }

    /**
     * Creates a new agenda entry with the specified task, green space, start date, and end date.
     *
     * @param task the task associated with the agenda entry.
     * @param greenSpace the green space associated with the agenda entry.
     * @param startDate the start date of the agenda entry.
     * @param endDate the end date of the agenda entry.
     * @return the newly created AgendaEntry object.
     */
    public AgendaEntry createAgendaEntry(Task task, GreenSpace greenSpace, Date startDate, Date endDate) {
        return new AgendaEntry(task, greenSpace, startDate, endDate);
    }

    /**
     * Adds a new agenda entry to the agenda.
     *
     * @param agendaEntry the agenda entry to be added.
     * @return true if the agenda entry was added successfully, false otherwise.
     */
    public boolean addAgendaEntry(AgendaEntry agendaEntry) {
        return entriesAgenda.add(agendaEntry);
    }

    public List<String> getStatusList() {
        return AgendaEntry.StatusOfEntry.getStatusList();
    }
}
