package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.Date;

public class AgendaEntry extends Entry{
    // Shouldt this be a Entry?
    private ToDoListEntry toDoListEntry;
    private Date startDate;
    private Date endDate;
    private StatusOfEntry status;

    public Entry getEntry() {
        return toDoListEntry.getEntry();
    }

    public AgendaEntry (Task task, GreenSpace greenSpace, Date startDate, Date endDate) {
        super(task, greenSpace);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public StatusOfEntry getStatus() {
        return status;
    }
}
