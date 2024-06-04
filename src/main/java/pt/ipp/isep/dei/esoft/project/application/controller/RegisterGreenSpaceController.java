package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidGreenSpaceDataException;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

/**
 * RegisterGreenSpaceController is a class responsible for handling requests related to the registration of green spaces.
 * It interacts with the repository to add new green spaces and retrieve existing ones.
 */
public class RegisterGreenSpaceController {

    private GreenSpaceRepository greenSpaceRepository;

    /**
     * Constructs a RegisterGreenSpaceController object.
     * Initializes the GreenSpaceRepository instance.
     */
    public RegisterGreenSpaceController() {
        this.greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
    }

    /**
     * Registers a new green space with the provided details.
     *
     * @param parkName          The name of the green space.
     * @param dimension         The dimension of the green space.
     * @param address           The address of the green space.
     * @param type              The type of the green space.
     * @param greenSpaceManager The manager of the green space.
     * @return True if the green space is successfully registered, false otherwise.
     */
    public boolean registerGreenSpace(String parkName, double dimension, String address, GreenSpace.TypeOfGreenSpace type, Collaborator greenSpaceManager) {
        try {
            // Create a new GreenSpace object with the provided details
            GreenSpace greenSpace = new GreenSpace(type, parkName, dimension, address, greenSpaceManager);

            // Add the new green space to the repository
            greenSpaceRepository.addGreenSpace(greenSpace);

            // Green space successfully registered
            return true;
        } catch (InvalidGreenSpaceDataException e) {
            // Log or handle the exception as needed
            System.err.println("Error registering green space: " + e.getMessage());
            return false;
        }
    }
}
