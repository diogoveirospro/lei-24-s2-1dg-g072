package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class RegisterJobController {

    private JobRepository jobRepository;

    public RegisterJobController(){
        getJobRepository();
    }

    public RegisterJobController(JobRepository jobRepository){

        this.jobRepository = jobRepository;
    }

    private JobRepository getJobRepository() {
        if (jobRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
    }

    public Job registerJob(String name) {

        if (!validateJob(newJob)) {
            throw new IllegalArgumentException("Invalid job to add");
        }
    }

    private validateRegisterJob(String name){
        return;
    }



}
