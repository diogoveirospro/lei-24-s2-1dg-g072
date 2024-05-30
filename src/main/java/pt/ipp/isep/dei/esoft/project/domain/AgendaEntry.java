package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.Date;

import java.util.ArrayList;
import java.util.List;

public class AgendaEntry extends Entry{

    private ToDoListEntry toDoListEntry;
    private Date startDate;
    private Date endDate;

    public enum StatusOfEntry {
        PLANNED("Planned"),
        POSTPONED("Postponed"),
        CANCELED("Canceled"),
        DONE("Done");
        private final String status;
        StatusOfEntry(String status) {
            this.status = status;
        }
        public String getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return status;
        }
        public static StatusOfEntry getStatusOfEntry(String status) {
            for (StatusOfEntry statusOfEntry : StatusOfEntry.values()) {
                if (statusOfEntry.getStatus().equals(status)) {
                    return statusOfEntry;
                }
            }
            throw new IllegalArgumentException("Invalid status of entry: " + status);
        }
        public static List<String> getStatusList() {
            List<String> statusList = new ArrayList<>();
            for (StatusOfEntry status : StatusOfEntry.values()) {
                statusList.add(status.getStatus());
            }
            return statusList;
        }
    }

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
