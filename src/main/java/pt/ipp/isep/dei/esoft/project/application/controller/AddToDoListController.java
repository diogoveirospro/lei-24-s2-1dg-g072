package pt.ipp.isep.dei.esoft.project.application.controller;


import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.ToDoListEntry;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.TaskRepository;

public class AddToDoListController {
    private TaskRepository taskRepository;
    private GreenSpaceRepository greenSpaceRepository;

    public AddToDoListController(TaskRepository taskRepository, GreenSpaceRepository greenSpaceRepository) {
        this.taskRepository = taskRepository;
        this.greenSpaceRepository = greenSpaceRepository;
    }

    public void addNewToDoListEntry(String taskId, String greenSpaceId, ToDoListEntry.DegreeOfUrgency degreeOfUrgency) {
        Task task = taskRepository.findTaskById(taskId);
        GreenSpace greenSpace = greenSpaceRepository.findGreenSpaceById(greenSpaceId);

        ToDoListEntry toDoListEntry = new ToDoListEntry(task, greenSpace, degreeOfUrgency);


    }
}


