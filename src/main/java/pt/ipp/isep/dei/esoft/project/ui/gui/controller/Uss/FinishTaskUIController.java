package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.application.controller.FinishTaskController;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.FinishTaskUI;

/**
 * Controller class for managing the Finish Task UI.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class FinishTaskUIController {

    private FinishTaskController finishTaskController;
    private FinishTaskUI finishTaskUI;

    @FXML
    private TextField taskIdField;

    @FXML
    private Button finishTaskButton;

    public FinishTaskUIController() {
        finishTaskController = new FinishTaskController();
    }

    public void setFinishTaskUI(FinishTaskUI finishTaskUI) {
        this.finishTaskUI = finishTaskUI;
    }

    @FXML
    public void finishTask() {
        String taskId = taskIdField.getText();
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
}
