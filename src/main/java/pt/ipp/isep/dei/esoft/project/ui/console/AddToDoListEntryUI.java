package pt.ipp.isep.dei.esoft.project.ui.console;



import pt.ipp.isep.dei.esoft.project.domain.ToDoListEntry;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.TaskRepository;

import java.util.Scanner;

public class AddToDoListEntryUI {

    private TaskRepository taskRepository;
    private GreenSpaceRepository greenSpaceRepository;
    private Scanner scanner;

    public AddToDoListEntryUI(TaskRepository taskRepository, GreenSpaceRepository greenSpaceRepository) {
        this.taskRepository = taskRepository;
        this.greenSpaceRepository = greenSpaceRepository;
        this.scanner = new Scanner(System.in);
    }

    public void showAddToDoListEntryMenu() {
        System.out.println("=== Add To-Do List Entry ===");


        System.out.print("Enter Task ID: ");
        String taskId = scanner.nextLine();


        System.out.print("Enter Green Space ID: ");
        String greenSpaceId = scanner.nextLine();


        System.out.print("Enter Degree of Urgency (LOW/MEDIUM/HIGH): ");
        String urgency = scanner.nextLine().toUpperCase();


        ToDoListEntry.DegreeOfUrgency degreeOfUrgency = ToDoListEntry.DegreeOfUrgency.valueOf(urgency);



        System.out.println("To do list entry added successfully!");
    }
}
