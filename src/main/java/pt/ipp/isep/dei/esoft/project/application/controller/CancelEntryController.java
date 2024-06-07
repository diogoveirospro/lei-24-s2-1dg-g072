package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.repository.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;
import java.util.Objects;

public class CancelEntryController {

    /**
     * The Agenda
     */
    private final Agenda agenda;

    /**
     * The List of Agenda Entries
     */
    private final List<AgendaEntry> agendaEntries;

    /**
     * Instantiates a new Cancel entry controller.
     */
    public CancelEntryController() {
        this.agenda = Repositories.getInstance().getAgenda();
        this.agendaEntries = agenda.getEntryList();
    }

    /**
     * Instantiates a new Cancel entry controller.
     *
     * @param agenda               the agenda
     * @param greenSpaceRepository the green space repository
     */
    public CancelEntryController(Agenda agenda, GreenSpaceRepository greenSpaceRepository) {
        this.agenda = agenda;
        this.agendaEntries = agenda.getEntryList();
    }

    /**
     * Changes the status of an agenda entry to canceled
     * @param agendaEntry the agenda entry
     * @throws InvalidEntryDataException if the entry is invalid
     */
    public void cancelAgendaEntry(AgendaEntry agendaEntry) throws InvalidEntryDataException {
        if (agendaEntry == null) {
            throw new InvalidEntryDataException("Agenda Entry is invalid.");
        } else
            agendaEntry.taskCanceled();
    }

    /**
     * Gets the list of agenda entries as Strings
     * @return List of Strings
     */
    public List<String> getAgendaEntryListString() {
        return agenda.getAllAgendaEntryList();
    }

    /**
     * Gets the agenda entry by task name
     * @param taskName the task name
     * @return AgendaEntry
     */
    public AgendaEntry getAgendaEntryByTaskName(String taskName) {
        try {
            for (AgendaEntry entry : agendaEntries) {
                if (Objects.equals(entry.getName(), taskName)) {
                    return entry;
                }
            }
        }catch (Exception e){
            System.out.println("Error while getting the agenda entry by task name.");
        }
        return null;
    }
}