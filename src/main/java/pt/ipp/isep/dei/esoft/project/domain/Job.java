package pt.ipp.isep.dei.esoft.project.domain;

/**
 * It represents a job with all its attributes.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class Job {
    /**
     * Name that represents the job.
     */
    private String name;

    /**
     * Job builder.
     * @param name
     */
    public Job(String name){
        this.name = name;
    }

    /**
     * Get the name of the job.
     * @return name of job.
     */
    public String getName() {
        return name;
    }

    /**
     * Change the job name
     * @param name new job name.
     */
    public void setName(String name) {
        this.name = name;
    }


}
