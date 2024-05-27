package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<AgendaEntry> entriesAgenda;

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
}
