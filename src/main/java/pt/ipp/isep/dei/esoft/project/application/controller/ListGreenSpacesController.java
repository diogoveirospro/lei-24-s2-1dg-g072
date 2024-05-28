package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.Mapper.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

public class ListGreenSpacesController {
    /**
     * greenSpaceRepository contains all green spaces
     */
    private GreenSpaceRepository greenSpaceRepository;
    /**
     * collaboratorRepository contains all collaborators
     */
    private CollaboratorRepository collaboratorRepository;
    /**
     * authenticationRepository authenticates the app
     */
    private AuthenticationRepository authenticationRepository;

    /**
     * Empty ListGreenSpacesController builder.
     */
    public ListGreenSpacesController() {
        this.greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
        this.collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    /**
     * ListGreenSpacesController builder
     * @param greenSpaceRepository contains all green spaces
     * @param collaboratorRepository contains all collaborators
     * @param authenticationRepository  authenticates the app
     */
    public ListGreenSpacesController(GreenSpaceRepository greenSpaceRepository, CollaboratorRepository collaboratorRepository, AuthenticationRepository authenticationRepository) {
        this.greenSpaceRepository = greenSpaceRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.authenticationRepository = authenticationRepository;
    }
    /**
     * Lets the controller get the authentication repository
     *
     * @return authenticationRepository
     */
    public AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Lets the controller get the green space repository
     * @return greenSpaceRepository
     */
    public GreenSpaceRepository getGreenSpaceRepository() {
        if (greenSpaceRepository == null) {
            Repositories repositories = Repositories.getInstance();

            greenSpaceRepository = repositories.getGreenSpaceRepository();
        }
        return greenSpaceRepository;
    }

    /**
     * Lets the controller get the collaborator repository
     * @return collaboratorRepository
     */
    public  CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();

            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }
    /**
     * Lets the controller get the collaborator by email
     *
     * @param email email
     * @return collaborator
     */
    public Collaborator getCollaboratorByEmail(String email) {
        return this.collaboratorRepository.getCollaboratorByEmail(email);
    }
    public List<GreenSpaceDto> getGreenSpaceList(Collaborator greenSpaceManager, String sortingOption){
        List<GreenSpace> greenSpaceList = greenSpaceRepository.getGreenSpaceList(greenSpaceManager, sortingOption);
        GreenSpaceMapper mapper = new GreenSpaceMapper();
        return mapper.greenSpaceListToDto(greenSpaceList);
    }
}
