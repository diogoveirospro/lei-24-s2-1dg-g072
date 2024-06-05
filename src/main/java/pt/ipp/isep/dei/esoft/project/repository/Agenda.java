package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
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
    public List<AgendaEntry> getAgendaEntryList(List<Team> teamList, Date startDate, Date endDate, AgendaEntry.StatusOfEntry typeStatus) {
        List<AgendaEntry> agendaEntryList = new ArrayList<>();
        for (Team team : teamList) {
            for (AgendaEntry agendaEntry : entriesAgenda) {
                if ((agendaEntry.getTeam().equals(team)) && (agendaEntry.getStartDate().compareTo(startDate) >= 0 && agendaEntry.getEndDate().compareTo(endDate) >= 0) && agendaEntry.getStatus().equals(typeStatus)) {
                    agendaEntryList.add(agendaEntry);
                }
            }
        }
        return agendaEntryList;
    }

    public Agenda(){
        this.entriesAgenda = new ArrayList<>();
    }

    /**
     * Creates a new agenda entry with the specified task, green space, start date, and end date.
     *
     * @param task       the task associated with the agenda entry.
     * @param greenSpace the green space associated with the agenda entry.
     * @param startDate  the start date of the agenda entry.
     * @param startHour  the start hour of the agenda entry.
     * @param endDate    the end date of the agenda entry.
     * @param endHour    the end hour of the agenda entry.
     * @return the newly created AgendaEntry object.
     * @throws InvalidEntryDataException if the provided data is invalid.
     */
    public AgendaEntry createAgendaEntry(Task task, GreenSpace greenSpace, Date startDate, AgendaEntry.HourOfDay startHour,
                                         Date endDate, AgendaEntry.HourOfDay endHour) throws InvalidEntryDataException {

        try {
            return new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
        } catch (InvalidEntryDataException e) {
            throw new InvalidEntryDataException(e.getMessage());
        }
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

    public List<AgendaEntry> getEntryList() {
        List<AgendaEntry> entryList = new ArrayList<>();
        for (AgendaEntry entry : entriesAgenda) {
            entryList.add(entry);
        }
        return entryList;
    }

    public List<String> getAgendaEntryList() {
        List<AgendaEntry> entryList;
        List<String> entryListString = new ArrayList<>();
        entryList = getEntryList();
        for (int i = 0; i < entryList.size(); i++) {
            entryListString.add(entryList.get(i).getName());
        }

        return entryListString;
    }

    public boolean assignTeamToAgendaEntry(AgendaEntry agendaEntry, Team team) {
        return agendaEntry.assignTeam(team);
    }
}