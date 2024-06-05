package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.repository.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;
import java.util.Objects;

public class PostponeEntryController {

    /**
     * The Agenda
     */
    private final Agenda agenda;

    /**
     * The List of Agenda Entries
     */
    private List<AgendaEntry> agendaEntries;

    /**
     * Instantiates a new Postpone entry controller.
     */
    public PostponeEntryController() {
        this.agenda = Repositories.getInstance().getAgenda();
        this.agendaEntries = agenda.getEntryList();
    }

    /**
     * Instantiates a new Postpone entry controller.
     * @param agenda the agenda
     * @param greenSpaceRepository the green space repository
     */
    public PostponeEntryController(Agenda agenda, GreenSpaceRepository greenSpaceRepository) {
        this.agenda = agenda;
        this.agendaEntries = agenda.getEntryList();
    }

    /**
     * Gets the list of agenda entries as Strings
     * @return List of Strings
     */
    public List<String> getAgendaEntryListString() {
        return agenda.getAgendaEntryList();
    }

    /**
     * Postpones an agenda entry
     * @param agendaEntry the agenda entry
     * @param newDate the new date
     * @throws InvalidEntryDataException the invalid entry data exception
     */
    public void postponeAgendaEntry(AgendaEntry agendaEntry, Date newDate) throws InvalidEntryDataException {
        if (agendaEntry == null || newDate == null) {
            throw new InvalidEntryDataException("Agenda Entry is invalid.");
        } else
            agendaEntry.postponeEntry(newDate);
        agendaEntry.taskPostponed();
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
