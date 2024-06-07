package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidGreenSpaceDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidTaskDataException;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EntryTest {
    private List<Entry> entries = new ArrayList<>();
    private static GreenSpaceRepository gsr = Repositories.getInstance().getGreenSpaceRepository();
    private static CollaboratorRepository cr = Repositories.getInstance().getCollaboratorRepository();
    private GreenSpace greenSpace;
    private Collaborator gsm;
    private Task task;
    private Entry anotherEntry;
    private GreenSpace anotherGreenSpace;
    private Task anotherTask;
    private static SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();

    @BeforeEach
    void setUp() throws InvalidTaskDataException, InvalidCollaboratorDataException, InvalidGreenSpaceDataException, InvalidEntryDataException {
        task = new Task("Task", "10");
        anotherTask = new Task("AnotherTask", "20");
        gsm = new Collaborator("GSM", new Date(1988, 2, 3), new Date(2020,
                3, 1), "Rua6", "912645629", "gsm@gsm.com", "232139687",
                Collaborator.IdDocType.CC, "122472678cc3", "ABC1234");

        cr.addCollaborator(gsm);
        anotherGreenSpace = new GreenSpace(GreenSpace.TypeOfGreenSpace.LPARK, "Porto", 100.5, "Porto", gsm);
        greenSpace = new GreenSpace(GreenSpace.TypeOfGreenSpace.GARDEN, "Ovar", 40.5, "Ovar", gsm);
        gsr.addGreenSpace(anotherGreenSpace);
        gsr.addGreenSpace(greenSpace);

        Entry entry = new Entry(task, greenSpace);
        anotherEntry = new Entry(anotherTask, anotherGreenSpace);
        entries.add(entry);
        entries.add(anotherEntry);
    }

    @Test
    void testTaskNull() {
        assertThrows(InvalidEntryDataException.class, () -> {
            Entry entry = new Entry(null, greenSpace);
        });
    }

    @Test
    void testGreenSpaceNull() {
        assertThrows(InvalidEntryDataException.class, () -> {
            Entry entry = new Entry(task, null);
        });
    }

    @Test
    void testSetGreenSpace() {
        anotherEntry.setGreenSpace(greenSpace);
        assertEquals(greenSpace, anotherEntry.getGreenSpace());
    }
    @Test
    void testSetTask() {

        anotherEntry.setTask(task);
        assertEquals(task, anotherEntry.getTask());
    }
    @Test
    void testGetTask() throws InvalidEntryDataException {
        anotherEntry = new Entry(anotherTask, anotherGreenSpace);

        assertEquals(anotherTask, anotherEntry.getTask());
    }
    @Test
    void testGetGreenSpace() throws InvalidEntryDataException {
        anotherEntry = new Entry(anotherTask, anotherGreenSpace);
        assertEquals(anotherGreenSpace, anotherEntry.getGreenSpace());
    }

    @AfterAll
    public static void tearDown() {
        gsr = new GreenSpaceRepository();
        cr = new CollaboratorRepository(skillRepository);
        skillRepository = new SkillRepository();
    }
}
