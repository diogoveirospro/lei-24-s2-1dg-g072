package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

/**
 *
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class RegisterCollaboratorController {

    private final CollaboratorRepository collaboratorRepository;
    private final JobRepository jobRepository;

    public RegisterCollaboratorController() {
        this.collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        this.jobRepository = Repositories.getInstance().getJobRepository();
    }

    public RegisterCollaboratorController(CollaboratorRepository collaboratorRepository, JobRepository jobRepository) {
        this.collaboratorRepository = collaboratorRepository;
        this.jobRepository = jobRepository;
    }

    public void registerCollaborator(String name, Date birthDate, Date admissionDate, String address,
                                        int mobile, String email, int taxpayerNumber, String idDocType, int idDocNumber, String jobName) {

        this.collaboratorRepository.addCollaborator(new Collaborator(name, birthDate, admissionDate, address, mobile,
                email, taxpayerNumber, idDocType, idDocNumber, jobName));
    }
}
