package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskRepository {

    private Map<String, Task> tasks;

    public TaskRepository() {
        this.tasks = new HashMap<>();
    }

    /**
     * Adiciona uma tarefa ao repositório.
     *
     * @param task A tarefa a ser adicionada.
     */
    public void addTask(Task task) {
        tasks.put(task.getTaskId(), task);
    }

    /**
     * Remove uma tarefa do repositório.
     *
     * @param taskId O ID da tarefa a ser removida.
     * @return True se a tarefa foi removida com sucesso, False caso contrário.
     */
    public boolean removeTask(String taskId) {
        return tasks.remove(taskId) != null;
    }

    /**
     * Encontra uma tarefa pelo seu ID.
     *
     * @param taskId O ID da tarefa a ser encontrada.
     * @return A tarefa encontrada, ou null se não for encontrada.
     */
    public Task findTaskById(String taskId) {
        return tasks.get(taskId);
    }

    /**
     * Retorna todas as tarefas armazenadas no repositório.
     *
     * @return Uma lista de todas as tarefas.
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }
}
