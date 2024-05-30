package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.Date;

import java.util.ArrayList;
import java.util.List;

public class AgendaEntry extends Entry{

    private Team team;
    private ToDoListEntry toDoListEntry;
    private Date startDate;
    private Date endDate;
    private StatusOfEntry status = StatusOfEntry.PLANNED;
    private List<Vehicle> vehicleList;

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


    public AgendaEntry (Task task, Date startDate, Date endDate) {
        super(task);
        this.startDate = startDate;
        this.endDate = endDate;
        this.vehicleList = new ArrayList<>();
        this.team = null;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public ToDoListEntry getToDoListEntry() {
        return toDoListEntry;
    }

    public StatusOfEntry getStatus() {
        return status;
    }

    public Team getTeam() {
        return team;
    }


    public void setToDoListEntry(ToDoListEntry toDoListEntry) {
        this.toDoListEntry = toDoListEntry;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

}
