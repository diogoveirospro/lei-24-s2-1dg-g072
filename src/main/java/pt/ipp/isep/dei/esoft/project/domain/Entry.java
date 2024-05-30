package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

public class Entry {
    private Task task;
    private GreenSpace greenSpace;
    private List<Vehicle> vehicleList;

    public Entry(Task task, GreenSpace greenSpace) {
        this.task = task;
        this.greenSpace = greenSpace;
        this.vehicleList = new ArrayList<>();
    }

    public Task getTask() {
        return task;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public GreenSpace getGreenSpace() {
        return greenSpace;
    }


}
