package pt.ipp.isep.dei.esoft.project.Mapper;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDto;

import java.util.ArrayList;
import java.util.List;

public class AgendaEntryMapper {
    private List<AgendaEntryDto> entryListDto;

    public AgendaEntryMapper() {
        entryListDto = new ArrayList<>();
    }

    public List<AgendaEntryDto> getEntryListDto() {
        return entryListDto;
    }

    public void setEntryListDto(List<AgendaEntryDto> entryListDto) {
        this.entryListDto = entryListDto;
    }

    public void addEntry(AgendaEntryDto entry) {
        entryListDto.add(entry);
    }

    public List<AgendaEntryDto> toDtoList(List<AgendaEntry> agendaEntryList) {
        for (AgendaEntry agendaEntry : agendaEntryList) {
            AgendaEntryDto agendaEntryDto = new AgendaEntryDto(
                    agendaEntry.getToDoListEntry(),
                    agendaEntry.getStartDate(),
                    agendaEntry.getEndDate(),
                    agendaEntry.getStatus(),
                    agendaEntry.getGreenSpace(),
                    agendaEntry.getTask(),
                    agendaEntry.getTeam(),
                    agendaEntry.getVehicleList()
            );
            addEntry(agendaEntryDto);
        }
        return entryListDto;
    }
}
