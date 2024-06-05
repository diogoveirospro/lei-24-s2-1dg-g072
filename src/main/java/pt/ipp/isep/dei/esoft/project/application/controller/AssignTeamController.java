package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.esoft.project.repository.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.util.List;
import java.util.Properties;

public class AssignTeamController {

    private Agenda agenda;
    private TeamRepository teamRepository;

    public AssignTeamController() {
        this.agenda = Repositories.getInstance().getAgenda();
        this.teamRepository = Repositories.getInstance().getTeamRepository();
    }

    public List<AgendaEntry> getAgendaEntries() {
        return this.agenda.getEntryList();
    }

    public List<Team> getValidTeams(AgendaEntry agendaEntry) {
        return this.teamRepository.getValidTeams(agendaEntry);
    }

    public boolean assignTeamToAgendaEntry(AgendaEntry agendaEntry, Team team) {
        return agenda.assignTeamToAgendaEntry(agendaEntry, team);
    }

    public String showEmailServices(){
        Properties prop = new Properties();

    }
}
