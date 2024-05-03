package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.Optional;

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

    public boolean registerCollaborator(String name, String birthDate, String admissionDate, String address,
                                        String contactInfo, String taxpayerNumber, String idDocType, String idDocNumber,
                                        String jobName) {
        // Check if the job exists
        Optional<Job> optionalJob = jobRepository.findJobByName(jobName);
        if (optionalJob.isEmpty()) {
            System.out.println("Job with name " + jobName + " does not exist.");
            return false;
        }

        // Create a new collaborator
        Collaborator collaborator = new Collaborator(name, birthDate, admissionDate, address, contactInfo,
                taxpayerNumber, idDocType, idDocNumber);
        collaborator.setJob(optionalJob.get()); // Assign the job to the collaborator

        // Add the collaborator to the repository
        collaboratorRepository.addCollaborator(collaborator);
        return true;
    }
}
