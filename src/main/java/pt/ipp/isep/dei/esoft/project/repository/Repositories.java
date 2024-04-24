package pt.ipp.isep.dei.esoft.project.repository;

public class Repositories {

    private static Repositories instance;
    private final JobRepository jobRepository;


    private Repositories() {
        jobRepository = new JobRepository();
    }

    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }

    public JobRepository getJobRepository() {
        return jobRepository;
    }


}