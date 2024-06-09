package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.repository.Agenda;

import java.util.List;

/**
 * Controller for postponing agenda entries.
 */

public class PostponeAgendaEntryController {
    private final Agenda agenda;

    /**
     * Constructor for PostponeAgendaEntryController.
     *
     * @param agenda the agenda repository instance
     */

    public PostponeAgendaEntryController (Agenda agenda){
        this.agenda = agenda;
    }

    /**
     * Postpones an agenda entry to new start and end dates.
     *
     * @param agendaEntry the agenda entry to postpone
     * @param newStartDate the new start date
     * @param newEndDate the new end date
     * @return true if the postponement was successful
     * @throws InvalidEntryDataException if the dates are invalid
     */

    public boolean POSTponeAgendaEntry (AgendaEntry agendaEntry, Date newStartDate, Date newEndDate) throws InvalidEntryDataException {
        if(agendaEntry == null || newEndDate == null || !newEndDate.isGreater(newStartDate)){
            throw new InvalidEntryDataException("Invalid Dates");
        }
        agendaEntry.setStartDate(newStartDate);
        agendaEntry.setEndDate(newEndDate);
        agendaEntry.taskPostponed();
        return true;
    }

    /**
     * Gets a list of all agenda entries.
     *
     * @return a list of all agenda entries
     */

    public List<AgendaEntry> getAgendaEntries(){
        return agenda.getEntryList();
    }

    /**
     * Gets an agenda entry by the associated task.
     *
     * @param task the task associated with the agenda entry
     * @return the agenda entry associated with the task, or null if not found
     */

    public AgendaEntry getAgendaEntryByTask(Task task){
        for(AgendaEntry entry: agenda.getEntryList()){
            if (entry.getTask().equals(task)){
                return entry;
            }
        }
        return null;
    }

}
