package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidTaskDataException;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

/**
 * This class serves as the controller for the functionality of finishing a task
 * assigned to the collaborator.
 *
 * @autor Group 072 - Byte Masters - ISEP
 */
public class FinishTaskController {
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
    private java.lang.String String;

    /**
     * Empty FinishTaskController builder.
     */
    public FinishTaskController() {
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
    public FinishTaskController(TaskRepository taskRepository, CollaboratorRepository collaboratorRepository, Agenda agenda) {
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
     * Finish a task by its ID.
     *
     * @param taskId the ID of the task to finish
     */
    public void finishTask(String taskId) throws InvalidTaskDataException {
        AgendaEntry agendaEntry = agenda.getAgendaEntry(taskId);
        agendaEntry.taskDone();
    }


    private Collaborator getCollaboratorFromSession() {
        String email = ApplicationSession.getInstance().getCurrentSession().getUserEmail();
        return collaboratorRepository.getCollaboratorByEmail(email);
    }
}
