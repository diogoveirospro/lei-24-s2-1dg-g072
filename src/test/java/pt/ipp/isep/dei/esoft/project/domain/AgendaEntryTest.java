package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidAgendaEntryDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidGreenSpaceDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidTaskDataException;
import pt.ipp.isep.dei.esoft.project.domain.*;

import static org.junit.jupiter.api.Assertions.*;

class AgendaEntryTest {

    private Task task;
    private GreenSpace greenSpace;
    private Date startDate;
    private AgendaEntry.HourOfDay startHour;
    private Date endDate;
    private AgendaEntry.HourOfDay endHour;

    @BeforeEach
    void setUp() {
        try {
            task = new Task("Task", "10");
            greenSpace = new GreenSpace(GreenSpace.TypeOfGreenSpace.GARDEN, "Garden", 100.0, "123 Main St", new Collaborator("Manager", new Date(1990, 1, 1), new Date(2020, 1, 1), "123456789", "example@example.com", "123 Main St", "Manager", Collaborator.IdDocType.BI, "PT"));
            startDate = new Date(2024, 6, 3);
            startHour = AgendaEntry.HourOfDay.H08;
            endDate = new Date(2024, 6, 4);
            endHour = AgendaEntry.HourOfDay.H12;
        } catch (InvalidTaskDataException | InvalidGreenSpaceDataException | InvalidCollaboratorDataException e) {
            fail("Exception thrown when it shouldn't have been: " + e.getMessage());
        }
    }

    @Test
    void constructor_ValidData_Success() {
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            assertNotNull(agendaEntry);
        } catch (InvalidAgendaEntryDataException e) {
            fail("Exception thrown when it shouldn't have been: " + e.getMessage());
        }
    }

    @Test
    void constructor_InvalidData_ExceptionThrown() {
        assertThrows(InvalidAgendaEntryDataException.class, () -> new AgendaEntry(null, greenSpace, startDate, startHour, endDate, endHour));
        assertThrows(InvalidAgendaEntryDataException.class, () -> new AgendaEntry(task, null, startDate, startHour, endDate, endHour));
        assertThrows(InvalidAgendaEntryDataException.class, () -> new AgendaEntry(task, greenSpace, null, startHour, endDate, endHour));
        assertThrows(InvalidAgendaEntryDataException.class, () -> new AgendaEntry(task, greenSpace, startDate, null, endDate, endHour));
        assertThrows(InvalidAgendaEntryDataException.class, () -> new AgendaEntry(task, greenSpace, startDate, startHour, null, endHour));
        assertThrows(InvalidAgendaEntryDataException.class, () -> new AgendaEntry(task, greenSpace, startDate, startHour, endDate, null));
    }

    @Test
    void getStartDate_ValidData_Success() {
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            assertEquals(startDate, agendaEntry.getStartDate());
        } catch (InvalidAgendaEntryDataException e) {
            fail("Exception thrown when it shouldn't have been: " + e.getMessage());
        }
    }

    @Test
    void getStartHour_ValidData_Success() {
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            assertEquals(startHour, agendaEntry.getStartHour());
        } catch (InvalidAgendaEntryDataException e) {
            fail("Exception thrown when it shouldn't have been: " + e.getMessage());
        }
    }

    @Test
    void getEndDate_ValidData_Success() {
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            assertEquals(endDate, agendaEntry.getEndDate());
        } catch (InvalidAgendaEntryDataException e) {
            fail("Exception thrown when it shouldn't have been: " + e.getMessage());
        }
    }

    @Test
    void getEndHour_ValidData_Success() {
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            assertEquals(endHour, agendaEntry.getEndHour());
        } catch (InvalidAgendaEntryDataException e) {
            fail("Exception thrown when it shouldn't have been: " + e.getMessage());
        }
    }

    @Test
    void getStatus_InitialStatus_NotNull() {
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            assertNotNull(agendaEntry.getStatus());
        } catch (InvalidAgendaEntryDataException e) {
            fail("Exception thrown when it shouldn't have been: " + e.getMessage());
        }
    }

    @Test
    void setStartDate_ValidData_Success() {
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            Date newStartDate = new Date(2024, 6, 5);
            agendaEntry.setStartDate(newStartDate);
            assertEquals(newStartDate, agendaEntry.getStartDate());
        } catch (InvalidAgendaEntryDataException e) {
            fail("Exception thrown when it shouldn't have been: " + e.getMessage());
        }
    }
}




