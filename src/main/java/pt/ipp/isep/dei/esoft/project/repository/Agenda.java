package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidAgendaEntryDataException;
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
     * @param teamList   the list of tasks to filter entries.
     * @param startDate  the start date of the range to filter entries.
     * @param endDate    the end date of the range to filter entries.
     * @param typeStatus the status of the entries to filter.
     * @return a list of entries matching the specified tasks and date range.
     */
    public List<AgendaEntry> getAgendaEntryList(List<Team> teamList, Date startDate, Date endDate, String typeStatus) {
        List<AgendaEntry> agendaEntryList = new ArrayList<>();
        for (Team team : teamList) {
            for (AgendaEntry agendaEntry : entriesAgenda) {
                if ((agendaEntry.getTeam().equals(team)) && (agendaEntry.getStartDate().compareTo(startDate) >= 0 && agendaEntry.getEndDate().compareTo(endDate) >= 0) && agendaEntry.getStatus().toString().equals(typeStatus)) {
                    agendaEntryList.add(agendaEntry);
                }
            }
        }
        return agendaEntryList;
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
    public AgendaEntry createAgendaEntry(Task task, GreenSpace greenSpace, Date startDate, Date endDate) throws InvalidAgendaEntryDataException {
        return new AgendaEntry(task, greenSpace,startDate, endDate);
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

    public List<AgendaEntry> getEntriesAgenda() {
        return entriesAgenda;
    }
    public List<AgendaEntry> getEntryList() {
        return new ArrayList<>(entriesAgenda);
    }
}