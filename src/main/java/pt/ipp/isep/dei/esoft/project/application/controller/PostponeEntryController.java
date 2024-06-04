package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.Mapper.AgendaEntryMapper;
import pt.ipp.isep.dei.esoft.project.Mapper.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.esoft.project.repository.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;
import java.util.Objects;

public class PostponeEntryController {

    private final Agenda agenda;

    private GreenSpaceRepository greenSpaceRepository;

    private CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();

    private List<AgendaEntry> agendaEntries;

    public PostponeEntryController() {
        this.agenda = Repositories.getInstance().getAgenda();
        this.greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
        this.agendaEntries = agenda.getEntryList();
    }

    public PostponeEntryController(Agenda agenda, GreenSpaceRepository greenSpaceRepository) {
        this.agenda = agenda;
        this.greenSpaceRepository = greenSpaceRepository;
        this.agendaEntries = agenda.getEntryList();
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

    public List<AgendaEntry> getAgendaEntryList() {
        if (agenda != null) {
            return agenda.getEntryList();
        } else return null;
    }

    public List<String> getAgendaEntryListString() {
        List<String> agendaEntries = agenda.getAgendaEntryList();
        return agendaEntries;
    }

    public List<AgendaEntryDto> entryListToDto() {
        AgendaEntryMapper agendaEntryMapper = new AgendaEntryMapper();
        return agendaEntryMapper.toDtoList(agendaEntries);
    }

    public void postponeAgendaEntry(AgendaEntry agendaEntry, Date newDate) throws InvalidEntryDataException {
        if (agendaEntry == null || newDate == null) {
            throw new InvalidEntryDataException("Agenda Entry is invalid.");
        } else
            agendaEntry.postponeEntry(newDate);
        agendaEntry.taskPostponed();
    }

    public AgendaEntry getAgendaEntryByTaskName(String taskName) {
        try {
            for (AgendaEntry entry : agendaEntries) {
                if (Objects.equals(entry.getName(), taskName)) {
                    return entry;
                }
            }
        }catch (Exception e){
            System.out.println("Error while getting the agenda entry by task name.");
        }
        return null;
    }
}
