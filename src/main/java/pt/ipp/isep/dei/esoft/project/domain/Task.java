package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;


public class Task {
    private List<Vehicle> vehicleList;
    private Team team;
    private Entry.StatusOfEntry status;
    public Date duration;
    private String task;

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public Date getDuration() {
        return duration;
    }

    public Entry.StatusOfEntry getStatus() {
        return status;
    }

    public String getTask() {
        return task;
    }

    public Team getTeam() {
        return team;
    }
}
