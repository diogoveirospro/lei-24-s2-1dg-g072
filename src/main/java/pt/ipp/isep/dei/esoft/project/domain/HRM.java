package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class HRM {
    private JobRepository jobRepository;

    public HRM(){
        this.jobRepository = Repositories.getInstance().getJobRepository();
    }

    public void registerJob (String name){
        Job job = new Job(name);
        jobRepository.addJob(job);
    }
}
