package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;
import java.util.List;

public class AgendaEntry extends Entry{
    // Shouldt this be a Entry?
    private ToDoListEntry toDoListEntry;
    private Date startDate;
    private Date endDate;
    private StatusOfEntry status;

    public Entry getEntry() {
        return toDoListEntry.getEntry();
    }
}
