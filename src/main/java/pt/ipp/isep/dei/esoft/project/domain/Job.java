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
     * @param name job name
     */
    public Job(String name){
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty or null");
        }
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

    /**
     * Rewriting the equals method for the job class.
     * @param obj another job to compare.
     * @return true if they are equally false, otherwise.
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj){
            return true;
        }

        if (obj == null || getClass() != obj.getClass()){
            return false;
        }

        Job anotherJob = (Job) obj;

        return this.getName().equals(anotherJob.getName()) ;
    }

    /**
     * Rewriting the toString method for the job class.
     * @return String representing a job.
     */
    @Override
    public String toString(){
        return this.name;
    }

}
