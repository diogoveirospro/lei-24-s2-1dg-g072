package pt.ipp.isep.dei.esoft.project.application.controller;


import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidTaskDataException;
import pt.ipp.isep.dei.esoft.project.Mapper.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.ToDoListEntry;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;

/**
 * Controller class for adding to-do list entries.
 * Provides methods for managing tasks, green spaces, and to-do list entries.
 */

public class AddToDoListController {
    private TaskRepository taskRepository;
    private ToDoList toDoList;
    private GreenSpaceRepository greenSpaceRepository;
    private CollaboratorRepository collaboratorRepository;

    /**
     * Constructs an AddToDoListController with the specified repositories and to-do list.
     *
     * @param taskRepository the repository for tasks
     * @param greenSpaceRepository the repository for green spaces
     * @param collaboratorRepository the repository for collaborators
     * @param toDoList the to-do list
     */

    public AddToDoListController(TaskRepository taskRepository, GreenSpaceRepository greenSpaceRepository, CollaboratorRepository collaboratorRepository, ToDoList toDoList) {
        this.taskRepository = taskRepository;
        this.greenSpaceRepository = greenSpaceRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.toDoList = toDoList;
    }

    /**
     * Constructs an AddToDoListController with default repositories and to-do list.
     */

    public AddToDoListController() {
        this.taskRepository = Repositories.getInstance().getTaskRepository();
        this.greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
        this.collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        this.toDoList = Repositories.getInstance().getToDoList();
    }

    /**
     * Retrieves a collaborator by email.
     *
     * @param email the email of the collaborator to retrieve
     * @return the collaborator with the specified email
     */

    public Collaborator getCollaboratorByEmail(String email) {
        return this.collaboratorRepository.getCollaboratorByEmail(email);
    }

    /**
     * Retrieves a list of GreenSpaceDto objects managed by the currently logged-in collaborator.
     *
     * @return a list of GreenSpaceDto objects
     */

    public List<GreenSpaceDto> getGreenSpaceList() {
        Collaborator greenSpaceManager = getCollaboratorFromSession();
        List<GreenSpace> greenSpaceList = greenSpaceRepository.getListGreenSpacesManagedByGsm(greenSpaceManager);
        GreenSpaceMapper mapper = new GreenSpaceMapper();
        return mapper.greenSpaceListToDto(greenSpaceList);
    }

    /**
     * Retrieves the currently logged-in collaborator from the session.
     *
     * @return the currently logged-in collaborator
     */

    private Collaborator getCollaboratorFromSession() {
        String email = ApplicationSession.getInstance().getCurrentSession().getUserEmail();
        return collaboratorRepository.getCollaboratorByEmail(email);
    }

    /**
     * Retrieves a list of degree of urgency options.
     *
     * @return a list of degree of urgency options
     */

    public List<String> getDegreeOgUrgencyList() {
        toDoList = Repositories.getInstance().getToDoList();
        return toDoList.getDegreeOfUrgencyList();
    }

    /**
     * Adds a new to-do list entry.
     *
     * @param taskName the name of the task
     * @param duration the duration of the task
     * @param greenSpaceName the name of the green space
     * @param degreeOfUrgency the degree of urgency
     * @throws InvalidTaskDataException if the task data is invalid
     * @throws InvalidEntryDataException if the entry data is invalid
     */

    public void addNewToDoListEntry(String taskName, String duration, String greenSpaceName, String degreeOfUrgency) throws InvalidTaskDataException, InvalidEntryDataException {
        GreenSpace greenSpace = greenSpaceRepository.getGreenSpaceByParkName(greenSpaceName);
        ToDoListEntry.DegreeOfUrgency degree = ToDoListEntry.DegreeOfUrgency.getDegreeOfUrgency(degreeOfUrgency);
        Task task = new Task(taskName, duration);
        taskRepository.addTask(task);
        ToDoListEntry toDoListEntry = new ToDoListEntry(task, greenSpace, degree);
        toDoList.addEntry(toDoListEntry);
    }

    /**
     * Retrieves a list of all tasks.
     *
     * @return a list of tasks
     */

    public List<Task> getTaskList() {
        return taskRepository.getAllTasks();
    }
}