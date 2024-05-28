package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

public class Entry {
    public Task getTask() {
        return task;
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

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public Team getTeam() {
        return team;
    }

    public DegreeOfUrgency getDegree() {
        return degree;
    }

    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

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
    private StatusOfEntry status;
    private DegreeOfUrgency degree;
    private Task task;
    private Team team;
    private GreenSpace greenSpace;
    private Date startDate;
    private Date endDate;
    private List<Vehicle> vehicleList;

}
