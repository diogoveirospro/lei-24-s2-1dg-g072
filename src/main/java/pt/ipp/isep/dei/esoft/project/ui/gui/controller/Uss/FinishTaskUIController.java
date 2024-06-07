package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.application.controller.FinishTaskController;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.CollaboratorUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.GSMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.HRMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.FinishTaskUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for managing the Finish Task UI.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class FinishTaskUIController {
    private CollaboratorUI collaboratorUI;
    private FinishTaskController finishTaskController = new FinishTaskController();
    private FinishTaskUI finishTaskUI;
    private ObservableList<String> taskListString ;
    @FXML
    private ComboBox<String> taskIdCb;

    @FXML
    private Button finishTaskButton;

    @FXML
    private Button CancelButton;


    public void setFinishTaskUI(FinishTaskUI finishTaskUI) {
        this.finishTaskUI = finishTaskUI;
    }

    @FXML
    public void initialize() throws InvalidCollaboratorDataException {
        try {
            List<AgendaEntry> agendaEntryList = finishTaskController.getAgendaEntries();
            List<String> taskList = new ArrayList<>();
            for (AgendaEntry agendaEntry : agendaEntryList) {
                taskList.add(agendaEntry.getName());
            }
            taskListString = FXCollections.observableArrayList(taskList);
            taskIdCb.setItems(taskListString);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }

    }
    @FXML
    public void finishTask() {
        String taskId = taskIdCb.getValue();
        try {
            finishTaskController.finishTask(taskId);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Task marked as completed successfully.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void handleCancelButtonAction() {
        try {
            collaboratorUI = new CollaboratorUI();
            collaboratorUI.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the cancel action: " + e.getMessage());
        }
    }
}
