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

public class AddToDoListController {
    private TaskRepository taskRepository;
    private ToDoList toDoList;
    private GreenSpaceRepository greenSpaceRepository;
    private CollaboratorRepository collaboratorRepository;

    public AddToDoListController(TaskRepository taskRepository, GreenSpaceRepository greenSpaceRepository, CollaboratorRepository collaboratorRepository, ToDoList toDoList) {
        this.taskRepository = taskRepository;
        this.greenSpaceRepository = greenSpaceRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.toDoList = toDoList;
    }
    public AddToDoListController() {
        this.taskRepository = Repositories.getInstance().getTaskRepository();
        this.greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
        this.collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        this.toDoList = Repositories.getInstance().getToDoList();
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
    public List<GreenSpaceDto> getGreenSpaceList(){
        Collaborator greenSpaceManager = getCollaboratorFromSession();
        List<GreenSpace> greenSpaceList = greenSpaceRepository.getListGreenSpacesManagedByGsm(greenSpaceManager);
        GreenSpaceMapper mapper = new GreenSpaceMapper();
        return mapper.greenSpaceListToDto(greenSpaceList);
    }
    private Collaborator getCollaboratorFromSession() {
        String email = ApplicationSession.getInstance().getCurrentSession().getUserEmail();
        return collaboratorRepository.getCollaboratorByEmail(email);
    }
    public List<String> getDegreeOgUrgencyList() {
        toDoList = Repositories.getInstance().getToDoList();
        return toDoList.getDegreeOfUrgencyList();
    }


    public void addNewToDoListEntry(String taskName,String duration, String greenSpaceName, String degreeOfUrgency) throws InvalidTaskDataException, InvalidEntryDataException {
        GreenSpace greenSpace = greenSpaceRepository.getGreenSpaceByParkName(greenSpaceName);
        ToDoListEntry.DegreeOfUrgency degree = ToDoListEntry.DegreeOfUrgency.getDegreeOfUrgency(degreeOfUrgency);
        Task task = new Task(taskName, duration);
        ToDoListEntry toDoListEntry = new ToDoListEntry(task, greenSpace, degree);
        toDoList.addEntry(toDoListEntry);
    }
}


