package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.Arrays;
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
    private ToDoListEntry toDoListEntry;
    private Date startDate;
    private WorkingDayHours startHour;
    private Date endDate;
    private WorkingDayHours endHour;
    private StatusOfEntry status;
    private List<Vehicle> vehicleList;
    private int duration;

    private TeamRepository teamRepository = Repositories.getInstance().getTeamRepository();
    private VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();

    /**
     * Enum representing the hours of the day.
     */
    public enum WorkingDayHours {
        H09("9:00"),
        H10("10:00"),
        H11("11:00"),
        H12("12:00"),
        H14("14:00"),
        H15("15:00"),
        H16("16:00"),
        H17("17:00");

        private final String hour;

        /**
         * Constructor for HourOfDay enum.
         *
         * @param hour the hour represented as a string in HH:mm format
         */
        WorkingDayHours(String hour) {
            this.hour = hour;
        }

        /**
         * Gets the hour as a string.
         *
         * @return the hour as a string in HH:mm format
         */
        public String getHour() {
            return hour;
        }

        @Override
        public String toString() {
            return hour;
        }

        /**
         * Gets the HourOfDay enum corresponding to the given hour string.
         *
         * @param hour the hour string in HH:mm format
         * @return the HourOfDay enum
         * @throws IllegalArgumentException if the hour string is invalid
         */
        public static WorkingDayHours fromString(String hour) throws InvalidEntryDataException {
            for (WorkingDayHours hourOfDay : WorkingDayHours.values()) {
                if (hourOfDay.getHour().equals(hour)) {
                    return hourOfDay;
                }
            }
            throw new InvalidEntryDataException("Invalid hour: " + hour);
        }

        /**
         * Returns a list containing all the hours of the day.
         *
         * @return a list of all hours
         */
        public static List<WorkingDayHours> getAllHours() {
            return new ArrayList<>(Arrays.asList(WorkingDayHours.values()));
        }

        /**
         * Checks if this hour is greater than another hour.
         *
         * @param otherHour the other hour to compare
         * @return true if this hour is greater than the other hour, false otherwise
         */
        public boolean isGreater(WorkingDayHours otherHour) {
            if (otherHour == null || this == otherHour){
                return false;
            }

            int hour1 = Integer.parseInt(this.getHour().substring(0, 2));
            int hour2 = Integer.parseInt(otherHour.getHour().substring(0, 2));

            return hour1 > hour2;
        }

    }

    /**
     * Enumeration representing the status of an agenda entry.
     */
    public enum StatusOfEntry {
        SCHEDULED("Scheduled"),
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
        public static StatusOfEntry getStatusOfEntry(String status) throws InvalidEntryDataException {
            for (StatusOfEntry statusOfEntry : StatusOfEntry.values()) {
                if (statusOfEntry.getStatus().equals(status)) {
                    return statusOfEntry;
                }
            }
            throw new InvalidEntryDataException("Invalid status of entry: " + status);
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
     * @throws InvalidEntryDataException if the start date is later than the end date
     */
    public AgendaEntry(Task task, GreenSpace greenSpace, Date startDate, WorkingDayHours startHour, Date endDate, WorkingDayHours endHour) throws InvalidEntryDataException {
        super(task, greenSpace);
        if (validateDates(startDate, endDate, startHour, endHour)) {
            throw new InvalidEntryDataException("The start date of the task cannot be later than the end date.");
        }
        this.startDate = startDate;
        this.startHour = startHour;
        this.endDate = endDate;
        this.endHour = endHour;
        this.duration = calculateDuration();
        this.vehicleList = new ArrayList<>();
        this.team = null;
        this.status = StatusOfEntry.SCHEDULED;
        this.toDoListEntry = greenSpace.getToDoList().getToDoListEntryByTaskName(task.getTaskId());

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
     * Gets the start hour of the task.
     *
     * @return the start hour
     */
    public WorkingDayHours getStartHour() {
        return startHour;
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
     * Gets the end hour of the task.
     *
     * @return the end hour
     */
    public WorkingDayHours getEndHour() {
        return endHour;
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
     * Gets the duration of the task.
     * @return the duration of the task
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the start date of the task.
     *
     * @param startDate the new start date
     * @throws InvalidEntryDataException if the start date is later than the end date
     */
    public void setStartDate(Date startDate) throws InvalidEntryDataException {
        if (validateDates(startDate, endDate, startHour, endHour)) {
            throw new InvalidEntryDataException("The start date of the task cannot be later than the end date.");
        }
        this.startDate = startDate;
    }

    /**
     * Sets the start hour of the task.
     *
     * @param startHour the new start hour
     * @throws InvalidEntryDataException if the start hour is later than the end hour
     */
    public void setStartHour(WorkingDayHours startHour) throws InvalidEntryDataException {
        if (validateDates(startDate, endDate, startHour, endHour)) {
            throw new InvalidEntryDataException("The start hour of the task cannot be later than the end hour.");
        }
        this.startHour = startHour;
    }

    /**
     * Sets the end date of the task.
     *
     * @param endDate the new end date
     * @throws InvalidEntryDataException if the end date is earlier than the start date
     */
    public void setEndDate(Date endDate) throws InvalidEntryDataException {
        if (validateDates(startDate, endDate, startHour, endHour)) {
            throw new InvalidEntryDataException("The end date of the task cannot be earlier than the start date.");
        }
        this.endDate = endDate;
    }

    /**
     * Sets the end hour of the task.
     *
     * @param endHour the new end hour
     * @throws InvalidEntryDataException if the end hour is earlier than the start hour
     */
    public void setEndHour(WorkingDayHours endHour) throws InvalidEntryDataException {
        if (validateDates(startDate, endDate, startHour, endHour)) {
            throw new InvalidEntryDataException("The end hour of the task cannot be earlier than the start hour.");
        }
        this.endHour = endHour;
    }


    public void addTeam(Team team) throws InvalidEntryDataException {
        if (!teamRepository.getTeams().contains(team)) {
            throw new InvalidEntryDataException("The team is not in the repository.");
        }
        this.team = team;
    }

    /**
     * Sets the status of the task to "Scheduled".
     */
    public void taskSchedule() {
        status = StatusOfEntry.SCHEDULED;
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
     * Validates the dates and hours.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @param startHour the start hour
     * @param endHour   the end hour
     * @return true if the end date and hour are greater than or equal to the start date and hour, false otherwise
     */
    private boolean validateDates(Date startDate, Date endDate, WorkingDayHours startHour, WorkingDayHours endHour) {


        if (startDate == null ||  endDate == null || startHour == null || endHour == null) {
            return true;
        }else if (endDate.isGreater(startDate) ){
            return false;
        } else if (startDate.isGreater(endDate)){
            return true;
        } else if (startDate.equals(endDate)){
            return !endHour.isGreater(startHour);
        } else {
            return true;
        }
    }

    /**
     * Postpones the entry to a new date.
     *
     * @param newDate the new date
     */
    public void postponeEntry(Date newDate) {
        this.startDate = newDate;
    }

    /**
     * Gets the name of the task.
     * @return the name of the task
     */
    public String getName(){
        return super.getTask().getTaskId();
    }

    /**
     * Calculates the duration of the task.
     *
     * @return the duration of the task in days.
     */
    public int calculateDuration(){
        return this.startDate.difference(this.endDate);
    }


    public void addVehicleList(List<Vehicle> vehicleList) throws InvalidEntryDataException {
        for (Vehicle vehicle : vehicleList) {
            if (!vehicleRepository.getVehicleList().contains(vehicle)) {
                throw new InvalidEntryDataException("The vehicle is not in the repository.");
            }
        }
        this.vehicleList = vehicleList;
    }

    public boolean assignTeam(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("The team is null.");
        } else if (!teamRepository.getTeams().contains(team)) {
            throw new IllegalArgumentException("The team is not in the repository.");

        } else if (this.team == null) {
            throw new IllegalArgumentException("The entry already has an associated team.");
        }

        this.team = team;
        return team.assignAgendaEntry(this);
    }

    public void addVehicle(Vehicle vehicle) throws InvalidEntryDataException {
        if (!vehicleRepository.getVehicleList().contains(vehicle)) {
            throw new InvalidEntryDataException("The vehicle is not in the repository.");
        }
        this.vehicleList.add(vehicle);
    }

    public AgendaEntry cloneEntry() throws InvalidEntryDataException {
        AgendaEntry newEntry = new AgendaEntry(super.getTask(), super.getGreenSpace(), this.startDate, this.startHour, this.endDate, this.endHour);
        newEntry.status.equals(StatusOfEntry.SCHEDULED);
        return newEntry;
    }

    @Override
    public Task getTask() {
        return super.getTask();
    }

    /**
     * Gets the vehicle assigned to this entry.
     *
     * @return the vehicle assigned to this entry, or null if no vehicle is assigned
     */
    public Vehicle getAssignedVehicle() {
        return vehicleList.isEmpty() ? null : vehicleList.get(0);
    }

    public void assignVehicleToAgendaEntry(Vehicle vehicle) throws InvalidEntryDataException {
        if (!vehicleRepository.getVehicleList().contains(vehicle)) {
            throw new InvalidEntryDataException("The vehicle is not in the repository.");
        }

        this.addVehicle(vehicle);
    }

    /**
     * Assigns a vehicle to this agenda entry.
     *
     * @param vehicle the vehicle to be assigned
     * @throws InvalidEntryDataException if the vehicle cannot be assigned
     */
    public void assignVehicle(Vehicle vehicle) throws InvalidEntryDataException {
        if (vehicle == null) {
            throw new IllegalArgumentException("O veículo é nulo.");
        }
        if (vehicleList.contains(vehicle)) {
            throw new InvalidEntryDataException("O veículo já está atribuído a esta entrada de agenda.");
        }
        vehicleList.add(vehicle);
    }
}

