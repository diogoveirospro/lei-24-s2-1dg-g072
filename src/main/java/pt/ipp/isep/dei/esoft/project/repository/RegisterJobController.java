package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Job;

public class RegisterJobController {
    private JobRepository jobRepository;

    public RegisterJobController (){
        this.jobRepository = Repositories.getInstance().getJobRepository();
    }
    public void registerJob (String name){
        Job job = new Job(name);
        jobRepository.addJob(job);
    }
}
