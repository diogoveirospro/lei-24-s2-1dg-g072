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

public class AddAgendaEntryController {

    private CollaboratorRepository collaboratorRepository;

    private GreenSpaceRepository greenSpaceRepository;

    private ToDoList toDoList;

    private Agenda agenda;

    public AddAgendaEntryController() {
        collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
        toDoList = Repositories.getInstance().getToDoList();
        agenda = Repositories.getInstance().getAgenda();
    }

    public AddAgendaEntryController(CollaboratorRepository collaboratorRepository,
                                    GreenSpaceRepository greenSpaceRepository, ToDoList toDoList, Agenda agenda) {
        this.collaboratorRepository = collaboratorRepository;
        this.greenSpaceRepository = greenSpaceRepository;
        this.toDoList = toDoList;
        this.agenda = agenda;

    }

    public List<GreenSpaceDto> getListGreenSpaces() {
        Collaborator GSM = getCollaboratorFromSession();
        List<GreenSpace> listGreenSpaces = greenSpaceRepository.getListGreenSpacesManagedByGsm(GSM);

        GreenSpaceMapper greenSpaceMapper = new GreenSpaceMapper();

        return greenSpaceMapper.greenSpaceListToDto(listGreenSpaces);
    }

    private Collaborator getCollaboratorFromSession() {
        String email = ApplicationSession.getInstance().getCurrentSession().getUserEmail();

        return collaboratorRepository.getCollaboratorByEmail(email);

    }

    public List<ToDoListEntryDto> getToDoListEntries(GreenSpaceDto greenSpaceDto) {

        GreenSpace greenSpace = (GreenSpace) toDomain(greenSpaceDto);

        List<ToDoListEntry> listEntries = greenSpace.getToDoList().getToDoListEntries();

        return ToDoListMapper.toDoListEntriesToDto(listEntries);

    }

    public Task getTask (ToDoListEntryDto toDoListEntryDto) {
        ToDoListEntry toDoListEntry = (ToDoListEntry) toDomain(toDoListEntryDto);

        return toDoListEntry.getTask();
    }

    public AgendaEntry createAgendaEntry(Task task, GreenSpace greenSpace, Date startDate, Date endDate) throws InvalidAgendaEntryDataException {
        agenda = Repositories.getInstance().getAgenda();

        return agenda.createAgendaEntry(task, greenSpace, startDate, endDate);

    }

    public boolean addAgendaEntry(AgendaEntry agendaEntry) {
        return agenda.addAgendaEntry(agendaEntry);
    }

    public <T> Object toDomain(T dto) {
        if (dto instanceof GreenSpaceDto) {
            greenSpaceRepository.getGreenSpaceByParkName(((GreenSpaceDto) dto).getParkName());

        } else if (dto instanceof ToDoListEntryDto) {
            return toDoList.getToDoListEntryByTaskHashCode(((ToDoListEntryDto) dto).getTaskHashCode());
        }
        return null;
    }
}
