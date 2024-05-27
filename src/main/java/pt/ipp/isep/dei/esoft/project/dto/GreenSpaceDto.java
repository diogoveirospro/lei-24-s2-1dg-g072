package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.ToDoList;

public class GreenSpaceDto {
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
     * The type of the green space
     *
     */
    private GreenSpace.TypeOfGreenSpace type;

    /**
     * Constructor of the GreenSpaceDto
     * @param parkName name of the green space
     * @param dimension dimension of the green space
     * @param greenSpaceManager manager of the green space
     * @param toDoList list of tasks of the green space
     * @param type type of the green space
     * @param address location of the green space
     */
    public GreenSpaceDto(String parkName, double dimension, Collaborator greenSpaceManager, ToDoList toDoList, GreenSpace.TypeOfGreenSpace type, String address){
        this.parkName = parkName;
        this.dimension = dimension;
        this.greenSpaceManager = greenSpaceManager;
        this.toDoList = toDoList;
        this.type = type;
        this.address = address;

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
    public GreenSpace.TypeOfGreenSpace getType() {
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
    public void setType(GreenSpace.TypeOfGreenSpace type) {
        this.type = type;
    }

    /**
     * Lets the user set the manager of the green space
     * @param greenSpaceManager of the green space
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
