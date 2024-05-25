package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;

/**
 * This class serves as the controller for the functionality of listing all the tasks
 * that the collaborator has.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class ListTaskController {
    /**
     * taskRepository contains all tasks
     */
    private  TaskRepository taskRepository;
    /**
     * collaboratorRepository contains all collaborators
     */
    private  CollaboratorRepository collaboratorRepository;
    /**
     * agenda contains all entries inside the agenda
     */
    private  Agenda agenda;
    /**
     * teamRepository contains all teams
     */
    private  TeamRepository teamRepository;
    /**
     * authenticationRepository authenticates the app
     */
    private  AuthenticationRepository authenticationRepository;
    /**
     * Empty ListTaskController builder.
     *
     */
    public ListTaskController() {
        this.taskRepository = Repositories.getInstance().getTaskRepository();
        this.collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        this.agenda = Repositories.getInstance().getAgenda();
        this.teamRepository = Repositories.getInstance().getTeamRepository();
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }
    /**
     * ListTaskController builder
     *
     * @param taskRepository contains all tasks
     * @param collaboratorRepository contains all collaborators
     * @param agenda contains all tasks
     * @param teamRepository contains all teams
     * @param authenticationRepository authenticates the app
     */
    public ListTaskController(TaskRepository taskRepository, CollaboratorRepository collaboratorRepository, Agenda agenda, TeamRepository teamRepository, AuthenticationRepository authenticationRepository) {
        this.taskRepository = taskRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.agenda = agenda;
        this.teamRepository = teamRepository;
        this.authenticationRepository = authenticationRepository;
    }
    /**
     * Lets the controller get the task repository
     *
     * @return taskRepository
     */
    public TaskRepository getTaskRepository() {
        if (taskRepository == null) {
            Repositories repositories = Repositories.getInstance();

            taskRepository = repositories.getTaskRepository();
        }
        return taskRepository;
    }
    /**
     * Lets the controller get the collaborator repository
     *
     * @return collaboratorRepository
     */
    public CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();

            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }
    /**
     * Lets the controller get the agenda
     *
     * @return agenda
     */
    public Agenda getAgenda() {
        if (agenda == null) {
            Repositories repositories = Repositories.getInstance();

            agenda = repositories.getAgenda();
        }
        return agenda;
    }
    /**
     * Lets the controller get the team repository
     *
     * @return teamRepository
     */
    public TeamRepository getTeamRepository() {
        if (teamRepository == null) {
            Repositories repositories = Repositories.getInstance();

            teamRepository = repositories.getTeamRepository();
        }
        return teamRepository;
    }
    /**
     * Lets the controller get the authentication repository
     *
     * @return authenticationRepository
     */
    public AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }
    /**
     * Lets the controller get the collaborator by email
     *
     * @param email email
     * @return collaborator
     */
    public Collaborator getCollaboratorByEmail(String email) {
        return this.collaboratorRepository.getCollaboratorByEmail(email);
    }
    public List<String> getStatusList() {
        return this.taskRepository.getStatusList();
    }
}
