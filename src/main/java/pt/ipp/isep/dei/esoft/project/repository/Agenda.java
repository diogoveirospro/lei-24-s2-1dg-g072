package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.data.SerializableRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing agenda entries.
 * Provides methods to retrieve, create, and add entries to the agenda.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class Agenda extends SerializableRepository<List<AgendaEntry>> implements Serializable {
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
                if (typeStatus != null){
                    if ((agendaEntry.getTeam().equals(team)) && (agendaEntry.getStartDate().compareTo(startDate) >= 0 && agendaEntry.getEndDate().compareTo(endDate) >= 0) && agendaEntry.getStatus().equals(typeStatus)) {
                        agendaEntryList.add(agendaEntry);
                    }
                } else {
                    if ((agendaEntry.getTeam().equals(team)) && (agendaEntry.getStartDate().compareTo(startDate) >= 0 && agendaEntry.getEndDate().compareTo(endDate) >= 0)) {
                        agendaEntryList.add(agendaEntry);
                    }
                }
            }
        }
        return agendaEntryList;
    }

    public Agenda() {
        super("agenda.ser");
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
    public AgendaEntry createAgendaEntry(Task task, GreenSpace greenSpace, Date startDate, AgendaEntry.WorkingDayHours startHour,
                                         Date endDate, AgendaEntry.WorkingDayHours endHour) throws InvalidEntryDataException {

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

    /**
     * Gets the list of agenda entries with scheduled status as Strings.
     *
     * @return
     */
    public List<String> getAgendaEntryList() {
        List<AgendaEntry> entryList;
        List<String> entryListString = new ArrayList<>();
        entryList = getEntryList();
        for (int i = 0; i < entryList.size(); i++) {
            if (entryList.get(i).getStatus().equals(AgendaEntry.StatusOfEntry.SCHEDULED)) {
                entryListString.add(entryList.get(i).getName());
            }
        }

        return entryListString;
    }

    /**
     * Gets the list of all agenda entries as Strings.
     * @return List of Strings
     */
    public List<String> getAllAgendaEntryList() {
        List<AgendaEntry> entryList;
        List<String> entryListString = new ArrayList<>();
        entryList = getEntryList();
        for (int i = 0; i < entryList.size(); i++) {
            if (!entryList.get(i).getStatus().equals(AgendaEntry.StatusOfEntry.CANCELED)) {
                entryListString.add(entryList.get(i).getName());
            }
        }
        return entryListString;
    }


    public boolean assignTeamToAgendaEntry(AgendaEntry agendaEntry, Team team) {
        return agendaEntry.assignTeam(team);
    }

    public List<AgendaEntry> getAgendaEntriesWithoutTeam() {

        List<AgendaEntry> agendaEntriesWithoutTeam = new ArrayList<>();
        for (AgendaEntry agendaEntry : entriesAgenda) {
            if (agendaEntry.getTeam() == null) {
                agendaEntriesWithoutTeam.add(agendaEntry);
            }
        }
        return agendaEntriesWithoutTeam;

    }

    public AgendaEntry getAgendaEntry(String selectedAgendaEntryName) {
        for (AgendaEntry agendaEntry : entriesAgenda) {
            if (agendaEntry.getName().equals(selectedAgendaEntryName)) {
                return agendaEntry;
            }
        }
        return null;
    }

    public List<AgendaEntry> getAgendaEntriesWithoutVehicle() {
        List<AgendaEntry> entriesWithoutVehicle = new ArrayList<>();
        for (AgendaEntry entry : entriesAgenda) {
            if (entry.getAssignedVehicle()== null) {
                entriesWithoutVehicle.add(entry);
            }
        }
        return entriesWithoutVehicle;
    }

    public boolean assignVehicleToAgendaEntry(AgendaEntry agendaEntry, Vehicle vehicle) {
        try {

            agendaEntry.assignVehicle(vehicle);
            return true;
        } catch (InvalidEntryDataException e) {

            System.err.println("Erro ao atribuir veículo à entrada da agenda: " + e.getMessage());
            return false;
        }
    }

}