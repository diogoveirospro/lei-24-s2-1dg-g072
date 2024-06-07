package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidGreenSpaceDataException;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

/**
 * RegisterGreenSpaceController is a class responsible for handling requests related to the registration of green spaces.
 * It interacts with the repository to add new green spaces and retrieve existing ones.
 */
public class RegisterGreenSpaceController {
    private CollaboratorRepository collaboratorRepository;
    private GreenSpaceRepository greenSpaceRepository;

    /**
     * Constructs a RegisterGreenSpaceController object.
     * Initializes the GreenSpaceRepository instance.
     */
    public RegisterGreenSpaceController() {
        this.collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        this.greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
    }

    /**
     * Registers a new green space with the provided details.
     *
     * @param parkName          The name of the green space.
     * @param dimension         The dimension of the green space.
     * @param address           The address of the green space.
     * @param type              The type of the green space.
     * @return True if the green space is successfully registered, false otherwise.
     */
    public boolean registerGreenSpace(String parkName, double dimension, String address, GreenSpace.TypeOfGreenSpace type) {
        try {
            Collaborator collaborator = getCollaboratorFromSession();
            GreenSpace greenSpace = new GreenSpace(type, parkName, dimension, address, collaborator);


            greenSpaceRepository.addGreenSpace(greenSpace);

            return true;
        } catch (InvalidGreenSpaceDataException e) {

            System.err.println("Error registering green space: " + e.getMessage());
            return false;
        }

    }
    private Collaborator getCollaboratorFromSession() {
        String email = ApplicationSession.getInstance().getCurrentSession().getUserEmail();
        return collaboratorRepository.getCollaboratorByEmail(email);
    }
}
