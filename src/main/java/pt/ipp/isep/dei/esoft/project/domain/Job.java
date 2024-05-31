package pt.ipp.isep.dei.esoft.project.domain;

/**
 * It represents a job with all its attributes.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class Job implements Comparable<Job> {
    /**
     * Name that represents the job.
     */
    private String name;

    /**
     * Job builder.
     * @param name job name
     */
    public Job(String name){
        if(!validateJobName(name)){
            throw new IllegalArgumentException("Name cannot be empty, null or contain numbers or special characters ");
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
        if(!validateJobName (name)){
            throw new IllegalArgumentException("Name cannot be empty, null or contain numbers or special characters ");
        }
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
     * Checks if the job name adheres to the specified pattern (letters and spaces only).
     * @param name the name of the job.
     * @return true if the name is valid, false otherwise.
     */

    public boolean validateJobName(String name){
        if(name == null||name.trim().isEmpty()){
            return false;
        }
        String pattern = "^[a-zA-Z ]+$";
        return name.matches(pattern);
    }

    /**
     * Rewriting the toString method for the job class.
     * @return String representing a job.
     */
    @Override
    public String toString(){
        return this.name;
    }

    /**
     * Compares this job to another job by name.
     *
     * @param job the job to be compared
     * @return a negative integer, zero, or a positive integer as this job's name
     *         is less than, equal to, or greater than the specified job's name
     */
    @Override
    public int compareTo(Job job){
        return this.name.compareTo(job.getName());
    }
}
