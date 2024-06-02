package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidAgendaEntryDataException;
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
public class AddAgendaEntryUIController {

    private CollaboratorRepository collaboratorRepository;

    private GreenSpaceRepository greenSpaceRepository;

    private ToDoList toDoList;

    private Agenda agenda;

    /**
     * Default constructor initializing the repositories and agenda from the singleton instance of Repositories.
     */
    public AddAgendaEntryUIController() {
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
    public AddAgendaEntryUIController(CollaboratorRepository collaboratorRepository,
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
     * @param greenSpaceDto the green space DTO
     * @return a list of ToDoListEntryDto representing the to-do list entries
     */
    public List<ToDoListEntryDto> getToDoListEntries(GreenSpaceDto greenSpaceDto) {
        GreenSpace greenSpace = (GreenSpace) toDomain(greenSpaceDto);
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
     * Creates a new agenda entry.
     *
     * @param task       the task associated with the agenda entry
     * @param greenSpace the green space associated with the agenda entry
     * @param startDate  the start date of the agenda entry
     * @param endDate    the end date of the agenda entry
     * @return the created agenda entry
     * @throws InvalidAgendaEntryDataException if the start date is later than the end date
     */
    public AgendaEntry createAgendaEntry(Task task, GreenSpace greenSpace, Date startDate, Date endDate) throws InvalidAgendaEntryDataException {
        agenda = Repositories.getInstance().getAgenda();
        return agenda.createAgendaEntry(task, greenSpace, startDate, endDate);
    }

    /**
     * Adds an agenda entry to the agenda.
     *
     * @param agendaEntry the agenda entry to be added
     * @return true if the entry was successfully added, false otherwise
     * @throws InvalidAgendaEntryDataException if the agenda entry is invalid
     */
    public boolean addAgendaEntry(AgendaEntry agendaEntry) throws InvalidAgendaEntryDataException {
        if (agendaEntry == null) {
            throw new InvalidAgendaEntryDataException("Agenda Entry is invalid.");
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
            return toDoList.getToDoListEntryByTaskHashCode(((ToDoListEntryDto) dto).getTaskHashCode());
        }
        return null;
    }
}
