/*
package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.esoft.project.Mapper.AgendaEntryMapper;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class FinishTaskController {

    private final Agenda agenda;
    private final CollaboratorRepository collaboratorRepository;
    private final ApplicationSession appSession;

    public FinishTaskController() {
        this.agenda = Repositories.getInstance().getAgenda();
        this.collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        this.appSession = ApplicationSession.getInstance();
    }

    public Collaborator getCollaboratorByEmail(String email) {
        return collaboratorRepository.getCollaboratorByEmail(email);
    }

    public List<AgendaEntryDto> getTaskList(Collaborator collaborator, Date dateI, Date dateF, String typeStatus) {
        List<AgendaEntry> agendaEntries = agenda.getCollaboratorAgendaEntries(collaborator, dateI, dateF, typeStatus);
        return AgendaEntryMapper.toDTOList(agendaEntries);
    }

    public Optional<AgendaEntryDto> markTaskAsCompleted(String taskId) {
        Optional<AgendaEntry> optionalAgendaEntry = agenda.getAgendaEntry(taskId);
        if (optionalAgendaEntry.isPresent()) {
            AgendaEntry agendaEntry = optionalAgendaEntry.get();
            agendaEntry.markAsCompleted();
            return Optional.of(AgendaEntryMapper.toDTO(agendaEntry));
        }
        return Optional.empty();
    }
}

 */
