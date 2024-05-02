package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

/**
 * The HRM class is responsible for handling the
 * registration and management of jobs within the system.
 * It uses the JobRepository to persist job information.
 */

public class HRM {
    private JobRepository jobRepository;

    /**
     * Constructs a new HRM object and initializes it with a JobRepository instance
     * from the central Repositories class.
     */

    public HRM(){
        this.jobRepository = Repositories.getInstance().getJobRepository();
    }

    /**
     * Registers a new job with the specified name into the system.
     * This method creates a new Job instance and adds it to the JobRepository.
     *
     * @param name the name of the new job to addSkill
     */

    public void registerJob (String name){
        Job job = new Job(name);
        jobRepository.addJob(job);
    }
}
