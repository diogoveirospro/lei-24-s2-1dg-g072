package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

/**
 * RegisterJobController is a class responsible for making requests related to the registration of jobs, requested by the UI.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class RegisterJobUIController {
    /**
     * Repository containing the jobs.
     */
    private JobRepository jobRepository;

    /**
     * Empty RegisterJobController builder.
     */
    public RegisterJobUIController(){
        this.jobRepository = Repositories.getInstance().getJobRepository();
    }

    /**
     * RegisterJobController builder.
     * @param jobRepository job repository
     */
    public RegisterJobUIController(JobRepository jobRepository){

        this.jobRepository = jobRepository;
    }

    /**
     * Register a job
     * @param name job name
     */
    public void registerJob(String name) {

        Job job = new Job(name);
        jobRepository.addJob(job);
    }

}
