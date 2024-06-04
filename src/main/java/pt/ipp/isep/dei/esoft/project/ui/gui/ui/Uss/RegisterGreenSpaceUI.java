package pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidGreenSpaceDataException;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace.TypeOfGreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.Optional;

/**
 * RegisterGreenSpaceUI class handles the UI operations related to registering a new Green Space.
 * It interacts with repositories to store and retrieve data.
 *
 * @version 1.0
 */
public class RegisterGreenSpaceUI {

    private GreenSpaceRepository greenSpaceRepository;

    /**
     * Default constructor for RegisterGreenSpaceUI.
     * Initializes the repositories by fetching instances from Repositories.
     */
    public RegisterGreenSpaceUI() {
        this.greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
    }

    /**
     * Fetches the GreenSpaceRepository instance.
     *
     * @return greenSpaceRepository instance
     */
    private GreenSpaceRepository getGreenSpaceRepository() {
        if (greenSpaceRepository == null) {
            Repositories repositories = Repositories.getInstance();
            greenSpaceRepository = repositories.getGreenSpaceRepository();
        }
        return greenSpaceRepository;
    }

    /**
     * Registers a new green space with the provided details.
     *
     * @param parkName          The name of the green space.
     * @param type              The type of the green space.
     * @param dimension         The dimension of the green space.
     * @param address           The address of the green space.
     * @return Optional containing the new GreenSpace if registered successfully, otherwise empty.
     * @throws InvalidGreenSpaceDataException if the provided data for the green space is invalid.
     */
    public Optional<GreenSpace> registerGreenSpace(String parkName, String type, double dimension, String address) throws InvalidGreenSpaceDataException, InvalidGreenSpaceDataException {
        Optional<GreenSpaceRepository> greenSpaceRepo = Optional.ofNullable(getGreenSpaceRepository());
        Optional<GreenSpace> newGreenSpace = Optional.empty();

        if (greenSpaceRepo.isPresent()) {
            TypeOfGreenSpace greenSpaceType = TypeOfGreenSpace.getTypeOfGreenSpace(type);
            GreenSpace greenSpace = new GreenSpace(greenSpaceType, parkName, dimension, address, null); // Assuming no manager needed
            greenSpaceRepo.get().addGreenSpace(greenSpace);
            newGreenSpace = Optional.of(greenSpace);
        }

        return newGreenSpace;
    }
}
