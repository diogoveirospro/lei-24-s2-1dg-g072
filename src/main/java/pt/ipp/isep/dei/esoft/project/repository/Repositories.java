package pt.ipp.isep.dei.esoft.project.repository;

public class Repositories {

    private static Repositories instance;
    private JobRepository jobRepository;
    private SkillRepository skillRepository;
    private CollaboratorRepository collaboratorRepository;
    private TeamRepository teamRepository;


    private Repositories() {
        jobRepository = new JobRepository();
        skillRepository = new SkillRepository();
        collaboratorRepository = new CollaboratorRepository();
        teamRepository = new TeamRepository();
    }

    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }

    /**
     * Get the job repository.
     * @return job repository
     */
    public JobRepository getJobRepository() {
        if (jobRepository == null) {

            Repositories repositories = Repositories.getInstance();
            jobRepository = repositories.getJobRepository();

        }
        return jobRepository;
    }

    /**
     * Get the skill repository.
     * @return skill repository
     */
    public SkillRepository getSkillRepository() {
        if (skillRepository == null) {

            Repositories repositories = Repositories.getInstance();
            skillRepository = repositories.getSkillRepository();

        }
        return skillRepository;
    }

    /**
     * Get the collaborator repository.
     * @return collaborator repository
     */
    public CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {

            Repositories repositories = Repositories.getInstance();
            collaboratorRepository = repositories.getCollaboratorRepository();

        }
        return collaboratorRepository;
    }

    /**
     * Get the team repository.
     * @return team repository
     */
    public TeamRepository getTeamRepository() {
        if (teamRepository == null) {

            Repositories repositories = Repositories.getInstance();
            teamRepository = repositories.getTeamRepository();

        }
        return teamRepository;
    }
}