package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;
import java.util.List;

public class ToDoListEntry {
    private GreenSpace greenSpace;
    private Task task;
    private Date startDate;
    private Date endDate;
    private DegreeOfUrgency degree;
    private StatusOfEntry status;
    private Team team;
    private List<Vehicle> vehicleList;

    // Constructor
    public ToDoListEntry(GreenSpace greenSpace, Task task, Date startDate, Date endDate, DegreeOfUrgency degree, StatusOfEntry status, Team team, List<Vehicle> vehicleList) {
        this.greenSpace = greenSpace;
        this.task = task;
        this.startDate = startDate;
        this.endDate = endDate;
        this.degree = degree;
        this.status = status;
        this.team = team;
        this.vehicleList = vehicleList;
    }

    // Getters
    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    public Task getTask() {
        return task;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public DegreeOfUrgency getDegree() {
        return degree;
    }

    public StatusOfEntry getStatus() {
        return status;
    }

    public Team getTeam() {
        return team;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    // Setters
    public void setGreenSpace(GreenSpace greenSpace) {
        this.greenSpace = greenSpace;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setDegree(DegreeOfUrgency degree) {
        this.degree = degree;
    }

    public void setStatus(StatusOfEntry status) {
        this.status = status;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    // Enum for Degree of Urgency
    public enum DegreeOfUrgency {
        LOW("Low"),
        MEDIUM("Medium"),
        HIGH("High");

        private final String degree;

        DegreeOfUrgency(String degree) {
            this.degree = degree;
        }

        public String getDegree() {
            return degree;
        }

        @Override
        public String toString() {
            return degree;
        }

        public static DegreeOfUrgency getDegreeOfUrgency(String degree) {
            for (DegreeOfUrgency degreeOfUrgency : DegreeOfUrgency.values()) {
                if (degreeOfUrgency.getDegree().equals(degree)) {
                    return degreeOfUrgency;
                }
            }
            throw new IllegalArgumentException("Invalid degree of urgency: " + degree);
        }
    }

    // Enum for Status of Entry
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

    // Override toString() method to provide a string representation of the ToDoListEntry
    @Override
    public String toString() {
        return "ToDoListEntry{" +
                "greenSpace=" + greenSpace +
                ", task=" + task +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", degree=" + degree +
                ", status=" + status +
                ", team=" + team +
                ", vehicleList=" + vehicleList +
                '}';
    }

    // Override equals() method to compare two ToDoListEntry objects
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToDoListEntry that = (ToDoListEntry) o;

        if (greenSpace != null ? !greenSpace.equals(that.greenSpace) : that.greenSpace != null) return false;
        if (task != null ? !task.equals(that.task) : that.task != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (degree != that.degree) return false;
        if (status != that.status) return false;
        if (team != null ? !team.equals(that.team) : that.team != null) return false;
        return vehicleList != null ? vehicleList.equals(that.vehicleList) : that.vehicleList == null;
    }

    // Override hashCode() method for hashing based on fields
    @Override
    public int hashCode() {
        int result = greenSpace != null ? greenSpace.hashCode() : 0;
        result = 31 * result + (task != null ? task.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (degree != null ? degree.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (team != null ? team.hashCode() : 0);
        result = 31 * result + (vehicleList != null ? vehicleList.hashCode() : 0);
        return result;
    }
}
