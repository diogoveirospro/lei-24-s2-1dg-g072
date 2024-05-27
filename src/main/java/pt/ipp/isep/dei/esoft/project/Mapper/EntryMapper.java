package pt.ipp.isep.dei.esoft.project.Mapper;

import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.dto.EntryDto;

import java.util.ArrayList;
import java.util.List;

public class EntryMapper {
    private List<EntryDto> entryListDto;

    public EntryMapper() {
        entryListDto = new ArrayList<>();
    }

    public List<EntryDto> getEntryListDto() {
        return entryListDto;
    }

    public void setEntryListDto(List<EntryDto> entryListDto) {
        this.entryListDto = entryListDto;
    }

    public void addEntry(EntryDto entry) {
        entryListDto.add(entry);
    }

    public  List<EntryDto> toDtoList(List<Entry> entryList) {
        for (Entry entry : entryList) {
            EntryDto entryDto = new EntryDto(
                    entry.getTask(),
                    entry.getStartDate(),
                    entry.getEndDate(),
                    entry.getStatus(),
                    entry.getVehicleList(),
                    entry.getTeam(),
                    entry.getDegree(),
                    entry.getGreenSpace()

            );
            addEntry(entryDto);
        }
        return entryListDto;
    }
}
