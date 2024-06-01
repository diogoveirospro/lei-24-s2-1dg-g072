package pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss;

import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.Mapper.AgendaEntryMapper;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.esoft.project.repository.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;
import java.util.Objects;

public class PostponeEntryUI {

    private final Agenda agenda;

    private final List<AgendaEntry> agendaEntries = getAgendaEntryList();

    public PostponeEntryUI() {
        this.agenda = Repositories.getInstance().getAgenda();
    }

    public PostponeEntryUI(Agenda agenda) {
        this.agenda = agenda;
    }

    public List<AgendaEntry> getAgendaEntryList(){
        return Objects.requireNonNull(agenda).getEntryList();
    }

    public List<AgendaEntryDto> entryListToDto(){
        AgendaEntryMapper agendaEntryMapper = new AgendaEntryMapper();
        return agendaEntryMapper.toDtoList(agendaEntries);
    }

    public void showUI(Stage primaryStage) {

    }
}
