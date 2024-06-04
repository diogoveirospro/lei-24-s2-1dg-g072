package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.repository.Agenda;

import java.util.List;

public class PostponeAgendaEntryController {
    private final Agenda agenda;

    public PostponeAgendaEntryController (Agenda agenda){
        this.agenda = agenda;
    }

    public boolean POSTponeAgendaEntry (AgendaEntry agendaEntry, Date newStartDate, Date newEndDate) throws InvalidEntryDataException {
        if(agendaEntry == null || newEndDate == null || !newEndDate.isGreater(newStartDate)){
            throw new InvalidEntryDataException("Invalid Dates");
        }
        agendaEntry.setStartDate(newStartDate);
        agendaEntry.setEndDate(newEndDate);
        agendaEntry.taskPostponed();
        return true;
    }

    public List<AgendaEntry> getAgendaEntries(){
        return agenda.getEntryList();
    }

    public AgendaEntry getAgendaEntryByTask(Task task){
        for(AgendaEntry entry: agenda.getEntryList()){
            if (entry.getTask().equals(task)){
                return entry;
            }
        }
        return null;
    }

}
