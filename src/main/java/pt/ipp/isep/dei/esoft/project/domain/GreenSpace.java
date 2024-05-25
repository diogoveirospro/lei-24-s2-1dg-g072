package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.ToDoList;

public class GreenSpace {
    /**
     * The name of the green space
     *
     */
    private String parkName;
    /**
     * The dimension of the green space
     *
     */
    private double dimension;
    /**
     * The location of the green space
     *
     */
    private String address;
    /**
     * To do list of tasks of the green space
     *
     */
    private ToDoList toDoList;
    /**
     * The manager of the green space
     *
     */
    private Collaborator greenSpaceManager;
    /**
     * All the types of green spaces
     *
     */
    public enum TypeOfGreenSpace {
        GARDEN("Garden"),
        MPARK("Medium-sized park"),
        LPARK("Large-sized park"),;
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

        public static TypeOfGreenSpace getTypeOfGreenSpace(String type) {
            for (TypeOfGreenSpace typeOfGreenSpace : TypeOfGreenSpace.values()) {
                if (typeOfGreenSpace.getType().equals(type)) {
                    return typeOfGreenSpace;
                }
            }
            throw new IllegalArgumentException("Invalid type of green space: " + type);
        }
    }

    /**
     * The type of the green space
     *
     */
    private TypeOfGreenSpace type;

    /**
     * Constructor of the green space, that initializes the type, the name, the dimension and the address of the green space
     *
     * @param type the type of the green space
     * @param parkName the name of the green space
     * @param dimension the dimension of the green space
     * @param address the address of the green space
     * @param greenSpaceManager the manager of the green space
     */
    public GreenSpace(TypeOfGreenSpace type, String parkName, double dimension,String address,Collaborator greenSpaceManager) {
        this.type =type;
        this.parkName = parkName;
        this.dimension = dimension;
        this.address = address;
        this.greenSpaceManager = greenSpaceManager;
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
     * @param address of the green space
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Lets the user set the dimension of the green space
     * @param dimension of the green space
     */
    public void setDimension(double dimension) {
        this.dimension = dimension;
    }

    /**
     * Lets the user set the name of the green space
     * @param parkName of the green space
     */
    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    /**
     * Lets the user set the type of the green space
     * @param type of the green space
     */
    public void setType(TypeOfGreenSpace type) {
        this.type = type;
    }

    /**
     * Lets the user set the manager of the green space
     * @param greenSpaceManager
     */
    public void setGreenSpaceManager(Collaborator greenSpaceManager) {
        this.greenSpaceManager = greenSpaceManager;
    }
    /**
     * Lets the user set the to do list of the green space
     * @param toDoList of the green space
     */
    public void setToDoList(ToDoList toDoList) {
        this.toDoList = toDoList;
    }
}
