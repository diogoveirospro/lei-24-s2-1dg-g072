package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Represents a task in the system.
 *
 * @autor Group 072 - Byte Masters - ISEP
 */
public class Task {

    private String taskId;
    private String description;
    private Date startDate;
    private Date endDate;
    private Vehicle vehicle;
    private Team team;
    private List<Skill> requiredSkills;

    /**
     * Constructor for the Task class.
     *
     * @param taskId Task identifier.
     * @param description Description of the task.
     * @param startDate Start date of the task.
     * @param endDate End date of the task.
     * @param vehicle Vehicle associated with the task.
     * @param team Team assigned to the task.
     * @param requiredSkills List of skills required for the task.
     */
    public Task(String taskId, String description, Date startDate, Date endDate, Vehicle vehicle, Team team, List<Skill> requiredSkills) {
        this.taskId = taskId;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.vehicle = vehicle;
        this.team = team;
        this.requiredSkills = requiredSkills;
    }

    /**
     * Gets the task identifier.
     *
     * @return Task identifier.
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * Gets the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the start date of the task.
     *
     * @return Start date of the task.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Gets the end date of the task.
     *
     * @return End date of the task.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Gets the vehicle associated with the task.
     *
     * @return Vehicle associated with the task.
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Gets the team assigned to the task.
     *
     * @return Team assigned to the task.
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Gets the list of skills required for the task.
     *
     * @return List of skills required for the task.
     */
    public List<Skill> getRequiredSkills() {
        return requiredSkills;
    }

    /**
     * Sets the description of the task.
     *
     * @param description New description for the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the start date of the task.
     *
     * @param startDate New start date for the task.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets the end date of the task.
     *
     * @param endDate New end date for the task.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Sets the vehicle associated with the task.
     *
     * @param vehicle New vehicle for the task.
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Sets the team assigned to the task.
     *
     * @param team New team for the task.
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * Sets the list of skills required for the task.
     *
     * @param requiredSkills New list of required skills for the task.
     */
    public void setRequiredSkills(List<Skill> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    /**
     * Checks if the task is valid based on required skills and assigned team.
     *
     * @return True if the task is valid, false otherwise.
     */
    public boolean validateTask() {
        for (Skill skill : requiredSkills) {
            boolean skillFound = false;
            for (Collaborator collaborator : team.getTeam()) {
                for (Skill collaboratorSkill : collaborator.getSkillSet()) {
                    if (collaboratorSkill.equals(skill)) {
                        skillFound = true;
                        break;
                    }
                }
                if (skillFound) {
                    break;
                }
            }
            if (!skillFound) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compares this task to another object for equality.
     *
     * @param o Object to compare to.
     * @return True if the tasks are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(taskId, task.taskId) &&
                Objects.equals(description, task.description) &&
                Objects.equals(startDate, task.startDate) &&
                Objects.equals(endDate, task.endDate) &&
                Objects.equals(vehicle, task.vehicle) &&
                Objects.equals(team, task.team) &&
                Objects.equals(requiredSkills, task.requiredSkills);
    }

    /**
     * Generates a hash code for the task.
     *
     * @return Hash code for the task.
     */
    @Override
    public int hashCode() {
        return Objects.hash(taskId, description, startDate, endDate, vehicle, team, requiredSkills);
    }

    /**
     * Provides a string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", vehicle=" + vehicle +
                ", team=" + team +
                ", requiredSkills=" + requiredSkills +
                '}';
    }
}
