package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.esoft.project.repository.Agenda;

import java.util.List;
import java.util.stream.Collectors;

public class FinishTaskController {

    private final Agenda agenda;

    public FinishTaskController() {
        this.agenda = new Agenda();
    }

    public List<String> getStatusList() {
        // Return the list of statuses
        return List.of("Not Started", "In Progress", "Completed");
    }

    public Collaborator getCollaboratorByEmail(String email) throws InvalidCollaboratorDataException {
        // Logic to get Collaborator by email
        return agenda.getCollaboratorByEmail(email);
    }

    public List<AgendaEntryDto> getTaskList(Collaborator collaborator, String status, Date startDate, Date endDate) {
        // Logic to get task list based on criteria
        return agenda.getTasksByCollaborator(collaborator).stream()
                .filter(task -> task.getStatus().toString().equals(status)
                        && !task.getStartDate().isBefore(startDate)
                        && !task.getEndDate().isAfter(endDate))
                .map(AgendaEntryDto::new)
                .collect(Collectors.toList());
    }

    public void markTaskAsCompleted(int taskId) {
        // Logic to mark task as completed
        agenda.markTaskAsCompleted(taskId);
    }
}
