package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;
import java.util.Objects;


public class Task {
    public Date duration;
    private String task;

    public Date getDuration() {
        return duration;
    }

    public String getTask() {
        return task;
    }

    public boolean equals(Object vehicleObject) {
        if (this == vehicleObject) return true;
        if (vehicleObject == null || getClass() != vehicleObject.getClass()) return false;
        Task task1 = (Task) vehicleObject;
        return duration.equals(task1.duration) &&
                task.equals(task1.task);
    }
    public int hashCode() {
        return Objects.hash(duration, task);
    }
}
