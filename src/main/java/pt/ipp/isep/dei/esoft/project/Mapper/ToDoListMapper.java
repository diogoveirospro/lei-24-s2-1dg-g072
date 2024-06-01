package pt.ipp.isep.dei.esoft.project.Mapper;

import pt.ipp.isep.dei.esoft.project.domain.ToDoListEntry;
import pt.ipp.isep.dei.esoft.project.dto.ToDoListEntryDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper class to convert between ToDoListEntry and ToDoListEntryDto objects.
 * This class contains methods to map individual entries as well as lists of entries.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class ToDoListMapper {

    /**
     * Default constructor for the ToDoListMapper class.
     */
    public ToDoListMapper() {
    }

    /**
     * Converts a list of ToDoListEntry objects to a list of ToDoListEntryDto objects.
     *
     * @param listEntries the list of ToDoListEntry objects to be converted.
     * @return a list of ToDoListEntryDto objects.
     */
    public static List<ToDoListEntryDto> toDoListEntriesToDto(List<ToDoListEntry> listEntries) {
        List<ToDoListEntryDto> listEntriesDto = new ArrayList<>();

        for (ToDoListEntry toDoListEntry : listEntries) {
            ToDoListEntryDto toDoListEntryDto = toDTO(toDoListEntry);
            listEntriesDto.add(toDoListEntryDto);
        }
        return listEntriesDto;
    }

    /**
     * Converts a single ToDoListEntry object to a ToDoListEntryDto object.
     * This is a private helper method used within this class.
     *
     * @param toDoListEntry the ToDoListEntry object to be converted.
     * @return a ToDoListEntryDto object.
     */
    private static ToDoListEntryDto toDTO(ToDoListEntry toDoListEntry) {
        return new ToDoListEntryDto(toDoListEntry);
    }
}
