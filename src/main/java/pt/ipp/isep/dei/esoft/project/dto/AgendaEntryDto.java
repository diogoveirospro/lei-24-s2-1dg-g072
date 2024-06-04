package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.ToDoList;

import java.util.List;

public class AgendaEntryDto {

    /**
     * The start date of the entry
     */
    private Date startDate;
    /**
     * The end date of the entry
     */
    private Date endDate;
    /**
     * The status of the entry
     */
    public AgendaEntry.StatusOfEntry status;
    /**
     * The green space associated to the entry
     */
    private GreenSpace greenSpace;
    /**
     * The task associated to the entry
     */
    private Task task;
    /**
     * The team associated to the entry
     */
    private Team team;
    /**
     * The list of vehicles associated to the entry
     */
    private List<Vehicle> vehicleList;

    /**
     * AgendaEntryDto contractor that initializes the toDoListEntry, startDate, endDate, status, greenSpace and task
     *
     * @param startDate the start date of the entry
     * @param endDate the end date of the entry
     * @param status the status of the entry
     * @param greenSpace the green space associated to the entry
     * @param task the task associated to the entry
     */
    public AgendaEntryDto(Date startDate, Date endDate, AgendaEntry.StatusOfEntry status, GreenSpace greenSpace, Task task, Team team, List<Vehicle> vehicleList) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.greenSpace = greenSpace;
        this.task = task;
        this.team = team;
        this.vehicleList = vehicleList;
    }

    /**
     * Gets the status of the entry
     *
     * @return status
     */

    public AgendaEntry.StatusOfEntry getStatus() {
        return status;
    }

    /**
     * Gets the start date of the entry
     *
     * @return startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Gets the task associated to the entry
     *
     * @return task
     */
    public Task getTask() {
        return task;
    }

    /**
     * Gets the green space associated to the entry
     *
     * @return greenSpace
     */
    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    /**
     * Gets the end date of the entry
     *
     * @return endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Gets the team associated to the entry
     *
     * @return team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Gets the list of vehicles associated to the entry
     *
     * @return vehicleList
     */
    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    /**
     * Sets the start date of the entry
     *
     * @param startDate the start date of the entry
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets the green space associated to the entry
     *
     * @param greenSpace the green space associated to the entry
     */
    public void setGreenSpace(GreenSpace greenSpace) {
        this.greenSpace = greenSpace;
    }

    /**
     * Sets the task associated to the entry
     *
     * @param task the task associated to the entry
     */
    public void setTask(Task task) {
        this.task = task;
    }

    /**
     * Sets the status of the entry
     *
     * @param status the status of the entry
     */
    public void setStatus(AgendaEntry.StatusOfEntry status) {
        this.status = status;
    }

    /**
     * Sets the end date of the entry
     *
     * @param endDate the end date of the entry
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

/**
     * Sets the vehicles associated to the entry
     *
     * @param vehicleList the vehicles associated to the entry
     */

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    /**
     * Sets the team associated to the entry
     *
     * @param team the team associated to the entry
     */
    public void setTeam(Team team) {
        this.team = team;
    }
}
