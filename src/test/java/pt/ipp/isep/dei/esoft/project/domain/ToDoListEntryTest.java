package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidGreenSpaceDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidTaskDataException;
import pt.ipp.isep.dei.esoft.project.repository.*;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListEntryTest {
    private static ToDoList toDoList = Repositories.getInstance().getToDoList();
    private static GreenSpaceRepository greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
    private static TaskRepository taskRepository = Repositories.getInstance().getTaskRepository();
    private static CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
    private static SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();
    private Collaborator gsm;
    private GreenSpace greenSpace1;
    private Task task1;
    private GreenSpace greenSpace2;
    private Task task2;
    private ToDoListEntry toDoListEntry1;
    private ToDoListEntry toDoListEntry2;
    @BeforeEach
    public void setUp() throws InvalidCollaboratorDataException, InvalidGreenSpaceDataException, InvalidTaskDataException, InvalidEntryDataException {
        Collaborator gsm = new Collaborator("GSM", new Date(1988, 2, 3), new Date(2020,
                3, 1), "Rua6", "912645629", "gsm@gsm.com", "232139687",
                Collaborator.IdDocType.CC, "122472678cc3", "ABC1234");
        collaboratorRepository.addCollaborator(gsm);
        greenSpace1 = new GreenSpace(GreenSpace.TypeOfGreenSpace.MPARK,"Poro",30.1,"Porto",gsm);
        greenSpace2 = new GreenSpace(GreenSpace.TypeOfGreenSpace.MPARK,"Poro",30.1,"Porto",gsm);
        greenSpaceRepository.addGreenSpace(greenSpace1);
        greenSpaceRepository.addGreenSpace(greenSpace2);
        task1 = new Task("Task one", "7");
        task2 = new Task("Task two", "10");
        taskRepository.addTask(task1);
        taskRepository.addTask(task2);
        toDoListEntry1 = new ToDoListEntry(task1, greenSpace1, ToDoListEntry.DegreeOfUrgency.HIGH);
        toDoListEntry2 = new ToDoListEntry(task2, greenSpace2, ToDoListEntry.DegreeOfUrgency.LOW);
        toDoList.addEntry(toDoListEntry1);
        toDoList.addEntry(toDoListEntry2);
    }

    @Test
    void testGetTask(){
        assertEquals(task1, toDoListEntry1.getTask());
    }
    @Test
    void testGetGreenSpace(){
        assertEquals(greenSpace1, toDoListEntry1.getGreenSpace());
    }
    @Test
    void testGetDegreeOfUrgency(){
        assertEquals(ToDoListEntry.DegreeOfUrgency.HIGH, toDoListEntry1.getDegreeOfUrgency());
    }
    @Test
    void testSetTask(){
        toDoListEntry1.setTask(task2);
        assertEquals(task2, toDoListEntry1.getTask());
    }
    @Test
    void testSetGreenSpace(){
        toDoListEntry1.setGreenSpace(greenSpace2);
        assertEquals(greenSpace2, toDoListEntry1.getGreenSpace());
    }
    @Test
    void testChangeUrgencyToLow(){
        toDoListEntry1.changeUrgencyToLow();
        assertEquals(ToDoListEntry.DegreeOfUrgency.LOW, toDoListEntry1.getDegreeOfUrgency());
    }
    @Test
    void testChangeUrgencyToMedium(){
        toDoListEntry1.changeUrgencyToMedium();
        assertEquals(ToDoListEntry.DegreeOfUrgency.MEDIUM, toDoListEntry1.getDegreeOfUrgency());
    }
    @Test
    void testChangeUrgencyToHigh(){
        toDoListEntry1.changeUrgencyToHigh();
        assertEquals(ToDoListEntry.DegreeOfUrgency.HIGH, toDoListEntry1.getDegreeOfUrgency());
    }
    @Test
    void testToStringDegreeOfUrgency(){
        assertEquals(ToDoListEntry.DegreeOfUrgency.HIGH.toString(), toDoListEntry1.getDegreeOfUrgency().toString());
    }
    @Test
    void testGetFromEnumDegreeOfUrgency(){
        assertEquals(ToDoListEntry.DegreeOfUrgency.HIGH, ToDoListEntry.DegreeOfUrgency.getDegreeOfUrgency("High"));
    }
    @Test
    void testGetFromEnumDegreeOfUrgencyNull(){
        assertThrows(IllegalArgumentException.class, () -> ToDoListEntry.DegreeOfUrgency.getDegreeOfUrgency("da"));
    }
    @AfterAll
    public static void tearDown(){
        toDoList = new ToDoList();
        greenSpaceRepository = new GreenSpaceRepository();
        taskRepository = new TaskRepository();
        collaboratorRepository = new CollaboratorRepository(skillRepository);
        skillRepository = new SkillRepository();
    }


}
