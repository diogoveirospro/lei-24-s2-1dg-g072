package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.Output;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.CollaboratorUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.Output.ShowTaskListUI;

import java.util.ArrayList;
import java.util.List;

public class ShowListTaskController {
    private String[][] dates = new String[0][0];
    private List<String> taskList = new ArrayList<>();
    private CollaboratorUI collaboratorUI;
    private ShowTaskListUI showTaskListUI;
    private ObservableList<String> listOfTasks;
    /**
     * Button to cancel
     */
    @FXML
    private Button btnCancel;

    /**
     * List of all the tasks
     */
    @FXML
    private ListView listTasks;

    @FXML
    public void initialize() {
        try{
            List<String> Output = new ArrayList<>();
            for (int i = 0; i < taskList.size(); i++) {
                if (dates[i][0] != dates[i][1]){
                    Output.add("Task " + taskList.get(i) + " Between " + dates[i][0] + " - " + dates[i][1]);
                } else{
                    Output.add("Task " + taskList.get(i) + " During " + dates[i][0]);
                }

            }
            listOfTasks = FXCollections.observableArrayList(Output);
            listTasks.setItems(listOfTasks);

        } catch (Exception e) {
            System.out.println("Error while loading the task list.");
        }
    }

    public void handleCancelButtonAction() {
        try {
            collaboratorUI = new CollaboratorUI();
            collaboratorUI.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the cancel action: " + e.getMessage());
        }
    }

    public void setShowTaskListUI(ShowTaskListUI showTaskListUI) {
        this.showTaskListUI = showTaskListUI;
    }

    public void setTaskList(List<String> taskList) {
        this.taskList = taskList;
    }

    public void setDates(String[][] dates) {
        this.dates = dates;
    }
}
