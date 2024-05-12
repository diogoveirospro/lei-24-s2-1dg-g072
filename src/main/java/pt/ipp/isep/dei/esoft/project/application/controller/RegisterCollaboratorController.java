package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

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
public class RegisterCollaboratorController {

    private final CollaboratorRepository collaboratorRepository;
    private final JobRepository jobRepository;

    /**
     * Constructs a new RegisterCollaboratorController object.
     * Initializes CollaboratorRepository and JobRepository from the Repositories singleton.
     */
    public RegisterCollaboratorController() {
        this.collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        this.jobRepository = Repositories.getInstance().getJobRepository();
    }

    /**
     * Constructs a new RegisterCollaboratorController object with custom repositories.
     *
     * @param collaboratorRepository the CollaboratorRepository instance to be used
     * @param jobRepository          the JobRepository instance to be used
     */
    public RegisterCollaboratorController(CollaboratorRepository collaboratorRepository, JobRepository jobRepository) {
        this.collaboratorRepository = collaboratorRepository;
        this.jobRepository = jobRepository;
    }

    /**
     * Registers a new collaborator with the provided information.
     *
     * @param name           the name of the collaborator
     * @param birthDate      the birth date of the collaborator
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
                                     int mobile, String email, int taxpayerNumber, String idDocType, int idDocNumber, String jobName) {

        this.collaboratorRepository.addCollaborator(new Collaborator(name, birthDate, admissionDate, address, mobile,
                email, taxpayerNumber, idDocType, idDocNumber, jobName));
    }
}
