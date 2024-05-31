package pt.ipp.isep.dei.esoft.project.domain;

/**
 * Represents an entry in a to-do list.
 * Contains a task, a green space, and a degree of urgency.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class ToDoListEntry extends Entry {
    private GreenSpace greenSpace;
    private DegreeOfUrgency degreeOfUrgency;



    /**
     * Enumeration for the degree of urgency.
     */
    public enum DegreeOfUrgency {
        LOW("Low"),
        MEDIUM("Medium"),
        HIGH("High");

        private final String degree;

        DegreeOfUrgency(String degree) {
            this.degree = degree;
        }

        /**
         * Gets the degree of urgency as a string.
         *
         * @return the degree of urgency.
         */
        public String getDegree() {
            return degree;
        }

        @Override
        public String toString() {
            return degree;
        }

        /**
         * Retrieves the DegreeOfUrgency enum value corresponding to the given degree string.
         *
         * @param degree the degree string.
         * @return the corresponding DegreeOfUrgency enum value.
         * @throws IllegalArgumentException if the degree string is invalid.
         */
        public static DegreeOfUrgency getDegreeOfUrgency(String degree) {
            for (DegreeOfUrgency degreeOfUrgency : DegreeOfUrgency.values()) {
                if (degreeOfUrgency.getDegree().equals(degree)) {
                    return degreeOfUrgency;
                }
            }
            throw new IllegalArgumentException("Invalid degree of urgency: " + degree);
        }
    }

    /**
     * Constructs a new ToDoListEntry with the specified task, green space, and degree of urgency.
     *
     * @param task the task associated with the to-do list entry.
     * @param greenSpace the green space associated with the to-do list entry.
     * @param degreeOfUrgency the degree of urgency for the to-do list entry.
     */
    public ToDoListEntry(Task task, GreenSpace greenSpace, DegreeOfUrgency degreeOfUrgency) {
        super(task,greenSpace);
        this.greenSpace = greenSpace;
        this.degreeOfUrgency = degreeOfUrgency;
    }

    /**
     * Gets the degree of urgency for this to-do list entry.
     *
     * @return the degree of urgency.
     */
    public DegreeOfUrgency getDegreeOfUrgency() {
        return degreeOfUrgency;
    }

    /**
     * Gets the task associated with this to-do list entry.
     *
     * @return the task.
     */
    @Override
    public Task getTask() {
        return super.getTask();
    }

    /**
     * Gets the green space associated with this to-do list entry.
     *
     * @return the green space.
     */
    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    /**
     * Sets the task for this to-do list entry.
     *
     * @param task the task to set.
     */
    @Override
    public void setTask(Task task) {
        super.setTask(task);
    }

    /**
     * Sets the green space for this to-do list entry.
     *
     * @param greenSpace the green space to set.
     */
    public void setGreenSpace(GreenSpace greenSpace) {
        this.greenSpace = greenSpace;
    }

    /**
     * Changes the degree of urgency to low.
     */
    public void changeUrgencyToLow() {
        this.degreeOfUrgency = DegreeOfUrgency.LOW;
    }

    /**
     * Changes the degree of urgency to medium.
     */
    public void changeUrgencyToMedium() {
        this.degreeOfUrgency = DegreeOfUrgency.MEDIUM;
    }

    /**
     * Changes the degree of urgency to high.
     */
    public void changeUrgencyToHigh() {
        this.degreeOfUrgency = DegreeOfUrgency.HIGH;
    }
}
