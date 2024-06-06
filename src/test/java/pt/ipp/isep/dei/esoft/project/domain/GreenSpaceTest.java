package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidGreenSpaceDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidTaskDataException;
import pt.ipp.isep.dei.esoft.project.repository.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GreenSpaceTest {
    private static GreenSpaceRepository gsr = Repositories.getInstance().getGreenSpaceRepository();
    private static CollaboratorRepository cr = Repositories.getInstance().getCollaboratorRepository();
    private static SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();
    private static ToDoList toDoList = Repositories.getInstance().getToDoList();
    private static TaskRepository taskRepository = Repositories.getInstance().getTaskRepository();
    private GreenSpace greenSpace;
    private ToDoListEntry toDoListEntry;
    private Task task;
    private Collaborator gsm;
    private GreenSpace anotherGreenSpace;

    @BeforeEach
    void setUp() throws InvalidCollaboratorDataException, InvalidGreenSpaceDataException, InvalidEntryDataException, InvalidTaskDataException {
        gsm = new Collaborator("GSM"
        );
        cr.addCollaborator(gsm);
        anotherGreenSpace = new GreenSpace(GreenSpace.TypeOfGreenSpace.LPARK, "Porto", 100.5, "Porto", gsm);
        greenSpace = new GreenSpace(GreenSpace.TypeOfGreenSpace.GARDEN, "Ovar", 40.5, "Ovar", gsm);
        gsr.addGreenSpace(anotherGreenSpace);
        gsr.addGreenSpace(greenSpace);
        task = new Task("Task", "10");
        taskRepository.addTask(task);
        toDoListEntry = new ToDoListEntry(task, greenSpace, ToDoListEntry.DegreeOfUrgency.HIGH);
        toDoList.addEntry(toDoListEntry);
        greenSpace.setToDoList(toDoList);
    }


    @Test
    public void testGreenSpaceNull() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            GreenSpace greenSpace = new GreenSpace(GreenSpace.TypeOfGreenSpace.GARDEN, "Ovar", 40.5, "Ovar", null);
        });
    }

    @Test
    public void testGreenSpaceTypeNull() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            GreenSpace greenSpace = new GreenSpace(null, "Ovar", 40.5, "Ovar", gsm);
        });
    }

    @Test
    public void testGreenSpaceAddressNull() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            GreenSpace greenSpace = new GreenSpace(GreenSpace.TypeOfGreenSpace.GARDEN, "Ovar", 40.5, null, gsm);
        });
    }

    @Test
    public void testGreenSpaceDimensionNegative() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            GreenSpace greenSpace = new GreenSpace(GreenSpace.TypeOfGreenSpace.GARDEN, "Ovar", -40.5, "Ovar", gsm);
        });
    }

    @Test
    public void testGreenSpaceDimensionZero() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            GreenSpace greenSpace = new GreenSpace(GreenSpace.TypeOfGreenSpace.GARDEN, "Ovar", 0, "Ovar", gsm);
        });
    }

    @Test
    public void testAddressInvalid() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            GreenSpace greenSpace = new GreenSpace(GreenSpace.TypeOfGreenSpace.GARDEN, "Ovar", 40.5, "", gsm);
        });
    }

    @Test
    public void testGreenSpaceNameNull() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            GreenSpace greenSpace = new GreenSpace(GreenSpace.TypeOfGreenSpace.GARDEN, null, 40.5, "Ovar", gsm);
        });
    }

    @Test
    public void testGreenSpaceNameEmpty() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            GreenSpace greenSpace = new GreenSpace(GreenSpace.TypeOfGreenSpace.GARDEN, "", 40.5, "Ovar", gsm);
        });
    }

    @Test
    public void testGreenSpaceTypeInvalid() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            GreenSpace greenSpace = new GreenSpace(null, "Ovar", 40.5, "Ovar", gsm);
        });
    }

    @Test
    public void testGreenSpaceAddressEmpty() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            GreenSpace greenSpace = new GreenSpace(GreenSpace.TypeOfGreenSpace.GARDEN, "Ovar", 40.5, "", gsm);
        });
    }

    @Test
    public void testGreenSpaceManagerNull() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            GreenSpace greenSpace = new GreenSpace(GreenSpace.TypeOfGreenSpace.GARDEN, "Ovar", 40.5, "Ovar", null);
        });
    }

    @Test
    public void testAddressNull() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            greenSpace.setAddress(null);
        });
    }

    @Test
    public void testSetAddress() throws InvalidGreenSpaceDataException {
        greenSpace.setAddress("Aveiro");
        assertEquals("Aveiro", greenSpace.getAddress());
    }

    @Test
    public void testSetAddressNull() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            greenSpace.setAddress(null);
        });
    }

    @Test
    public void testSetDimension() throws InvalidGreenSpaceDataException {
        greenSpace.setDimension(50.5);
        assertEquals(50.5, greenSpace.getDimension());
    }

    @Test
    public void testSetDimensionNegative() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            greenSpace.setDimension(-50.5);
        });
    }

    @Test
    public void testSetDimensionZero() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            greenSpace.setDimension(0);
        });
    }

    @Test
    public void testSetParkName() throws InvalidGreenSpaceDataException {
        greenSpace.setParkName("Porto");
        assertEquals("Porto", greenSpace.getParkName());
    }

    @Test
    public void testSetParkNameNull() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            greenSpace.setParkName(null);
        });
    }

    @Test
    public void testSetParkNameEmpty() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            greenSpace.setParkName("");
        });
    }

    @Test
    public void testSetType() throws InvalidGreenSpaceDataException {
        greenSpace.setType(GreenSpace.TypeOfGreenSpace.LPARK);
        assertEquals(GreenSpace.TypeOfGreenSpace.LPARK, greenSpace.getType());
    }

    @Test
    public void testSetTypeNull() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            greenSpace.setType(null);
        });
    }

    @Test
    public void testGetToDoList() {
        assertEquals(greenSpace.getToDoList(), toDoList);
    }

    @Test
    public void testSetToDoList() throws InvalidGreenSpaceDataException {
        ToDoList toDoList2 = greenSpace.getToDoList();
        anotherGreenSpace.setToDoList(toDoList2);
        assertEquals(anotherGreenSpace.getToDoList(), toDoList2);
    }

    @Test
    public void testSetToDoListNull() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            greenSpace.setToDoList(null);
        });
    }

    @Test
    public void testSetGreenSpaceManager() throws InvalidGreenSpaceDataException {
        greenSpace.setGreenSpaceManager(gsm);
        assertEquals(gsm, greenSpace.getGreenSpaceManager());
    }

    @Test
    public void testSetGreenSpaceManagerNull() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            greenSpace.setGreenSpaceManager(null);
        });
    }

    @Test
    public void testSetParkNameInvalid() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            greenSpace.setParkName("Porto1");
        });
    }

    @Test
    public void testSetDimensionInvalid() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            greenSpace.setDimension(-50.5);
        });
    }

    @Test
    public void testGetGreenSpaceManager() {
        assertEquals(gsm, greenSpace.getGreenSpaceManager());
    }

    @Test
    public void testGetTypeOfGreenSpace() {
        assertEquals(GreenSpace.TypeOfGreenSpace.GARDEN, greenSpace.getType());
    }

    @Test
    public void testToStringTypeOfGreenSpace() {
        String result = greenSpace.getType().toString();
        assertEquals("Garden", result);
    }

    @Test
    public void testGetTypeOfGreenSpaceEnum() {
        assertEquals(GreenSpace.TypeOfGreenSpace.GARDEN.getType(), GreenSpace.TypeOfGreenSpace.valueOf("GARDEN").getType());
    }

    @Test
    public void getTypeOfGreenSpaceFromType() throws InvalidGreenSpaceDataException {
        assertEquals(GreenSpace.TypeOfGreenSpace.GARDEN, GreenSpace.TypeOfGreenSpace.getTypeOfGreenSpace("Garden"));
    }

    @Test
    public void getTypeOfGreenSpaceFromTypeNull() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            GreenSpace.TypeOfGreenSpace.getTypeOfGreenSpace(null);
        });
    }

    @Test
    public void testCollaboratorDoesNotExist() throws InvalidCollaboratorDataException {
        Collaborator collaborator = new Collaborator("GSM"
        );
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            GreenSpace greenSpace = new GreenSpace(GreenSpace.TypeOfGreenSpace.GARDEN, "Ovar", 40.5, "Ovar", collaborator);
        });
    }



    @AfterAll
    public static void tearDown() {
        gsr = new GreenSpaceRepository();
        cr = new CollaboratorRepository(skillRepository);
        skillRepository = new SkillRepository();
        toDoList = new ToDoList();
        taskRepository = new TaskRepository();
    }
}
