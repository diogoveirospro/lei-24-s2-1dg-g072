package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidGreenSpaceDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidTaskDataException;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AgendaEntryTest {

    private Task task;
    private GreenSpace greenSpace;
    private Date startDate;
    private AgendaEntry.WorkingDayHours startHour;
    private Date endDate;
    private AgendaEntry.WorkingDayHours endHour;
    private CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
    private VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
    private TeamRepository teamRepository = Repositories.getInstance().getTeamRepository();

    @BeforeEach
    void setUp() {
        try {
            task = new Task("Task", "10");
            Collaborator gsm = new Collaborator("GSM", new Date(1988, 2, 3), new Date(2020,
                    3, 1), "Rua6", "912645629", "gsm@gsm.com", "232139687",
                    Collaborator.IdDocType.CC, "122472678cc3", "ABC1234");
            collaboratorRepository.addCollaborator(gsm);

            greenSpace = new GreenSpace(GreenSpace.TypeOfGreenSpace.GARDEN, "Garden", 100.0, "123 Main St", gsm);
            startDate = new Date(2024, 6, 3);
            startHour = AgendaEntry.WorkingDayHours.H09;
            endDate = new Date(2024, 6, 4);
            endHour = AgendaEntry.WorkingDayHours.H12;

        } catch (InvalidTaskDataException | InvalidGreenSpaceDataException | InvalidCollaboratorDataException e) {
            fail("Exception thrown when it shouldn't have been: " + e.getMessage());
        }
    }

    @Test
    void constructor_ValidData_Success() {
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            assertNotNull(agendaEntry);
        } catch (InvalidEntryDataException e) {
            fail("Exception thrown when it shouldn't have been: " + e.getMessage());
        }
    }

    @Test
    void constructor_InvalidData_ExceptionThrown() {
        assertThrows(InvalidEntryDataException.class, () -> new AgendaEntry(null, greenSpace, startDate, startHour, endDate, endHour));
        assertThrows(InvalidEntryDataException.class, () -> new AgendaEntry(task, null, startDate, startHour, endDate, endHour));
        assertThrows(InvalidEntryDataException.class, () -> new AgendaEntry(task, greenSpace, null, startHour, endDate, endHour));
        assertThrows(InvalidEntryDataException.class, () -> new AgendaEntry(task, greenSpace, startDate, null, endDate, endHour));
        assertThrows(InvalidEntryDataException.class, () -> new AgendaEntry(task, greenSpace, startDate, startHour, null, endHour));
        assertThrows(InvalidEntryDataException.class, () -> new AgendaEntry(task, greenSpace, startDate, startHour, endDate, null));
    }

    @Test
    void getStartDate_ValidData_Success() {
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            assertEquals(startDate, agendaEntry.getStartDate());
        } catch (InvalidEntryDataException e) {
            fail("Exception thrown when it shouldn't have been: " + e.getMessage());
        }
    }

    @Test
    void getStartHour_ValidData_Success() {
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            assertEquals(startHour, agendaEntry.getStartHour());
        } catch (InvalidEntryDataException e) {
            fail("Exception thrown when it shouldn't have been: " + e.getMessage());
        }
    }

    @Test
    void getEndDate_ValidData_Success() {
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            assertEquals(endDate, agendaEntry.getEndDate());
        } catch (InvalidEntryDataException e) {
            fail("Exception thrown when it shouldn't have been: " + e.getMessage());
        }
    }

    @Test
    void getEndHour_ValidData_Success() {
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            assertEquals(endHour, agendaEntry.getEndHour());
        } catch (InvalidEntryDataException e) {
            fail("Exception thrown when it shouldn't have been: " + e.getMessage());
        }
    }

    @Test
    void getStatus_InitialStatus_NotNull() {
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            assertNotNull(agendaEntry.getStatus());
        } catch (InvalidEntryDataException e) {
            fail("Exception thrown when it shouldn't have been: " + e.getMessage());
        }
    }

    @Test
    void setStartDate_ValidData_Success() {
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            Date newStartDate = new Date(2024, 6, 2);
            agendaEntry.setStartDate(newStartDate);
            assertEquals(newStartDate, agendaEntry.getStartDate());
        } catch (InvalidEntryDataException e) {
            fail("Exception thrown when it shouldn't have been: " + e.getMessage());
        }
    }

    @Test
    void getHourOfWorkingDayHoursTest(){
        assertEquals("12:00", AgendaEntry.WorkingDayHours.H12.getHour());
        assertEquals("16:00", AgendaEntry.WorkingDayHours.H16.getHour());
    }

    @Test
    void toStringWorkingDayHoursTest(){
        assertEquals("12:00", AgendaEntry.WorkingDayHours.H12.toString());
        assertEquals("16:00", AgendaEntry.WorkingDayHours.H16.toString());
    }

    @Test
    void fromStringWorkingDayHoursTest() throws InvalidEntryDataException {
        assertEquals(AgendaEntry.WorkingDayHours.H12, AgendaEntry.WorkingDayHours.fromString("12:00"));
        assertEquals(AgendaEntry.WorkingDayHours.H16, AgendaEntry.WorkingDayHours.fromString("16:00"));

        assertThrows(InvalidEntryDataException.class, () -> AgendaEntry.WorkingDayHours.fromString("08:01"));
    }

    @Test
    void getAllWorkingDayHoursTest(){
        assertEquals(8, AgendaEntry.WorkingDayHours.getAllHours().size());
    }

    @Test
    void isGreaterWorkingDayHoursTest(){
        assertTrue(AgendaEntry.WorkingDayHours.H17.isGreater(AgendaEntry.WorkingDayHours.H16));
        assertTrue(AgendaEntry.WorkingDayHours.H16.isGreater(AgendaEntry.WorkingDayHours.H12));
        assertTrue(AgendaEntry.WorkingDayHours.H12.isGreater(AgendaEntry.WorkingDayHours.H09));
        assertFalse(AgendaEntry.WorkingDayHours.H09.isGreater(AgendaEntry.WorkingDayHours.H12));
        assertFalse(AgendaEntry.WorkingDayHours.H12.isGreater(AgendaEntry.WorkingDayHours.H16));
        assertFalse(AgendaEntry.WorkingDayHours.H16.isGreater(AgendaEntry.WorkingDayHours.H17));
        assertFalse(AgendaEntry.WorkingDayHours.H16.isGreater(AgendaEntry.WorkingDayHours.H16));
        assertFalse(AgendaEntry.WorkingDayHours.H16.isGreater(null));
    }

    @Test
    void getStatusOfStatusOfEntryTest(){
        assertEquals("Scheduled", AgendaEntry.StatusOfEntry.SCHEDULED.getStatus());
        assertEquals("Postponed", AgendaEntry.StatusOfEntry.POSTPONED.getStatus());
        assertEquals("Canceled", AgendaEntry.StatusOfEntry.CANCELED.getStatus());
        assertEquals("Done", AgendaEntry.StatusOfEntry.DONE.getStatus());
    }

    @Test
    void toStringOdStatusOfEntryTest(){
        assertEquals("Scheduled", AgendaEntry.StatusOfEntry.SCHEDULED.toString());
        assertEquals("Postponed", AgendaEntry.StatusOfEntry.POSTPONED.toString());
        assertEquals("Canceled", AgendaEntry.StatusOfEntry.CANCELED.toString());
        assertEquals("Done", AgendaEntry.StatusOfEntry.DONE.toString());
    }

    @Test
    void getStatusOfEntryFromStringTest() throws InvalidEntryDataException {
        assertEquals(AgendaEntry.StatusOfEntry.SCHEDULED, AgendaEntry.StatusOfEntry.getStatusOfEntry("Scheduled"));
        assertEquals(AgendaEntry.StatusOfEntry.POSTPONED, AgendaEntry.StatusOfEntry.getStatusOfEntry("Postponed"));
        assertEquals(AgendaEntry.StatusOfEntry.CANCELED, AgendaEntry.StatusOfEntry.getStatusOfEntry("Canceled"));
        assertEquals(AgendaEntry.StatusOfEntry.DONE, AgendaEntry.StatusOfEntry.getStatusOfEntry("Done"));

        assertThrows(InvalidEntryDataException.class, () -> AgendaEntry.StatusOfEntry.getStatusOfEntry("SCHEDULED"));
    }

    @Test
    void getStatusListTest(){

        List<String> statusList = AgendaEntry.StatusOfEntry.getStatusList();
        List<String> expectedList = List.of("Scheduled", "Postponed", "Canceled", "Done");

        assertEquals(4, statusList.size());
        assertEquals(expectedList, statusList);
    }

    @Test
    void getVehicleListTest() throws InvalidEntryDataException {

        Vehicle v1 = new Vehicle("GG-69-EZ","BMW","i4","hibrid",3500.0,
                4500.0,1000.0,new Date(2024,1,10),
                new  Date(2024,1,26),10000.0,0.0);

        Vehicle v2 = new Vehicle("69-WP-42","Toyota","Avensis","Diesel",3000.0,
                4000.0,42000.0,new Date(2018,12,10),
                new  Date(2019,1,10),20000.0,30000.0);


        vehicleRepository.addVehicle(v1);
        vehicleRepository.addVehicle(v2);

        List<Vehicle> vehicleList = List.of(v1, v2);

        AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
        agendaEntry.addVehicleList(vehicleList);
        assertEquals(vehicleList, agendaEntry.getVehicleList());
    }

    @Test
    void getTeamTest() throws InvalidCollaboratorDataException, InvalidEntryDataException {

        Collaborator c1 = new Collaborator("Ana", new Date(1990, 2, 3),
                new Date(2010, 3, 1), "Rua1", "912345669", "ana@collaborator.com",
                "123456789", Collaborator.IdDocType.CC, "234564321zx7", "ABC1234");

        Collaborator c2 = new Collaborator("João", new Date(1980, 2, 3), new Date(2010,
                3, 1), "Rua2", "912345669", "joao@collaborator.com", "234567899",
                Collaborator.IdDocType.BI, "232566381", "ABC1234");

        List<Collaborator> members = List.of(c1, c2);
        Team team = new Team(members);
        teamRepository.addTeam(team);
        AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
        agendaEntry.addTeam(team);

        assertEquals(team, agendaEntry.getTeam());

    }

    @Test
    void setStartDate2(){
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            startDate = new Date(2024, 6, 6);
            agendaEntry.setStartDate(startDate);
            fail("The method should throw an InvalidEntryDataException");
        } catch (InvalidEntryDataException e) {
            assertEquals("The start date of the task cannot be later than the end date.", e.getMessage());
        }
    }

    @Test
    void setDate1(){
        try {
            startDate = new Date(2024, 6, 6);
            endDate = new Date(2024, 6, 6);
            startHour = AgendaEntry.WorkingDayHours.H12;
            endHour = AgendaEntry.WorkingDayHours.H12;
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            fail("The method should throw an InvalidEntryDataException");
        } catch (InvalidEntryDataException e) {
            assertEquals("The start date of the task cannot be later than the end date.", e.getMessage());
        }
    }

    @Test
    void setDate2(){
        try {
            startDate = new Date(2024, 6, 6);
            endDate = new Date(2024, 6, 6);
            startHour = AgendaEntry.WorkingDayHours.H15;
            endHour = AgendaEntry.WorkingDayHours.H12;
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            fail("The method should throw an InvalidEntryDataException");
        } catch (InvalidEntryDataException e) {
            assertEquals("The start date of the task cannot be later than the end date.", e.getMessage());
        }
    }


    @Test
    void setStartHour1() throws InvalidEntryDataException {
        AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
        startHour = AgendaEntry.WorkingDayHours.H12;
        agendaEntry.setStartHour(startHour);
        assertEquals(startHour, agendaEntry.getStartHour());

    }

    @Test
    void setStartHour2(){
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            agendaEntry.setStartHour(null);
            fail("The method should throw an InvalidEntryDataException");
        } catch (InvalidEntryDataException e) {
            assertEquals("The start hour of the task cannot be later than the end hour.", e.getMessage());
        }
    }

    @Test
    void setEndDate1() throws InvalidEntryDataException {
        AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
        endDate = new Date(2024, 6, 6);
        agendaEntry.setEndDate(endDate);
        assertEquals(endDate, agendaEntry.getEndDate());
    }

    @Test
    void setEndDate2(){
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            endDate = new Date(2024, 6, 2);
            agendaEntry.setEndDate(endDate);
            fail("The method should throw an InvalidEntryDataException");
        } catch (InvalidEntryDataException e) {
            assertEquals("The end date of the task cannot be earlier than the start date.", e.getMessage());
        }
    }

    @Test
    void setEndHour1() throws InvalidEntryDataException {
        AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
        endHour = AgendaEntry.WorkingDayHours.H16;
        agendaEntry.setEndHour(endHour);
        assertEquals(endHour, agendaEntry.getEndHour());
    }

    @Test
    void setEndHour2(){
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            agendaEntry.setEndHour(null);
            fail("The method should throw an InvalidEntryDataException");
        } catch (InvalidEntryDataException e) {
            assertEquals("The end hour of the task cannot be earlier than the start hour.", e.getMessage());
        }
    }

    @Test
    void addTeam(){
        try {
            Collaborator c1 = new Collaborator("Ana", new Date(1990, 2, 3),
                    new Date(2010, 3, 1), "Rua1", "912345669", "ana@collaborator.com",
                    "123456789", Collaborator.IdDocType.CC, "234564321zx7", "ABC1234");

            Collaborator c2 = new Collaborator("João", new Date(1980, 2, 3), new Date(2010,
                    3, 1), "Rua2", "912345669", "joao@collaborator.com", "234567899",
                    Collaborator.IdDocType.BI, "232566381", "ABC1234");

            List<Collaborator> members = List.of(c1, c2);
            Team team = new Team(members);
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            agendaEntry.addTeam(team);
        } catch (InvalidCollaboratorDataException ignored) {

        } catch (InvalidEntryDataException e) {
            assertEquals("The team is not in the repository.", e.getMessage());
        }
    }

    @Test
    void taskScheduleTest(){
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);

            agendaEntry.taskPostponed();
            assertEquals(AgendaEntry.StatusOfEntry.POSTPONED, agendaEntry.getStatus());

            agendaEntry.taskSchedule();
            assertEquals(AgendaEntry.StatusOfEntry.SCHEDULED, agendaEntry.getStatus());

            agendaEntry.taskCanceled();
            assertEquals(AgendaEntry.StatusOfEntry.CANCELED, agendaEntry.getStatus());

            agendaEntry.taskDone();
            assertEquals(AgendaEntry.StatusOfEntry.DONE, agendaEntry.getStatus());


        } catch (InvalidEntryDataException e) {
            fail("Exception thrown when it shouldn't have been: " + e.getMessage());
        }
    }

    @Test
    void postponeEntryTest(){
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            agendaEntry.postponeEntry(new Date(2024, 6, 5), new Date(2024, 6, 8));
            agendaEntry.taskPostponed();
            assertEquals(agendaEntry.getStartDate(), new Date(2024, 6, 5));
            assertEquals(AgendaEntry.StatusOfEntry.POSTPONED, agendaEntry.getStatus());
        } catch (InvalidEntryDataException e) {
            fail("Exception thrown when it shouldn't have been: " + e.getMessage());
        }
    }

    @Test
    void cancelEntryTest(){
        try{
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            agendaEntry.postponeEntry(new Date(2024, 6, 5), new Date(2024, 6, 8));
            agendaEntry.taskPostponed();
            assertEquals(agendaEntry.getStartDate(), new Date(2024, 6, 5));
            assertEquals(AgendaEntry.StatusOfEntry.CANCELED, agendaEntry.getStatus());
        } catch (InvalidEntryDataException e) {
            fail("Exception thrown when it shouldn't have been: " + e.getMessage());
        }
    }

    @Test
    void getNameTest(){
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            assertEquals("Task", agendaEntry.getName());
        } catch (InvalidEntryDataException e) {
            fail("Exception thrown when it shouldn't have been: " + e.getMessage());
        }
    }

    @Test
    void addVehicleListTest(){
        try {
            Vehicle v1 = new Vehicle("GG-69-EZ","BMW","i4","hibrid",3500.0,
                    4500.0,1000.0,new Date(2024,1,10),
                    new  Date(2024,1,26),10000.0,0.0);

            Vehicle v2 = new Vehicle("69-WP-42","Toyota","Avensis","Diesel",3000.0,
                    4000.0,42000.0,new Date(2018,12,10),
                    new  Date(2019,1,10),20000.0,30000.0);

            List<Vehicle> vehicleList = List.of(v1, v2);

            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            agendaEntry.addVehicleList(vehicleList);
            assertEquals(vehicleList, agendaEntry.getVehicleList());
        } catch (InvalidEntryDataException e) {
            assertEquals("The vehicle is not in the repository.", e.getMessage());
        }
    }

    @Test
    void getTaskTest(){
        try {
            AgendaEntry agendaEntry = new AgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
            assertEquals(task, agendaEntry.getTask());
        } catch (InvalidEntryDataException e) {
            fail("Exception thrown when it shouldn't have been: " + e.getMessage());
        }
    }
}
