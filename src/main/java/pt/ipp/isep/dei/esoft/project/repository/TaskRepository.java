package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    List<Task> taskList;

    public List<Task> getCollaboratorTaskList(List<Team> teamList, String typeStatus) {
        List<Task> tasks = new ArrayList<>();
        for (Team team : teamList) {
            for (Task task : taskList) {
                if (task.getTeam().equals(team) && task.getStatus().equals(typeStatus)) {
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }
}
