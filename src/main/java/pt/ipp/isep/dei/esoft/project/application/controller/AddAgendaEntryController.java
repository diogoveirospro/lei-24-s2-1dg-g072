package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.Mapper.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.Mapper.ToDoListMapper;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.esoft.project.dto.ToDoListEntryDto;
import pt.ipp.isep.dei.esoft.project.repository.*;
import java.util.List;

/**
 * Controller for managing agenda entries.
 * This controller handles the creation and addition of agenda entries, as well as retrieving green spaces and to-do list entries.
 */
public class AddAgendaEntryController {

    private CollaboratorRepository collaboratorRepository;

    private GreenSpaceRepository greenSpaceRepository;

    private ToDoList toDoList;

    private Agenda agenda;

    /**
     * Default constructor initializing the repositories and agenda from the singleton instance of Repositories.
     */
    public AddAgendaEntryController() {
        collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
        toDoList = Repositories.getInstance().getToDoList();
        agenda = Repositories.getInstance().getAgenda();
    }

    /**
     * Constructor for initializing the controller with specific repositories and agenda.
     *
     * @param collaboratorRepository the collaborator repository
     * @param greenSpaceRepository   the green space repository
     * @param toDoList               the to-do list
     * @param agenda                 the agenda
     */
    public AddAgendaEntryController(CollaboratorRepository collaboratorRepository,
                                    GreenSpaceRepository greenSpaceRepository, ToDoList toDoList, Agenda agenda) {
        this.collaboratorRepository = collaboratorRepository;
        this.greenSpaceRepository = greenSpaceRepository;
        this.toDoList = toDoList;
        this.agenda = agenda;
    }

    /**
     * Retrieves a list of green spaces managed by the current session's collaborator.
     *
     * @return a list of GreenSpaceDto representing the green spaces
     */
    public List<GreenSpaceDto> getListGreenSpaces() {
        Collaborator GSM = getCollaboratorFromSession();
        List<GreenSpace> listGreenSpaces = greenSpaceRepository.getListGreenSpacesManagedByGsm(GSM);

        GreenSpaceMapper greenSpaceMapper = new GreenSpaceMapper();

        return greenSpaceMapper.greenSpaceListToDto(listGreenSpaces);
    }

    /**
     * Retrieves the collaborator from the current session.
     *
     * @return the collaborator associated with the current session's user email
     */
    private Collaborator getCollaboratorFromSession() {
        String email = ApplicationSession.getInstance().getCurrentSession().getUserEmail();
        return collaboratorRepository.getCollaboratorByEmail(email);
    }

    /**
     * Retrieves a list of to-do list entries for a given green space.
     *
     * @param greenSpace the green space
     * @return a list of ToDoListEntryDto representing the to-do list entries
     */
    public List<ToDoListEntryDto> getToDoListEntries(GreenSpace greenSpace) {
        List<ToDoListEntry> listEntries = greenSpace.getToDoList().getToDoListEntries();
        return ToDoListMapper.toDoListEntriesToDto(listEntries);
    }

    /**
     * Retrieves the task associated with a given to-do list entry DTO.
     *
     * @param toDoListEntryDto the to-do list entry DTO
     * @return the task associated with the to-do list entry
     */
    public Task getTask(ToDoListEntryDto toDoListEntryDto) {
        ToDoListEntry toDoListEntry = (ToDoListEntry) toDomain(toDoListEntryDto);
        return toDoListEntry.getTask();
    }

    /**
     * Creates a new agenda entry with the provided data.
     *
     * @param task          The task associated with the agenda entry. May be null.
     * @param greenSpace    The green space associated with the agenda entry. May be null.
     * @param startDate     The start date of the agenda entry. Cannot be null.
     * @param startHour     The start hour of the agenda entry. Cannot be null.
     * @param endDate       The end date of the agenda entry. Cannot be null.
     * @param endHour       The end hour of the agenda entry. Cannot be null.
     * @return              The new agenda entry created.
     * @throws InvalidEntryDataException If the provided data is invalid.
     */
    public AgendaEntry createAgendaEntry(Task task, GreenSpace greenSpace, Date startDate, AgendaEntry.WorkingDayHours startHour, Date endDate, AgendaEntry.WorkingDayHours endHour) throws InvalidEntryDataException {
        agenda = Repositories.getInstance().getAgenda();
        return agenda.createAgendaEntry(task, greenSpace, startDate, startHour, endDate, endHour);
    }

    /**
     * Adds an agenda entry to the agenda.
     *
     * @param agendaEntry the agenda entry to be added
     * @return true if the entry was successfully added, false otherwise
     * @throws InvalidEntryDataException if the agenda entry is invalid
     */
    public boolean addAgendaEntry(AgendaEntry agendaEntry) throws InvalidEntryDataException {
        if (agendaEntry == null) {
            throw new InvalidEntryDataException("Agenda Entry is invalid.");
        }
        return agenda.addAgendaEntry(agendaEntry);
    }

    /**
     * Converts a DTO to its domain object equivalent.
     *
     * @param <T> the type of the DTO
     * @param dto the DTO to be converted
     * @return the domain object equivalent of the DTO, or null if the conversion is not applicable
     */
    public <T> Object toDomain(T dto) {
        if (dto instanceof GreenSpaceDto) {
            return greenSpaceRepository.getGreenSpaceByParkName(((GreenSpaceDto) dto).getParkName());
        } else if (dto instanceof ToDoListEntryDto) {
            return toDoList.getToDoListEntryByTaskName(((ToDoListEntryDto) dto).getTaskName());
        }
        return null;
    }

    /**
     * Retrieves a GreenSpaceDto object by the name of the green space.
     *
     * @param selectedGreenSpaceName the name of the green space to retrieve
     * @return the GreenSpaceDto object representing the green space with the specified name
     */

    public GreenSpaceDto getGreenSpaceByName(String selectedGreenSpaceName) {
        GreenSpace greenSpace = greenSpaceRepository.getGreenSpaceByParkName(selectedGreenSpaceName);
        return new GreenSpaceDto(greenSpace);
    }

    /**
     * Retrieves a ToDoListEntryDto object by the task name from a specific green space.
     *
     * @param selectedItem the name of the task item in the format "taskName - otherInfo"
     * @param greenSpaceDto the GreenSpaceDto object representing the green space containing the task
     * @return the ToDoListEntryDto object representing the to-do list entry with the specified task name
     */

    public ToDoListEntryDto getToDoListEntry(String selectedItem, GreenSpaceDto greenSpaceDto) {
        GreenSpace greenSpace = (GreenSpace) toDomain(greenSpaceDto);

        String taskName = selectedItem.substring(0, selectedItem.indexOf("-")).trim();

        ToDoListEntry toDoListEntry = greenSpace.getToDoList().getToDoListEntryByTaskName(taskName);
        return new ToDoListEntryDto(toDoListEntry);
    }
}
