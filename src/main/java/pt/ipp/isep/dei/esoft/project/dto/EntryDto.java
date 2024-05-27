package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.List;

public class EntryDto {
    private Task task;
    private Date startDate;
    private Date endDate;
    private Entry.StatusOfEntry status;
    private List<Vehicle> vehicleList;
    private Team team;
    private Entry.DegreeOfUrgency degree;
    private GreenSpace greenSpace;

    public EntryDto(Task task, Date startDate, Date endDate, Entry.StatusOfEntry status, List<Vehicle> vehicleList, Team team, Entry.DegreeOfUrgency degree, GreenSpace greenSpace) {
        this.task = task;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.vehicleList = vehicleList;
        this.team = team;
        this.degree = degree;
        this.greenSpace = greenSpace;
    }

    public Entry.DegreeOfUrgency getDegree() {
        return degree;
    }

    public Entry.StatusOfEntry getStatus() {
        return status;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    public Team getTeam() {
        return team;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Task getTask() {
        return task;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public void setDegree(Entry.DegreeOfUrgency degree) {
        this.degree = degree;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setGreenSpace(GreenSpace greenSpace) {
        this.greenSpace = greenSpace;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setStatus(Entry.StatusOfEntry status) {
        this.status = status;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
