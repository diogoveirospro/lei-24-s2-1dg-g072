package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidGreenSpaceDataException;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.ToDoList;

/**
 * Represents a green space in the project domain.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class GreenSpace {
    /**
     * The name of the green space.
     */
    private String parkName;
    /**
     * The dimension of the green space.
     */
    private double dimension;
    /**
     * The location of the green space.
     */
    private String address;
    /**
     * To-do list of tasks for the green space.
     */
    private ToDoList toDoList;
    /**
     * The manager of the green space.
     */
    private Collaborator greenSpaceManager;

    /**
     * All types of green spaces.
     */
    public enum TypeOfGreenSpace {
        GARDEN("Garden"),
        MPARK("Medium-sized park"),
        LPARK("Large-sized park");

        private final String type;

        TypeOfGreenSpace(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        @Override
        public String toString() {
            return type;
        }

        public static TypeOfGreenSpace getTypeOfGreenSpace(String type) throws InvalidGreenSpaceDataException {
            for (TypeOfGreenSpace typeOfGreenSpace : TypeOfGreenSpace.values()) {
                if (typeOfGreenSpace.getType().equals(type)) {
                    return typeOfGreenSpace;
                }
            }
            throw new InvalidGreenSpaceDataException("Invalid type of green space: " + type);
        }
    }

    /**
     * The type of the green space.
     */
    private TypeOfGreenSpace type;

    /**
     * Constructor for a green space.
     *
     * @param type              the type of the green space
     * @param parkName          the name of the green space
     * @param dimension         the dimension of the green space
     * @param address           the address of the green space
     * @param greenSpaceManager the manager of the green space
     * @throws InvalidGreenSpaceDataException if the green space data is invalid
     */
    public GreenSpace(TypeOfGreenSpace type, String parkName, double dimension, String address, Collaborator greenSpaceManager) throws InvalidGreenSpaceDataException {
        // Validate and initialize the type of the green space
        if (type != null) {
            this.type = type;
        } else {
            throw new InvalidGreenSpaceDataException("Invalid type of green space.");
        }

        // Validate and initialize the name of the green space
        if (isValidParkName(parkName)) {
            this.parkName = parkName;
        } else {
            throw new InvalidGreenSpaceDataException("Invalid green space name.");
        }

        // Validate and initialize the dimension of the green space
        if (dimension > 0) {
            this.dimension = dimension;
        } else {
            throw new InvalidGreenSpaceDataException("Invalid dimension of the green space.");
        }

        // Validate and initialize the address of the green space
        if (isValidAddress(address)) {
            this.address = address;
        } else {
            throw new InvalidGreenSpaceDataException("Invalid address of the green space.");
        }

        // Validate and initialize the manager of the green space
        if (isValidGreenSpaceManager(greenSpaceManager)) {
            this.greenSpaceManager = greenSpaceManager;
        } else {
            throw new InvalidGreenSpaceDataException("Invalid green space manager.");
        }

        this.toDoList = new ToDoList();
    }

    // Method to validate if the manager of the green space is valid
    private boolean isValidGreenSpaceManager(Collaborator greenSpaceManager) throws InvalidGreenSpaceDataException {
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();

        // Check if the manager exists in the system
        if (!collaboratorRepository.exist(greenSpaceManager)) {
            throw new InvalidGreenSpaceDataException("The GSM provided does not exist in the system!");
        } else {
            return true;
        }
    }

    // Method to validate if the address of the green space is valid
    private static boolean isValidAddress(String address) throws InvalidGreenSpaceDataException {
        if (address == null || address.isEmpty() || address.isBlank()) {
            throw new InvalidGreenSpaceDataException("The address cannot be empty or blank.");
        } else {
            return true;
        }
    }

    /**
     * Validates a green space name.
     *
     * @param greenSpace the green space to validate
     * @return true if the green space is valid
     * @throws InvalidGreenSpaceDataException if the green space is null, blank, or contains invalid characters
     */
    private static boolean isValidParkName(String greenSpace) throws InvalidGreenSpaceDataException {
        if (greenSpace == null || greenSpace.isBlank()) {
            throw new InvalidGreenSpaceDataException("Invalid input. The green space cannot be empty or blank.");
        } else if (!greenSpace.matches("[a-zA-ZÀ-ÿ ]+")) {
            throw new InvalidGreenSpaceDataException("Invalid input. The green space must not contain numbers or special characters, and must be composed of letters and spaces only.");
        } else {
            return true;
        }
    }


    /**
     * Lets the user get the dimension of the green space
     *
     * @return dimension of the green space
     */
    public double getDimension() {
        return dimension;
    }

    /**
     * Lets the user get the address of the green space
     *
     * @return address of the green space
     */
    public String getAddress() {
        return address;
    }

    /**
     * Lets the user get the name of the green space
     *
     * @return name of the green space
     */
    public String getParkName() {
        return parkName;
    }

    /**
     * Lets the user get the type of the green space
     *
     * @return type of the green space
     */
    public TypeOfGreenSpace getType() {
        return type;
    }

    /**
     * Lets the user get the manager of the green space
     *
     * @return manager of the green space
     */
    public Collaborator getGreenSpaceManager() {
        return greenSpaceManager;
    }

    /**
     * Lets the user get the to do list of the green space
     *
     * @return to do list of the green space
     */
    public ToDoList getToDoList() {
        return toDoList;
    }

    /**
     * Lets the user set the address of the green space
     *
     * @param address of the green space
     */
    public void setAddress(String address) throws InvalidGreenSpaceDataException {
        if (isValidAddress(address)) {
            this.address = address;
        } else {
            throw new InvalidGreenSpaceDataException("Invalid address of the green space.");
        }
    }

    /**
     * Lets the user set the dimension of the green space
     *
     * @param dimension of the green space
     */
    public void setDimension(double dimension) throws InvalidGreenSpaceDataException {
        if (dimension > 0) {
            this.dimension = dimension;
        } else {
            throw new InvalidGreenSpaceDataException("Invalid dimension of the green space.");
        }
    }

    /**
     * Lets the user set the name of the green space
     *
     * @param parkName of the green space
     */
    public void setParkName(String parkName) throws InvalidGreenSpaceDataException {
        if (isValidParkName(parkName)) {
            this.parkName = parkName;
        } else {
            throw new InvalidGreenSpaceDataException("Invalid green space name.");
        }
    }

    /**
     * Lets the user set the type of the green space
     *
     * @param type of the green space
     */
    public void setType(TypeOfGreenSpace type) throws InvalidGreenSpaceDataException {
        if (type != null) {
            this.type = type;
        } else {
            throw new InvalidGreenSpaceDataException("Invalid type of green space.");
        }
    }

    /**
     * Lets the user set the manager of the green space
     *
     * @param greenSpaceManager green space manager
     */
    public void setGreenSpaceManager(Collaborator greenSpaceManager) throws InvalidGreenSpaceDataException {
        if (isValidGreenSpaceManager(greenSpaceManager)) {
            this.greenSpaceManager = greenSpaceManager;
        } else {
            throw new InvalidGreenSpaceDataException("Invalid green space manager.");
        }
    }

    /**
     * Lets the user set the to do list of the green space
     *
     * @param toDoList of the green space
     */

    public void setToDoList(ToDoList toDoList) throws InvalidGreenSpaceDataException {

        if (toDoList == null) {
            throw new InvalidGreenSpaceDataException("The to-do list cannot be null.");
        }
        this.toDoList = toDoList;

    }
}

