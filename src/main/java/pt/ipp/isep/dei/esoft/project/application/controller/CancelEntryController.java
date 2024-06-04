package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.Mapper.AgendaEntryMapper;
import pt.ipp.isep.dei.esoft.project.Mapper.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.esoft.project.repository.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;
import java.util.Objects;

public class CancelEntryController {

    private final Agenda agenda;

    private GreenSpaceRepository greenSpaceRepository;

    private CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();

    private final List<AgendaEntry> agendaEntries = getAgendaEntryList();

    public CancelEntryController() {
        this.agenda = Repositories.getInstance().getAgenda();
        this.greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
    }

    public CancelEntryController(Agenda agenda, GreenSpaceRepository greenSpaceRepository) {
        this.agenda = agenda;
        this.greenSpaceRepository = greenSpaceRepository;
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

    public List<AgendaEntry> getAgendaEntryList(){
        return Objects.requireNonNull(agenda).getEntryList();
    }

    public List<AgendaEntryDto> entryListToDto(){
        AgendaEntryMapper agendaEntryMapper = new AgendaEntryMapper();
        return agendaEntryMapper.toDtoList(agendaEntries);
    }

    public void cancelAgendaEntry(AgendaEntry agendaEntry) throws InvalidEntryDataException {
        if (agendaEntry == null) {
            throw new InvalidEntryDataException("Agenda Entry is invalid.");
        } else
            agendaEntry.taskCanceled();
    }

}