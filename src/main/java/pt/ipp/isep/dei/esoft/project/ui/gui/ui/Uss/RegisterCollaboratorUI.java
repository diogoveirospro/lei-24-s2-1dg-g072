package pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss;

import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

/**
 * Controller class for registering a new collaborator.
 * Responsible for handling the registration of a new collaborator.
 * Uses CollaboratorRepository and JobRepository to access and manage collaborator and job data.
 * Collaborator registration is done by providing basic information and job name.
 * Collaborator object is created and added to the CollaboratorRepository.
 * CollaboratorRepository and JobRepository instances are obtained from Repositories singleton.
 * If needed, custom repositories can be injected through the constructor.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class RegisterCollaboratorUI {

    private final CollaboratorRepository collaboratorRepository;
    private final JobRepository jobRepository;

    /**
     * Constructs a new RegisterCollaboratorController object.
     * Initializes CollaboratorRepository and JobRepository from the Repositories singleton.
     */
    public RegisterCollaboratorUI() {
        this.collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        this.jobRepository = Repositories.getInstance().getJobRepository();
    }

    /**
     * Constructs a new RegisterCollaboratorController object with custom repositories.
     *
     * @param collaboratorRepository the CollaboratorRepository instance to be used
     * @param jobRepository          the JobRepository instance to be used
     */
    public RegisterCollaboratorUI(CollaboratorRepository collaboratorRepository, JobRepository jobRepository) {
        this.collaboratorRepository = collaboratorRepository;
        this.jobRepository = jobRepository;
    }

    /**
     * Registers a new collaborator with the provided information.
     *
     * @param name           the name of the collaborator
     * @param birthDate      the birthdate of the collaborator
     * @param admissionDate  the admission date of the collaborator
     * @param address        the address of the collaborator
     * @param mobile         the contact number of the collaborator
     * @param email          the email address of the collaborator
     * @param taxpayerNumber the taxpayer number of the collaborator
     * @param idDocType      the type of ID document of the collaborator
     * @param idDocNumber    the number of the ID document of the collaborator
     * @param jobName        the name of the job of the collaborator
     */
    public void registerCollaborator(String name, Date birthDate, Date admissionDate, String address,
                                     String mobile, String email, String taxpayerNumber, Collaborator.IdDocType idDocType, String idDocNumber, String jobName) throws InvalidCollaboratorDataException, InvalidCollaboratorDataException {

        this.collaboratorRepository.addCollaborator(new Collaborator(name, birthDate, admissionDate, address, mobile,
                email, taxpayerNumber, idDocType, idDocNumber, jobName));
    }

    /**
     * Retrieves a list of jobs.
     *
     * @return a list of jobs
     */
    public List<Job> getJobs(){
        return jobRepository.getJobs();
    }

    public void showUI(Stage primaryStage) {

    }
}
