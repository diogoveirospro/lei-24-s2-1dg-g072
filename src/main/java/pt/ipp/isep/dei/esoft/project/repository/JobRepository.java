package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents a list of all jobs already created.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class JobRepository {
    /**
     * List containing all jobs.
     */
    private final List<Job> jobs;

    /**
     * Job Repository builder.
     */
    public JobRepository(){
        jobs = new ArrayList<>();
    }

    /**
     * Get a job from the repository by its name.
     * @param name Job name
     * @return job
     */
    public Job getJob(String name){
        Job newJob = new Job(name);
        Job job = null;

        if (jobs.contains(newJob)){
            job = jobs.get(jobs.indexOf(newJob));
        }

        if (job == null){
            throw new IllegalArgumentException("Job " + name + " does not exist.");
        }

        return job;
    }

    /**
     * Add a job to the repository.
     * @param newJob new job.
     */
    public void addJob(Job newJob){
        if (!validateJob(newJob)) {
            throw new IllegalArgumentException("Job already exists!");
        }
        if (newJob == null) {
            throw new IllegalArgumentException("Job cannot be null!");
        }

        jobs.add(newJob);

    }

    /**
     * Private method to see if a job is already in the repository.
     * @param job job to be checked
     * @return True if the job is not yet in the repository and false otherwise.
     */
    private boolean validateJob(Job job){
        return !jobs.contains(job);
    }

    /**
     * This method returns a defensive (immutable) copy of the job list.
     * @return The job list.
     */
    public List<Job> getJobs(){
        sortJobsAlphabetically();
        return List.copyOf(jobs);
    }

    /**
     * Checks that the job exists in the repository.
     * @param jobName name of the job to be searched.
     * @return true if it exists in the repository and false otherwise.
     */
    public boolean exists(String jobName){
        Job job = new Job(jobName);
        return jobs.contains(job);
    }

    public void sortJobsAlphabetically() {
        for (int i = 0; i < jobs.size() - 1; i++) {
            for (int j = i + 1; j < jobs.size(); j++) {
                String name1 = jobs.get(i).getName();
                String name2 = jobs.get(j).getName();
                if (name1.compareToIgnoreCase(name2) > 0) {
                    Job aux = jobs.get(i);
                    jobs.set(i, jobs.get(j));
                    jobs.set(j, aux);
                }
            }
        }
    }
}
