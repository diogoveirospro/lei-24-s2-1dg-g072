package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.ToDoListEntry;

public class ToDoListEntryDto {

    private Task task;

    private GreenSpace greenSpace;

    private ToDoListEntry.DegreeOfUrgency degreeOfUrgency;


    public ToDoListEntryDto(ToDoListEntry toDoListEntry){
        this.task = toDoListEntry.getTask();
        this.greenSpace = toDoListEntry.getGreenSpace();
        this.degreeOfUrgency = toDoListEntry.getDegreeOfUrgency();
    }


    public int getTaskHashCode() {
        return this.task.hashCode();

    }
}
