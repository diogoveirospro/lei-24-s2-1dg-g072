package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidTaskDataException;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;

/**
 * This class serves as the controller for the functionality of finishing a task
 * assigned to the collaborator.
 *
 * @autor Group 072 - Byte Masters - ISEP
 */
public class FinishTaskController {
    /**
     * teamRepository contains all teams
     */
    private TeamRepository teamRepository;
    /**
     * taskRepository contains all tasks
     */
    private TaskRepository taskRepository;
    /**
     * collaboratorRepository contains all collaborators
     */
    private CollaboratorRepository collaboratorRepository;
    /**
     * agenda contains all entries inside the agenda
     */
    private Agenda agenda;
    private String String;

    /**
     * Empty FinishTaskController builder.
     */
    public FinishTaskController() {
        this.teamRepository = Repositories.getInstance().getTeamRepository();
        this.taskRepository = Repositories.getInstance().getTaskRepository();
        this.collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        this.agenda = Repositories.getInstance().getAgenda();
    }

    /**
     * FinishTaskController builder
     *
     * @param taskRepository         contains all tasks
     * @param collaboratorRepository contains all collaborators
     * @param agenda                 contains all tasks
     */
    public FinishTaskController(TaskRepository taskRepository, CollaboratorRepository collaboratorRepository, Agenda agenda, TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
        this.taskRepository = taskRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.agenda = agenda;
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
     * Retrieves the agenda entries for the teams associated with the currently logged-in collaborator.
     *
     * This method fetches the current collaborator from the session, retrieves the teams they belong to,
     * and then gets the agenda entries for those teams.
     *
     * @return a list of agenda entries associated with the teams of the current collaborator
     * @throws InvalidCollaboratorDataException if the collaborator data is invalid or not found
     */

    public List<AgendaEntry> getAgendaEntries() throws InvalidCollaboratorDataException {
        Collaborator collaborator = getCollaboratorFromSession();
        List<Team> teamList = this.teamRepository.getTeamsByCollaborator(collaborator);
        return agenda.getAgendaEntriesByTeamList(teamList);
    }

    /**
     * Finish a task by its ID.
     *
     * @param taskId the ID of the task to finish
     */
    public void finishTask(String taskId) throws InvalidTaskDataException {
        AgendaEntry agendaEntry = agenda.getAgendaEntry(taskId);
        agendaEntry.taskDone();
    }

    /**
     * Retrieves the currently logged-in collaborator from the session.
     *
     * This method fetches the email of the current user from the application session
     * and retrieves the corresponding collaborator from the repository.
     *
     * @return the collaborator associated with the currently logged-in user
     */

    private Collaborator getCollaboratorFromSession() {
        String email = ApplicationSession.getInstance().getCurrentSession().getUserEmail();
        return collaboratorRepository.getCollaboratorByEmail(email);
    }
}
