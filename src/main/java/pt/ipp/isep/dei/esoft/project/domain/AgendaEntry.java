package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidAgendaEntryDataException;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an agenda entry within the project.
 * An agenda entry contains details about a task, including the team,
 * start and end dates, status, and a list of associated vehicles.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class AgendaEntry extends Entry {

    private Team team;
    private Date startDate;
    private Date endDate;
    private StatusOfEntry status;
    private List<Vehicle> vehicleList;

    private GreenSpaceRepository greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
    private TeamRepository teamRepository = Repositories.getInstance().getTeamRepository();
    private VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();

    /**
     * Enumeration representing the status of an agenda entry.
     */
    public enum StatusOfEntry {
        SCHEDULE("Schedule"),
        POSTPONED("Postponed"),
        CANCELED("Canceled"),
        DONE("Done");

        private final String status;

        StatusOfEntry(String status) {
            this.status = status;
        }

        /**
         * Gets the status of the entry.
         *
         * @return the status as a string
         */
        public String getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return status;
        }

        /**
         * Gets the StatusOfEntry enum corresponding to the given status string.
         *
         * @param status the status string
         * @return the corresponding StatusOfEntry enum
         * @throws IllegalArgumentException if the status string is invalid
         */
        public static StatusOfEntry getStatusOfEntry(String status) {
            for (StatusOfEntry statusOfEntry : StatusOfEntry.values()) {
                if (statusOfEntry.getStatus().equals(status)) {
                    return statusOfEntry;
                }
            }
            throw new IllegalArgumentException("Invalid status of entry: " + status);
        }

        /**
         * Gets a list of all status strings.
         *
         * @return a list of status strings
         */
        public static List<String> getStatusList() {
            List<String> statusList = new ArrayList<>();
            for (StatusOfEntry status : StatusOfEntry.values()) {
                statusList.add(status.getStatus());
            }
            return statusList;
        }
    }

    /**
     * Constructs an agenda entry with the specified task, green space, start date, and end date.
     *
     * @param task       the task associated with this entry
     * @param greenSpace the green space associated with this entry
     * @param startDate  the start date of the task
     * @param endDate    the end date of the task
     * @throws InvalidAgendaEntryDataException if the start date is later than the end date
     */
    public AgendaEntry(Task task, GreenSpace greenSpace, Date startDate, Date endDate) throws InvalidAgendaEntryDataException {
        super(task, greenSpace);
        if (!validateEntry(startDate, endDate)) {
            throw new InvalidAgendaEntryDataException("The start date of the task cannot be later than the end date.");
        }

        this.startDate = startDate;
        this.endDate = endDate;
        this.vehicleList = new ArrayList<>();
        this.team = null;
    }

    /**
     * Gets the start date of the task.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Gets the end date of the task.
     *
     * @return the end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Gets the list of vehicles associated with this entry.
     *
     * @return the list of vehicles
     */
    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    /**
     * Gets the status of the entry.
     *
     * @return the status
     */
    public StatusOfEntry getStatus() {
        return status;
    }

    /**
     * Gets the team associated with this entry.
     *
     * @return the team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Sets the start date of the task.
     *
     * @param startDate the new start date
     * @throws InvalidAgendaEntryDataException if the start date is later than the end date
     */
    public void setStartDate(Date startDate) throws InvalidAgendaEntryDataException {
        if (startDate.isGreater(endDate)) {
            throw new InvalidAgendaEntryDataException("The start date of the task cannot be later than the end date.");
        }
        this.startDate = startDate;
    }

    /**
     * Sets the end date of the task.
     *
     * @param endDate the new end date
     * @throws InvalidAgendaEntryDataException if the end date is earlier than the start date
     */
    public void setEndDate(Date endDate) throws InvalidAgendaEntryDataException {
        if (!startDate.isGreater(endDate)) {
            throw new InvalidAgendaEntryDataException("The end date of the task cannot be earlier than the start date.");
        }
        this.endDate = endDate;
    }

    /**
     * Sets the list of vehicles associated with this entry.
     *
     * @param vehicleList the new list of vehicles
     * @throws InvalidAgendaEntryDataException if any vehicle is not in the repository
     */
    public void setVehicleList(List<Vehicle> vehicleList) throws InvalidAgendaEntryDataException {
        for (Vehicle vehicle : vehicleList) {
            if (!vehicleRepository.getVehicleList().contains(vehicle)) {
                throw new InvalidAgendaEntryDataException("The vehicle is not in the repository.");
            }
        }
        this.vehicleList = vehicleList;
    }

    /**
     * Sets the team associated with this entry.
     *
     * @param team the new team
     * @throws InvalidAgendaEntryDataException if the team is not in the repository
     */
    public void setTeam(Team team) throws InvalidAgendaEntryDataException {
        if (!teamRepository.getTeams().contains(team)) {
            throw new InvalidAgendaEntryDataException("The team is not in the repository.");
        }
        this.team = team;
    }

    /**
     * Sets the status of the task to "Scheduled".
     */
    public void taskSchedule() {
        status = StatusOfEntry.SCHEDULE;
    }

    /**
     * Sets the status of the task to "Postponed".
     */
    public void taskPostponed() {
        status = StatusOfEntry.POSTPONED;
    }

    /**
     * Sets the status of the task to "Canceled".
     */
    public void taskCanceled() {
        status = StatusOfEntry.CANCELED;
    }

    /**
     * Sets the status of the task to "Done".
     */
    public void taskDone() {
        status = StatusOfEntry.DONE;
    }

    /**
     * Validates the entry by ensuring the end date is greater than the start date.
     *
     * @param startDate the start date to validate
     * @param endDate   the end date to validate
     * @return true if the end date is greater than the start date, false otherwise
     */
    private boolean validateEntry(Date startDate, Date endDate) {
        return endDate.isGreater(startDate);
    }

    @Override
    public Task getTask() {
        return super.getTask();
    }
}
