package pt.ipp.isep.dei.esoft.project.repository;


import java.io.Serializable;

/**
 * It represents a list of all the repositories.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class Repositories implements Serializable {

    private static Repositories instance;
    private JobRepository jobRepository;
    private SkillRepository skillRepository;
    private CollaboratorRepository collaboratorRepository;
    private TeamRepository teamRepository;
    private VehicleRepository vehicleRepository;
    private MaintenanceRepository maintenanceRepository;
    private TaskRepository taskRepository;
    private Agenda agenda;
    private GreenSpaceRepository greenSpaceRepository;
    private ToDoList toDoList;
    // Este está temporariamente no codigo enquanto tentamos perceber o que fazer
    private AuthenticationRepository authenticationRepository;

    /**
     * Creates an instance of Repositories, by instantiating every repository with no parameters
     *
     */
    private Repositories() {
        jobRepository = new JobRepository();
        skillRepository = new SkillRepository();
        collaboratorRepository = new CollaboratorRepository(skillRepository);
        teamRepository = new TeamRepository();
        maintenanceRepository = new MaintenanceRepository();
        authenticationRepository = new AuthenticationRepository();
        vehicleRepository = new VehicleRepository();
        taskRepository = new TaskRepository();
        agenda = new Agenda();
        greenSpaceRepository = new GreenSpaceRepository();
        toDoList = new ToDoList();
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
        return jobRepository;
    }

    /**
     * Get the skill repository.
     * @return skill repository
     */
    public SkillRepository getSkillRepository() {
        return skillRepository;
    }

    /**
     * Get the collaborator repository.
     * @return collaborator repository
     */
    public CollaboratorRepository getCollaboratorRepository() {
        return collaboratorRepository;
    }

    /**
     * Get the team repository.
     * @return team repository
     */
    public TeamRepository getTeamRepository() {
        return teamRepository;
    }

    /**
     * Get the vehicle repository
     *
     * @return vehicleRepository
     */
    public VehicleRepository getVehicleRepository() {
        return vehicleRepository;
    }

    /**
     * Get the maintenance repository
     *
     * @return maintenanceRepository
     */
    public MaintenanceRepository getMaintenanceRepository() {
        return maintenanceRepository;
    }

    /**
     * Get the To do list
     *
     * @return toDoList
     */
    public ToDoList getToDoList() {
        return toDoList;
    }

    /**
     * Get the agenda
     *
     * @return agenda
     */
    public Agenda getAgenda() {
        return agenda;
    }
    /**
     * Get the green space repository
     *
     * @return greenSpaceRepository
     */
    public GreenSpaceRepository getGreenSpaceRepository() {
        return greenSpaceRepository;
    }

    /**
     * Get the task repository
     *
     * @return taskRepository
     */
    public TaskRepository getTaskRepository() {
        return taskRepository;
    }

    /**
     * Get the authentication repository
     *
     * @return authenticationRepository
     */
    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }
}